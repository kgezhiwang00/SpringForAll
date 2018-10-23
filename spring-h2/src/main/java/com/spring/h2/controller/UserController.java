package com.spring.h2.controller;

import com.spring.h2.domain.User;
import com.spring.h2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 16:02
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/getUser")
    String getUser(@RequestParam String id) {
        User user = userService.getUserById(id);
        return user.toString();
    }
}