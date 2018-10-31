package com.spring.conf.service.impl;

import com.spring.conf.dao.IConfDao;
import com.spring.conf.domain.ConfInfo;
import com.spring.conf.service.IConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: zcx
 * @Date: 2018/10/29 10:11
 * @Description:
 */
@Service
public class ConfServiceImpl implements IConfService {

    @Autowired
    private IConfDao confDao;

    @Override
    public void addServerInfo(ConfInfo confInfo) {

        ConfInfo newConfInfo = new ConfInfo();
        // 判断是否已经存在此confInfo
        ConfInfo oldConf = getServerInfo(confInfo.getId(),confInfo.getName());

        if(oldConf == null){
            confDao.insertServerInfo(confInfo);
        }else {
            newConfInfo = ConfInfo.getNewConfInfo(oldConf,confInfo);
            confDao.updateServerInfo(newConfInfo);
        }
    }

    @Override
    public ConfInfo getServerInfo(String id,String name) {
        return confDao.getServerInfo(id,name);
    }

    private void updateServerInfo(ConfInfo confInfo){
        confDao.updateServerInfo(confInfo);
    }
}
