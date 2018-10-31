package com.spring.conf.service;

import com.spring.conf.domain.ConfInfo; /**
 * @Auther: zcx
 * @Date: 2018/10/29 09:11
 * @Description:
 */
public interface IConfService {

    void addServerInfo(ConfInfo confInfo);

    ConfInfo getServerInfo(String id,String name);
}
