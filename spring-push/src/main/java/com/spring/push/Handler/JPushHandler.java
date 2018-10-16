package com.spring.push.Handler;

import com.spring.push.rest.ResultCode;
import com.spring.push.service.PushMsgService;
import com.spring.push.vo.NotificationRequest;
import com.spring.push.vo.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 09:23
 * @Description:
 */
@Component
public class JPushHandler implements NotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(JPushHandler.class);

    public JPushHandler() {
    }

    @ConditionalOnMissingBean
    public NotificationResponse pushMsgToSingleDevice(NotificationRequest notificationRequest) throws Exception {
        if (notificationRequest == null) {
            logger.error("【极光推送】推送消息到单一设备：notificationRequest 为空");
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else {
            return PushMsgService.pushMsgToSingleDevice(notificationRequest);
        }
    }

    @ConditionalOnMissingBean
    public NotificationResponse pushMsgToAll(NotificationRequest notificationRequest) throws Exception {
        if (notificationRequest == null) {
            logger.error("【极光推送】推送消息到所有设备：notificationRequest 为空");
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else {
            return PushMsgService.pushMsgToAll(notificationRequest);
        }
    }

    @ConditionalOnMissingBean
    public NotificationResponse pushMsgToTag(NotificationRequest notificationRequest) throws Exception {
        if (notificationRequest == null) {
            logger.error("【极光推送】推送消息到指定Tag设备：notificationRequest 为空");
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else {
            return PushMsgService.pushMsgToTag(notificationRequest);
        }
    }

    @ConditionalOnMissingBean
    public NotificationResponse pushMsgToDeviceByList(NotificationRequest notificationRequest) throws Exception {
        NotificationResponse notificationResponse = new NotificationResponse();
        if (notificationRequest == null) {
            logger.error("【极光推送】推送消息到批量设备：notificationRequest 为空");
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else if (notificationRequest.getChannelIdList() == null) {
            logger.error("【极光推送】推送消息到批量设备：ChannelIdList 为空");
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else {
            List list = new ArrayList();
            list.addAll(notificationRequest.getChannelIdList());

            while(!list.isEmpty()) {
                if (notificationRequest.getChannelIdList().size() <= 1000) {
                    notificationResponse = PushMsgService.pushMsgToDeviceByList(notificationRequest);
                    break;
                }

                notificationRequest.getChannelIdList().add(list.subList(0, 999));
                PushMsgService.pushMsgToDeviceByList(notificationRequest);
                list.removeAll(notificationRequest.getChannelIdList());
            }

            notificationResponse.fillResult(ResultCode.SUCCESS);
            return notificationResponse;
        }
    }

    @ConditionalOnMissingBean
    public NotificationResponse getTags(NotificationRequest notificationRequest) throws Exception {
        if (notificationRequest == null) {
            logger.error("【极光推送】获取当前所有的Tag标签：notificationRequest 为空");
            NotificationResponse notificationResponse = new NotificationResponse();
            notificationResponse.fillResult(ResultCode.PARAM_ERROR);
            return notificationResponse;
        } else {
            return PushMsgService.getTags(notificationRequest);
        }
    }
}
