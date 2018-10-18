//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.api.common.GraphQLConfig;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "delete",
    isMeta = true,
    isMutation = true
)
public final class DomainDeleteFetcher implements DataFetcher<Domain> {
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private GraphQLConfig config;

    public DomainDeleteFetcher() {
    }

    public Domain get(DataFetchingEnvironment env) {
        String domainName = ((Field)env.getFields().get(0)).getName();
        domainName = domainName.substring(0, domainName.indexOf(this.config.getSplit()));
        MetaDomain domain = this.metaService.getByName(domainName);
        Long id = (Long)env.getArgument("id");
        Domain original = (Domain)this.dynamicService.get(domain.getDomainClass(), id).orElse((Object)null);
        this.dynamicService.delete(original);
        return original;
    }
}
