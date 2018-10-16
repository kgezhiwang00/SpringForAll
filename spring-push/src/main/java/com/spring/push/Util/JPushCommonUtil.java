//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.Util;

import cn.jpush.api.device.DeviceClient;
import cn.jpush.api.push.PushClient;
import com.spring.push.exception.NotificationException;
import com.spring.push.vo.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPushCommonUtil {
    private static Logger logger = LoggerFactory.getLogger(JPushCommonUtil.class);

    public JPushCommonUtil() {
    }

    public static PushClient buildPushClient(NotificationRequest notificationRequest) {
        if (notificationRequest.getConfigMap().get("apiKey") != null && notificationRequest.getConfigMap().get("secretKey") != null) {
            PushClient pushClient = new PushClient(notificationRequest.getConfigMap().get("secretKey").toString(), notificationRequest.getConfigMap().get("apiKey").toString());
            return pushClient;
        } else {
            logger.error("构建极光推送客户端：参数错误 apiKey: {}  secretKey: {}", notificationRequest.getConfigMap().get("apiKey"), notificationRequest.getConfigMap().get("secretKey"));
            throw new NotificationException(-1);
        }
    }

    public static DeviceClient buildDeviceClient(NotificationRequest notificationRequest) {
        if (notificationRequest.getConfigMap().get("apiKey") != null && notificationRequest.getConfigMap().get("secretKey") != null) {
            DeviceClient deviceClient = new DeviceClient(notificationRequest.getConfigMap().get("secretKey").toString(), notificationRequest.getConfigMap().get("apiKey").toString());
            return deviceClient;
        } else {
            logger.error("构建极光推送客户端：参数错误 apiKey: {}  secretKey: {}", notificationRequest.getConfigMap().get("apiKey"), notificationRequest.getConfigMap().get("secretKey"));
            throw new NotificationException(-1);
        }
    }
}
