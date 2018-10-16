//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.Handler;

import com.spring.push.vo.NotificationRequest;
import com.spring.push.vo.NotificationResponse;

public interface NotificationHandler {
    NotificationResponse pushMsgToSingleDevice(NotificationRequest var1) throws Exception;

    NotificationResponse pushMsgToAll(NotificationRequest var1) throws Exception;

    NotificationResponse pushMsgToTag(NotificationRequest var1) throws Exception;

    NotificationResponse pushMsgToDeviceByList(NotificationRequest var1) throws Exception;

    NotificationResponse getTags(NotificationRequest var1) throws Exception;
}