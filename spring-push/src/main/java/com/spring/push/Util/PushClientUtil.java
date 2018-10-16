package com.spring.push.Util;

import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.model.PushPayload;
import com.spring.push.enums.PlatFormEnum;
import com.spring.push.vo.NotificationRequest;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 09:32
 * @Description: 根据平台类型构建发送请求的客户端
 */
public class PushClientUtil {

    public static PushClient buildPushClient(PlatFormEnum type,String appKey,String secret) {
        PushClient pushClient = new PushClient(secret, appKey);
        return pushClient;

    }

    public static PushPayload buildPushPayload(NotificationRequest request) {
        return null;
    }
}
