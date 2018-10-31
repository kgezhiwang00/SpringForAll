package com.jr.basic.meta;

import com.jr.basic.meta.dao.impl.DynamicDao;
import com.jr.basic.meta.dao.impl.DynamicDaoImpl;
import com.jr.basic.meta.service.DynamicService;
import com.jr.basic.meta.service.impl.DynamicServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zcx
 * @Date: 2018/10/23 09:46
 * @Description: 配置类
 */
@Configuration
public class MetaAutoConfiguration {

    @Bean
    public DynamicDao dynamicDao(){
        return new DynamicDaoImpl();
    }

    @Bean
    public DynamicService dynamicService(){
        return new DynamicServiceImpl();
    }
}
