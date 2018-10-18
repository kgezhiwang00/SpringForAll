//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.google.common.collect.Lists;
import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.query.Query;
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
    value = "select",
    isMeta = true
)
public final class DomainSelectFetcher implements DataFetcher<List<?>> {
    private static final String SEARCH_NAME = "value";
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;

    public DomainSelectFetcher() {
    }

    public List<?> get(DataFetchingEnvironment env) {
        String domainName = ((Field)env.getFields().get(0)).getName();
        MetaDomain meta = this.metaService.getByName(domainName);
        String fieldName = meta.getShowField();
        Query<Domain> queryCond = new Query();
        env.getArguments().entrySet().stream().forEach((arg) -> {
            String argumentName = (String)arg.getKey();
            Object value = arg.getValue();
            if (value != null) {
                byte var5 = -1;
                switch(argumentName.hashCode()) {
                case -1468771663:
                    if (argumentName.equals("_page_")) {
                        var5 = 0;
                    }
                    break;
                case 91078746:
                    if (argumentName.equals("_max_")) {
                        var5 = 1;
                    }
                    break;
                case 111972721:
                    if (argumentName.equals("value")) {
                        var5 = 3;
                    }
                    break;
                case 1324795417:
                    if (argumentName.equals("_orderBy_")) {
                        var5 = 2;
                    }
                }

                switch(var5) {
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
                    queryCond.like("value", value);
                }
            }

        });
        return (List)this.dynamicService.findAll(meta.getDomainClass(), queryCond).orElse(Lists.newArrayList());
    }
}
