package com.spring.push.test;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 10:18
 * @Description:
 */

public class Test {

    private static final String masterSecret = "044f730849d77ef5696451d7";

    private static final String appKey = "7dc0a1e3f82685503d94c09b";

    private static JPushClient jpushClient = null;

    /*
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒
	 */
    private static long timeToLive =  60 * 60 * 24;

    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        ClientConfig config = ClientConfig.getInstance();

        config.setApnsProduction(false); 	// development env
        config.setTimeToLive(timeToLive); // one day
        jpushClient = new JPushClient(masterSecret, appKey, null, config);
        PushPayload payload = buildPushObject_android_tag_alertWithTitle();
        PushResult result = jpushClient.sendPush(payload);
        System.out.println(result.toString());
    }
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android("fasdf", "aadf", null))
                .build();
    }

}
