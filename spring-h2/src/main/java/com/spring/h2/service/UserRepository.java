package com.spring.h2.service;

import com.spring.h2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 15:52
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
