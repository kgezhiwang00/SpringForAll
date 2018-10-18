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
import com.jr.basic.meta.exception.ServiceException;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.util.Iterator;
import java.util.function.Supplier;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "update",
    isMeta = true,
    isMutation = true
)
public final class DomainUpdateFetcher implements DataFetcher<Domain> {
    private static final Logger log = LoggerFactory.getLogger(DomainUpdateFetcher.class);
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private GraphQLConfig config;

    public DomainUpdateFetcher() {
    }

    public Domain get(DataFetchingEnvironment env) {
        try {
            String domainName = ((Field)env.getFields().get(0)).getName();
            domainName = domainName.substring(0, domainName.indexOf(this.config.getSplit()));
            MetaDomain domain = this.metaService.getByName(domainName);
            Long id = (Long)env.getArgument("id");
            Domain original = (Domain)this.dynamicService.get(domain.getDomainClass(), id).orElseThrow(() -> {
                return new NullPointerException("can not load domain by id " + id);
            });
            BeanWrapperImpl beanWrapper = new BeanWrapperImpl(original);
            Iterator var7 = domain.getUpdateField().iterator();

            while(var7.hasNext()) {
                MetaField cf = (MetaField)var7.next();
                Object value = env.getArgument(cf.getName());
                if (value != null) {
                    beanWrapper.setPropertyValue(cf.getName(), value);
                }
            }

            return this.dynamicService.patch(original);
        } catch (ConstraintViolationException var10) {
            log.error("dynamic create domain error {}", var10.getMessage());
            throw var10;
        } catch (ServiceException var11) {
            log.error("dynamic import domain error {} ", var11.getMessage());
            throw var11;
        } catch (Exception var12) {
            log.error("dynamic import domain error {} ", var12.getMessage());
            throw new ServiceException(var12);
        }
    }
}
