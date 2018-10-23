package com.spring.h2.service;

import com.spring.h2.domain.User;

/**
 * @Auther: zcx
 * @Date: 2018/10/22 14:47
 * @Description: 用户service
 */
public interface IUserService {

    public void createUser(User user);

    public User getUserById(String id);
}
