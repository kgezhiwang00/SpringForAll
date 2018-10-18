//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.google.common.base.Preconditions;
import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.service.MetaService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher("metaDomain")
public final class MetaLoadFetcher implements DataFetcher<MetaDomain> {
    @Autowired
    private MetaService metaService;

    public MetaLoadFetcher() {
    }

    public MetaDomain get(DataFetchingEnvironment env) {
        String value = (String)env.getArgument("name");
        Preconditions.checkNotNull(value, "need load meta with name");
        return this.metaService.getByName(value);
    }
}
