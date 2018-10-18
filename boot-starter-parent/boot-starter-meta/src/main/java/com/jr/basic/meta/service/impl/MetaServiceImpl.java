//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableMap.Builder;
import com.jr.basic.meta.domain.MetaDomain;
import com.jr.basic.meta.service.MetaNameStrategy;
import com.jr.basic.meta.service.MetaService;
import com.jr.basic.meta.utils.MetaUtils;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MetaServiceImpl implements MetaService {
    private static final Logger log = LoggerFactory.getLogger(MetaServiceImpl.class);
    private ImmutableMap<String, MetaDomain> domains;
    @Autowired(
        required = false
    )
    private MetaNameStrategy nameStrategy = new MetaNameStrategyImpl();

    public MetaServiceImpl(Set<Class<?>> domainClasses) {
        Preconditions.checkNotNull(domainClasses, "meta classes is null");
        if (log.isDebugEnabled()) {
            log.debug(String.format("read to load meta classes total: [%d]", domainClasses.size()));
        }

        Builder<String, MetaDomain> builder = ImmutableMap.builder();
        domainClasses.forEach((clazz) -> {
            MetaDomain domain = MetaUtils.resolveDomain(clazz);
            builder.put(domain.getName(), domain);
        });
        this.domains = builder.build();
    }

    public MetaDomain getByFullName(String fullName) {
        UnmodifiableIterator var2 = this.domains.values().iterator();

        MetaDomain domain;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            domain = (MetaDomain)var2.next();
        } while(!domain.getFullName().equals(fullName));

        return domain;
    }

    public List<MetaDomain> findAll() {
        return ImmutableList.copyOf(this.domains.values());
    }

    public MetaDomain getByName(String name) {
        return (MetaDomain)this.domains.get(name);
    }
}
