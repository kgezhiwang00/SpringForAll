//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.google.common.collect.Lists;
import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.api.common.GraphQLConfig;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.domain.MetaField;
import com.jr.basic.meta.enums.Aggregate;
import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.query.Select;
import com.jr.basic.meta.query.Condition.Type;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "aggregate",
    isMeta = true
)
public class DomainAggregateFetcher implements DataFetcher<List<Map<String, Object>>> {
    private static final String GROUPS = "_groups_";
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private GraphQLConfig config;

    public DomainAggregateFetcher() {
    }

    public List<Map<String, Object>> get(DataFetchingEnvironment env) {
        String domainName = ((Field)env.getFields().get(0)).getName();
        domainName = domainName.substring(0, domainName.indexOf(this.config.getSplit()));
        MetaDomain queryDomain = this.metaService.getByName(domainName);
        Query<Domain> queryCond = new Query();
        if (!queryDomain.hasAggregate().booleanValue()) {
            throw new IllegalArgumentException("error.domain.fetcher.aggregate.unknown");
        } else {
            env.getArguments().entrySet().stream().forEach((arg) -> {
                String argumentName = (String)arg.getKey();
                Object value = arg.getValue();
                if (value != null) {
                    byte var6 = -1;
                    switch(argumentName.hashCode()) {
                    case -1643313204:
                        if (argumentName.equals("_groups_")) {
                            var6 = 3;
                        }
                        break;
                    case -1468771663:
                        if (argumentName.equals("_page_")) {
                            var6 = 0;
                        }
                        break;
                    case 91078746:
                        if (argumentName.equals("_max_")) {
                            var6 = 1;
                        }
                        break;
                    case 1324795417:
                        if (argumentName.equals("_orderBy_")) {
                            var6 = 2;
                        }
                    }

                    switch(var6) {
                    case 0:
                        queryCond.getPage().setCurPage((Integer)arg.getValue());
                        queryCond.getPage().setPageable(true);
                        break;
                    case 1:
                        queryCond.getPage().setMax((Integer)arg.getValue());
                        queryCond.getPage().setPageable(true);
                        break;
                    case 2:
                        if (StringUtils.isNotBlank((String)value)) {
                            queryCond.addOrder((String)value);
                        }
                        break;
                    case 3:
                        ((List)value).forEach((g) -> {
                            queryCond.group(g);
                        });
                        break;
                    default:
                        MetaField field = queryDomain.getField(argumentName);
                        if (field.getIsRangeQuery().booleanValue()) {
                            if (value instanceof List) {
                                queryCond.geth(argumentName, ((List)value).get(0));
                                queryCond.leth(argumentName, ((List)value).get(1));
                            }
                        } else {
                            queryCond.addCondition(new Condition(argumentName, field.getQueryType() == null ? Type.eq : Type.valueOf(field.getQueryType()), value));
                        }
                    }
                }

            });
            queryDomain.getAggregateField().forEach((f) -> {
                Aggregate[] var2 = f.getAggregates();
                int var3 = var2.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    Aggregate aggregate = var2[var4];
                    queryCond.select(new Select(f.getName(), aggregate));
                }

            });
            return (List)this.dynamicService.findAggregate(queryDomain.getDomainClass(), queryCond).orElse(Lists.newArrayList());
        }
    }
}
