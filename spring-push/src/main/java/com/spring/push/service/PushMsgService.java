//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.device.DeviceClient;
import cn.jpush.api.device.TagListResult;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

import com.spring.push.Util.JPushCommonUtil;
import com.spring.push.exception.NotificationException;
import com.spring.push.rest.ResultCode;
import com.spring.push.vo.NotificationRequest;
import com.spring.push.vo.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushMsgService {
    private static Logger logger = LoggerFactory.getLogger(PushMsgService.class);

    public PushMsgService() {
    }

    public static NotificationResponse pushMsgToSingleDevice(NotificationRequest notificationRequest) throws Exception {
        logger.debug("推送消息到单一设备：开始调用");
        NotificationResponse notificationResponse = new NotificationResponse();
        PushClient jpushClient = null;

        try {
            jpushClient = JPushCommonUtil.buildPushClient(notificationRequest);
            logger.debug("推送消息到单一设备：构建极光客户端完成");
        } catch (NotificationException var8) {
            logger.error("推送消息到单一设备：构建极光客户端失败");
            var8.buildNotificationResp(notificationResponse);
            return notificationResponse;
        }

        if (notificationRequest != null && notificationRequest.getChannelId() != null) {
            Notification notification = null;
            Message message = null;
            PushPayload pushPayload = null;
            if (notificationRequest.getDeviceType() != null && notificationRequest.getDeviceType().intValue() == 3) {
                logger.debug("推送消息到单一设备：构建消息体，当前是Android设备");
                if (notificationRequest.getMsgType().intValue() == 1) {
                    notification = Notification.android(notificationRequest.getContent(), notificationRequest.getNotificationAndroid().getTitle(), (Map)null);
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.registrationId(new String[]{notificationRequest.getChannelId()})).setNotification(notification).build();
                } else {
                    message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.registrationId(new String[]{notificationRequest.getChannelId()})).setMessage(message).build();
                }
            } else {
                if (notificationRequest.getDeviceType() == null || notificationRequest.getDeviceType().intValue() != 4) {
                    logger.error("推送消息到单一设备：构建消息体,设备类型错误");
                    notificationResponse.fillResult(ResultCode.NOTIFICATION_UNKNOWN_DEVICE_ERROR);
                    return notificationResponse;
                }

                if (notificationRequest.getMsgType().intValue() == 1) {
                    logger.debug("推送消息到单一设备：构建消息体，当前是IOS设备");
                    notification = Notification.ios(notificationRequest.getContent(), (Map)null);
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.registrationId(new String[]{notificationRequest.getChannelId()})).setNotification(notification).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).build();
                } else {
                    message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.registrationId(new String[]{notificationRequest.getChannelId()})).setMessage(message).build();
                }
            }

            logger.debug("推送消息到单一设备：构建极光对象完成");
            PushResult result = jpushClient.sendPush(pushPayload);
            if (result != null && result.isResultOK()) {
                logger.info("推送消息到单一设备：推送成功 result=" + result.toString());
                notificationResponse.fillResult(ResultCode.SUCCESS);
                Map resultMap = new HashMap();
                resultMap.put("msgId", result.msg_id);
                resultMap.put("sendno", result.sendno);
                notificationResponse.setResultMap(resultMap);
            } else {
                notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
                notificationResponse.setMessage(result.toString());
                logger.error("推送消息到单一设备：推送失败 result=" + result.toString());
            }

            return notificationResponse;
        } else {
            logger.error("推送消息到单一设备：构建配置对象，单一设备推送设备为空");
            notificationResponse.fillResult(ResultCode.NOTIFICATION_CHANELID_IS_NULL_ERROR);
            return notificationResponse;
        }
    }

    public static NotificationResponse pushMsgToAll(NotificationRequest notificationRequest) throws Exception {
        logger.debug("推送消息给所有消息：开始调用");
        NotificationResponse notificationResponse = new NotificationResponse();
        PushClient jpushClient = null;

        try {
            jpushClient = JPushCommonUtil.buildPushClient(notificationRequest);
            logger.debug("推送消息给所有消息：构建极光客户端完成");
        } catch (NotificationException var8) {
            logger.error("推送消息给所有消息：构建极光客户端失败");
            var8.buildNotificationResp(notificationResponse);
            return notificationResponse;
        }

        PushPayload pushPayload = null;
        Notification notification = null;
        Message message = null;
        if (notificationRequest.getMsgType().intValue() == 1) {
            logger.debug("推送消息给所有消息：构建消息体完成");
            notification = Notification.newBuilder().setAlert(notificationRequest.getContent()).build();
            pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all()).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setNotification(notification).build();
        } else {
            message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
            pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all()).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setMessage(message).build();
        }

        logger.debug("推送消息给所有消息：构建传递参数完成");
        PushResult result = jpushClient.sendPush(pushPayload);
        if (result != null && result.isResultOK()) {
            logger.info("推送消息给所有消息：推送成功 result=" + result.toString());
            notificationResponse.fillResult(ResultCode.SUCCESS);
            Map resultMap = new HashMap();
            resultMap.put("msgId", result.msg_id);
            resultMap.put("sendno", result.sendno);
            notificationResponse.setResultMap(resultMap);
        } else {
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
            notificationResponse.setMessage(result.toString());
            logger.error("推送消息给所有消息：推送失败 result=" + result.toString());
        }

        return notificationResponse;
    }

    public static NotificationResponse pushMsgToTag(NotificationRequest notificationRequest) throws Exception {
        logger.debug("发送消息给指定Tag：开始调用");
        NotificationResponse notificationResponse = new NotificationResponse();
        PushClient jpushClient = null;

        try {
            jpushClient = JPushCommonUtil.buildPushClient(notificationRequest);
            logger.debug("发送消息给指定Tag：构建极光客户端完成");
        } catch (NotificationException var8) {
            logger.error("发送消息给指定Tag：构建极光客户端失败");
            var8.buildNotificationResp(notificationResponse);
            return notificationResponse;
        }

        PushPayload pushPayload = null;
        Notification notification = null;
        Message message = null;
        if (notificationRequest.getDeviceType() != null && notificationRequest.getDeviceType().intValue() == 3) {
            if (notificationRequest.getMsgType().intValue() == 1) {
                notification = Notification.newBuilder().setAlert(notificationRequest.getContent()).build();
                logger.debug("发送消息给指定Tag：构建消息体完成");
                pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(new String[]{notificationRequest.getTag()})).setNotification(notification).build();
                logger.debug("发送消息给指定Tag：构建传递参数完成");
            } else {
                message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(new String[]{notificationRequest.getTag()})).setMessage(message).build();
            }
        } else {
            if (notificationRequest.getDeviceType() == null || notificationRequest.getDeviceType().intValue() != 4) {
                logger.error("推送消息到单一设备：构建消息体,设备类型错误");
                notificationResponse.fillResult(ResultCode.NOTIFICATION_UNKNOWN_DEVICE_ERROR);
                return notificationResponse;
            }

            if (notificationRequest.getMsgType().intValue() == 1) {
                notification = Notification.newBuilder().setAlert(notificationRequest.getContent()).build();
                logger.debug("发送消息给指定Tag：构建消息体完成");
                pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag(new String[]{notificationRequest.getTag()})).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setNotification(notification).build();
                logger.debug("发送消息给指定Tag：构建传递参数完成");
            } else {
                message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag(new String[]{notificationRequest.getTag()})).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setMessage(message).build();
            }
        }

        PushResult result = jpushClient.sendPush(pushPayload);
        if (result != null && result.isResultOK()) {
            logger.info("发送消息给指定Tag：推送成功 result=" + result.toString());
            notificationResponse.fillResult(ResultCode.SUCCESS);
            Map resultMap = new HashMap();
            resultMap.put("msgId", result.msg_id);
            resultMap.put("sendno", result.sendno);
            notificationResponse.setResultMap(resultMap);
        } else {
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
            notificationResponse.setMessage(result.toString());
            logger.error("发送消息给指定Tag：推送失败 result=" + result.toString());
        }

        return notificationResponse;
    }

    public static NotificationResponse getTags(NotificationRequest notificationRequest) {
        logger.info("获取当前所有Tag标签：开始调用");
        NotificationResponse notificationResponse = new NotificationResponse();
        DeviceClient deviceClient = null;

        try {
            deviceClient = JPushCommonUtil.buildDeviceClient(notificationRequest);
            logger.debug("获取当前所有Tag标签：构建极光客户端完成");
        } catch (NotificationException var6) {
            logger.error("获取当前所有Tag标签：构建极光客户端失败");
            var6.buildNotificationResp(notificationResponse);
            return notificationResponse;
        }

        try {
            TagListResult tagListResult = deviceClient.getTagList();
            notificationResponse.fillResult(ResultCode.SUCCESS);
            notificationResponse.getResultMap().put("tagListResult", tagListResult.tags);
            logger.info("获取当前所有Tag标签：调用成功，返回结果 >>" + tagListResult.toString());
        } catch (APIConnectionException var4) {
            logger.error("获取当前所有Tag标签：获取标签异常" + var4);
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
        } catch (APIRequestException var5) {
            logger.error("获取当前所有Tag标签：获取标签异常" + var5);
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
        }

        return notificationResponse;
    }

    public static NotificationResponse pushMsgToDeviceByList(NotificationRequest notificationRequest) throws Exception {
        logger.debug("推送消息到批量设备：开始调用");
        NotificationResponse notificationResponse = new NotificationResponse();
        PushClient jpushClient = null;

        try {
            jpushClient = JPushCommonUtil.buildPushClient(notificationRequest);
            logger.debug("推送消息到批量设备：构建极光客户端完成");
        } catch (NotificationException var8) {
            logger.error("推送消息到批量设备：构建极光客户端失败");
            var8.buildNotificationResp(notificationResponse);
            return notificationResponse;
        }

        if (notificationRequest != null && notificationRequest.getChannelIdList() != null) {
            Notification notification = null;
            PushPayload pushPayload = null;
            Message message = null;
            if (notificationRequest.getDeviceType() != null && notificationRequest.getDeviceType().intValue() == 3) {
                logger.debug("推送消息到批量设备：构建消息体，当前是Android设备");
                if (notificationRequest.getMsgType().intValue() == 1) {
                    notification = Notification.android(notificationRequest.getContent(), notificationRequest.getNotificationAndroid().getTitle(), (Map)null);
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.registrationId(notificationRequest.getChannelIdList())).setNotification(notification).build();
                } else {
                    message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.registrationId(notificationRequest.getChannelIdList())).setMessage(message).build();
                }
            } else {
                if (notificationRequest.getDeviceType() == null || notificationRequest.getDeviceType().intValue() != 4) {
                    logger.error("推送消息到批量设备：构建消息体,设备类型错误");
                    notificationResponse.fillResult(ResultCode.NOTIFICATION_UNKNOWN_DEVICE_ERROR);
                    return notificationResponse;
                }

                logger.debug("推送消息到批量设备：构建消息体，当前是IOS设备");
                if (notificationRequest.getMsgType().intValue() == 1) {
                    logger.debug("推送消息到单一设备：构建消息体，当前是IOS设备");
                    notification = Notification.ios(notificationRequest.getContent(), (Map)null);
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.registrationId(notificationRequest.getChannelIdList())).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setNotification(notification).build();
                } else {
                    message = Message.newBuilder().setMsgContent(notificationRequest.getContent()).build();
                    pushPayload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.registrationId(notificationRequest.getChannelIdList())).setOptions(Options.newBuilder().setApnsProduction(notificationRequest.getDeployStatus().intValue() == 2).build()).setMessage(message).build();
                }
            }

            logger.debug("推送消息到批量设备：构建极光对象完成");
            PushResult result = jpushClient.sendPush(pushPayload);
            if (result != null && result.isResultOK()) {
                logger.info("推送消息到批量设备：推送成功 result=" + result.toString());
                notificationResponse.fillResult(ResultCode.SUCCESS);
                Map resultMap = new HashMap();
                resultMap.put("msgId", result.msg_id);
                resultMap.put("sendno", result.sendno);
                notificationResponse.setResultMap(resultMap);
            } else {
                notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
                notificationResponse.setMessage(result.toString());
                logger.error("推送消息到批量设备：推送失败 result=" + result.toString());
            }

            return notificationResponse;
        } else {
            logger.error("推送消息到批量设备：构建配置对象，批量推送设备为空");
            notificationResponse.fillResult(ResultCode.NOTIFICATION_CHANELID_IS_NULL_ERROR);
            return notificationResponse;
        }
    }
}
