package com.spring.h2;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 15:58
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.spring.h2","com.jr.basic.meta"})
@EnableSwagger2
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
