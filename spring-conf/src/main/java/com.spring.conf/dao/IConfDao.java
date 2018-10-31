package com.spring.conf.dao;

import com.spring.conf.domain.ConfInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 10:12
 * @Description:
 */

@Component
public interface IConfDao {

    void updateServerInfo(ConfInfo confInfo);

    void insertServerInfo(ConfInfo confInfo);

    ConfInfo getServerInfo(@Param("id") String id,@Param("name") String name);
}
