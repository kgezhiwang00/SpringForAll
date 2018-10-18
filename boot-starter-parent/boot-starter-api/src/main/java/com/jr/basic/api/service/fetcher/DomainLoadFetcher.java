//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.api.common.GraphQLConfig;
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
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "load",
    isMeta = true
)
public final class DomainLoadFetcher implements DataFetcher<Domain> {
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private GraphQLConfig config;

    public DomainLoadFetcher() {
    }

    public Domain get(DataFetchingEnvironment env) {
        String domainName = ((Field)env.getFields().get(0)).getName();
        domainName = domainName.substring(0, domainName.indexOf(this.config.getSplit()));
        MetaDomain queryDomain = this.metaService.getByName(domainName);
        Query<Domain> queryCond = new Query();
        env.getArguments().entrySet().stream().forEach((arg) -> {
            String argumentName = (String)arg.getKey();
            Object value = arg.getValue();
            if (value != null) {
                MetaField field = queryDomain.getField(argumentName);
                if (field.getQueryType() == null) {
                    queryCond.addCondition(Condition.eq(argumentName, value));
                } else {
                    queryCond.addCondition(new Condition(argumentName, Type.valueOf(field.getQueryType()), value));
                }
            }

        });
        return (Domain)this.dynamicService.get(queryDomain.getDomainClass(), queryCond).orElse(null);
    }
}
