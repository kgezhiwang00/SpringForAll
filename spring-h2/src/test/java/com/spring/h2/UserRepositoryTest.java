package com.spring.h2;

import com.spring.h2.domain.User;
import com.spring.h2.service.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 15:54
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() throws Exception {
        User user = new User();
        user.setName("郑龙飞");
        user.setUrl("http://merryyou.cn");
        User result = userRepository.save(user);
        log.info(result.toString());
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findOneTest() throws Exception{
        User user = userRepository.findOne(2L);
        log.info(user.toString());
        Assert.assertNotNull(user);
        Assert.assertTrue(2l==user.getId());
    }

}
