//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.google.common.collect.Lists;
import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.domain.MetaField;
import com.jr.basic.meta.query.Condition;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.query.Condition.Type;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "",
    isMeta = true
)
public final class DomainListFetcher implements DataFetcher<List<Domain>> {
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;

    public DomainListFetcher() {
    }

    public List<Domain> get(DataFetchingEnvironment env) {
        String domainName = ((Field)env.getFields().get(0)).getName();
        MetaDomain queryDomain = this.metaService.getByName(domainName);
        Query<Domain> queryCond = new Query();
        env.getArguments().entrySet().stream().forEach((arg) -> {
            String argumentName = (String)arg.getKey();
            Object value = arg.getValue();
            if (value != null) {
                byte var6 = -1;
                switch(argumentName.hashCode()) {
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
        return (List)this.dynamicService.findAll(queryDomain.getDomainClass(), queryCond).orElse(Lists.newArrayList());
    }
}
