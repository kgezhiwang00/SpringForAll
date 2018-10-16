package com.spring.push.service.impl;

import cn.jpush.api.device.TagListResult;

import com.alibaba.fastjson.JSONObject;
import com.spring.push.Handler.NotificationHandler;
import com.spring.push.constant.ApnsType;
import com.spring.push.constant.BroadcastType;
import com.spring.push.constant.NotificationConstant;
import com.spring.push.constant.PushType;
import com.spring.push.service.IPushService;
import com.spring.push.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 极光推送
 * @Author: sunjiang
 * @Date: Created in 15:55 2018/5/23
 * @copyright Navi WeCloud
 */
@Service("jPushService")
@Slf4j
public class JPushService implements IPushService {
    @Override
    public NotificationResponse push(NotificationHandler notificationHandler, ApiKeysInfoWithBLOBs apiKeysInfoWithBLOBs, MessageInfoVO messageInfoVO, List<String> deviceIds) throws Exception {
        log.info("极光推送：pushPlatform:{},pushtype:{}", apiKeysInfoWithBLOBs.getPushplatform(), apiKeysInfoWithBLOBs.getPushtype());
        NotificationRequest notificationRequest = new NotificationRequest();
        if(PushType.IOS.equals(apiKeysInfoWithBLOBs.getPushtype())){ //如果是ios推送 封装ios消息对象
            notificationRequest.setDeviceType(NotificationConstant.DEVICE_TYPE_IOS);
            NotificationIOS notificationIOS = new NotificationIOS();
            notificationIOS.setAlert(messageInfoVO.getTitle());
            notificationIOS.setBadge("1");
            notificationIOS.setSound("msg.mp3");
            notificationRequest.setNotificationIOS(notificationIOS);
            if(ApnsType.DEV.equals(messageInfoVO.getApnsType())){
                notificationRequest.setDeployStatus(1);
            } else {
                notificationRequest.setDeployStatus(2);
            }
            notificationRequest.setMsgType(messageInfoVO.getMessageType());
        } else {
            notificationRequest.setDeviceType(NotificationConstant.DEVICE_TYPE_ANDROID);
            NotificationAndroid notificationAndroid = new NotificationAndroid();
            notificationAndroid.setTitle(messageInfoVO.getTitle());
            notificationAndroid.setRing(true);
            notificationAndroid.setClearable(true);
            notificationAndroid.setVibrate(true);
            notificationRequest.setNotificationAndroid(notificationAndroid);
            notificationRequest.setMsgType(messageInfoVO.getMessageType());
            notificationRequest.setDeployStatus(2);
        }
        Map configMap = new HashMap();
        //configMap.put("apiKey", apiKeysInfoWithBLOBs.getThirdapikey());
        configMap.put("apiKey", apiKeysInfoWithBLOBs.getApiKey());
        configMap.put("secretKey", apiKeysInfoWithBLOBs.getSecretkey());
        notificationRequest.setConfigMap(configMap);
        notificationRequest.setMsgExpires(3600);
        notificationRequest.setMsgType(messageInfoVO.getMessageType());
        if (messageInfoVO.getParams() != null && !StringUtils.isEmpty(messageInfoVO.getParams())) {
            notificationRequest.setCustomContent(JSONObject.parseObject(messageInfoVO.getParams()));
        }
        notificationRequest.setContent(messageInfoVO.getMessage());
        NotificationResponse notificationResponse = new NotificationResponse();
        if(BroadcastType.DEVICES.equals(messageInfoVO.getBroadcastType())){
            notificationRequest.setChannelIdList(deviceIds);
            notificationResponse = notificationHandler.pushMsgToDeviceByList(notificationRequest);
        } else if(BroadcastType.ALL.equals(messageInfoVO.getBroadcastType())){
            notificationRequest.setApiKey(apiKeysInfoWithBLOBs.getApiKey());
            notificationResponse = notificationHandler.pushMsgToAll(notificationRequest);
        } else if(BroadcastType.GROUP.equals(messageInfoVO.getBroadcastType())){
            String tags = messageInfoVO.getTagList();
            if(tags!=null){
                String[] tagArray = tags.split(",");
                for(String tag : tagArray){
                    notificationRequest.setTag(tag);
                    notificationResponse = notificationHandler.pushMsgToTag(notificationRequest);
                }
            }
        }
        return notificationResponse;
    }

    /**
     * 获取第三方标签列表
     * @param apiKeysInfoWithBLOBs
     * @return
     * @throws Exception
     */
    @Override
    public List<String> getTags(ApiKeysInfoWithBLOBs apiKeysInfoWithBLOBs) throws Exception {
        /*NotificationHandler notificationHandler = (NotificationHandler) TspApplicationContext.getBean(apiKeysInfoWithBLOBs.getPushplatform());
        NotificationRequest notificationRequest = new NotificationRequest();
        Map configMap = new HashMap();
        configMap.put("apiKey", apiKeysInfoWithBLOBs.getApiKey());
        configMap.put("secretKey", apiKeysInfoWithBLOBs.getSecretkey());
        notificationRequest.setConfigMap(configMap);
        NotificationResponse notificationResponse = notificationHandler.getTags(notificationRequest);
        Map resultMap = notificationResponse.getResultMap();
        TagListResult tagListResult = (TagListResult)resultMap.get("tagListResult");
        List<String> tags = null;
        if(tagListResult != null ){
            tags  = tagListResult.tags;
        } else {
            tags = new ArrayList<String>();
        }
        return tags;*/
        return null;
    }
}
