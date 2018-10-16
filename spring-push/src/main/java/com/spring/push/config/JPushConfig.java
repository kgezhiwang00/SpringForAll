package com.spring.push.config;

import com.spring.push.Handler.JPushHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 14:17
 * @Description:
 */
@Configuration
public class JPushConfig {
    public JPushConfig() {
    }

    @Bean(name = {"jPushHandler"})
    public JPushHandler registerJPushHandler() {
        return new JPushHandler();
    }
}
