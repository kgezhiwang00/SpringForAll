package com.spring.h2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 16:02
 * @Description:
 */
@RestController
public class IndexController {

    @GetMapping("/")
    String index() {
        return "Spring Boot - Spring Data JPA - H2 Web Console";
    }
}