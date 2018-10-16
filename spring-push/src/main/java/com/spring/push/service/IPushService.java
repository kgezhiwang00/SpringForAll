package com.spring.push.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.spring.push.Handler.NotificationHandler;
import com.spring.push.vo.ApiKeysInfoWithBLOBs;
import com.spring.push.vo.MessageInfoVO;
import com.spring.push.vo.NotificationRequest;
import com.spring.push.vo.NotificationResponse;

import java.util.List;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 10:17
 * @Description:
 */
public interface IPushService {

    /**
     * 消息推送
     * @param notificationHandler
     * @param apiKeysInfoWithBLOBs
     * @param messageInfoVO
     * @param deviceIds
     * @return
     * @throws Exception
     */
    NotificationResponse push(NotificationHandler notificationHandler,
                              ApiKeysInfoWithBLOBs apiKeysInfoWithBLOBs,
                              MessageInfoVO messageInfoVO, List<String> deviceIds) throws Exception;

    /**
     * 获取标签名称列表。
     * @param apiKeysInfoWithBLOBs
     * @return
     * @throws Exception
     */
    List<String> getTags(ApiKeysInfoWithBLOBs apiKeysInfoWithBLOBs) throws Exception;
}
