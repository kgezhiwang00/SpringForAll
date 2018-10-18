//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta;

import com.jr.basic.meta.dao.DynamicDao;
import com.jr.basic.meta.dao.impl.DynamicDaoImpl;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.impl.DynamicServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaAutoConfiguration {
    public MetaAutoConfiguration() {
    }

    @Bean
    public DynamicDao dynamicDao() {
        return new DynamicDaoImpl();
    }

    @Bean
    public DynamicService dynamicService() {
        return new DynamicServiceImpl();
    }
}
