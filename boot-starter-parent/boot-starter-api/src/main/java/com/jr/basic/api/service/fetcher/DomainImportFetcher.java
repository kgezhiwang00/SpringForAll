//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.service.fetcher;

import com.jr.basic.api.annotation.Fetcher;
import com.jr.basic.api.common.GraphQLConfig;
import com.jr.basic.api.common.wrapper.CustomBeanWrapper;
import com.jr.basic.meta.domain.Domain;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.domain.MetaField;
import com.jr.basic.meta.domain.MetaRelation;
import com.jr.basic.meta.enums.Type;
import com.jr.basic.meta.exception.ServiceException;
import com.jr.basic.meta.query.Query;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.MetaService;
import graphql.language.Field;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Fetcher(
    value = "import",
    isMeta = true,
    isMutation = true
)
public class DomainImportFetcher<T extends Domain> implements DataFetcher<T> {
    private static final Logger log = LoggerFactory.getLogger(DomainImportFetcher.class);
    private static final String BOOL_VALUE_TRUE = "是";
    @Autowired
    private MetaService metaService;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private GraphQLConfig config;

    public DomainImportFetcher() {
    }

    public T get(DataFetchingEnvironment env) {
        try {
            String domainName = ((Field)env.getFields().get(0)).getName();
            domainName = domainName.substring(0, domainName.indexOf(this.config.getSplit()));
            MetaDomain meta = this.metaService.getByName(domainName);
            Domain cdomain = (Domain)meta.getDomainClass().newInstance();
            BeanWrapperImpl beanWrapper = new CustomBeanWrapper(cdomain);
            Iterator var6 = meta.getImportField().iterator();

            while(true) {
                String fname;
                Object value;
                MetaRelation relation;
                do {
                    while(true) {
                        MetaField cf;
                        do {
                            if (!var6.hasNext()) {
                                return this.dynamicService.create(cdomain);
                            }

                            cf = (MetaField)var6.next();
                            fname = cf.getName();
                            value = env.getArgument(fname);
                        } while(value == null);

                        relation = cf.getRelation();
                        if (relation != null) {
                            break;
                        }

                        if (cf.getType().equals(Type.ENUM)) {
                            Method method = cf.getFieldClass().getMethod("labelOf", String.class);
                            beanWrapper.setPropertyValue(fname, method.invoke((Object)null, value));
                        } else if (cf.getType().equals(Type.BOOLEAN)) {
                            beanWrapper.setPropertyValue(fname, "是".equals(value) ? Boolean.TRUE : Boolean.FALSE);
                        } else {
                            beanWrapper.setPropertyValue(fname, value);
                        }
                    }
                } while(!com.jr.basic.meta.domain.MetaRelation.Type.ONE_TO_ONE.equals(relation.getType()) && !com.jr.basic.meta.domain.MetaRelation.Type.MANY_TO_ONE.equals(relation.getType()));

                Query<T> query = (new Query()).eq(relation.getShowField(), value);
                Optional<T> ref = this.dynamicService.get(relation.getDomain(), query);
                if (ref.isPresent()) {
                    beanWrapper.setPropertyValue(fname, ((Domain)ref.get()).getId());
                }
            }
        } catch (ServiceException var13) {
            if (log.isErrorEnabled()) {
                log.error("dynamic import domain error {} ", var13.getMessage());
            }

            throw var13;
        } catch (Exception var14) {
            if (log.isErrorEnabled()) {
                log.error("dynamic import domain error {} ", var14.getMessage());
            }

            throw new ServiceException(var14);
        }
    }
}
