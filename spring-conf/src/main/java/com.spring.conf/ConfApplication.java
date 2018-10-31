package com.spring.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 08:51
 * @Description:
 */
@SpringBootApplication
@EnableSwagger2
public class ConfApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfApplication.class, args);

    }
}
