package com.spring.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.push.Handler.NotificationHandler;
import com.spring.push.Util.DateUtil;
import com.spring.push.Util.TspApplicationContext;
import com.spring.push.constant.BroadcastType;
import com.spring.push.constant.PushPlatformContant;
import com.spring.push.constant.PushType;
import com.spring.push.rest.CommonResult;
import com.spring.push.rest.ResultCode;
import com.spring.push.service.IPushService;
import com.spring.push.vo.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 10:17
 * @Description:
 */
@RestController
@Data
@Slf4j
public class PushController {
    private NotificationHandler notificationHandler;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private Map<String, IPushService> pushServiceMap;
    /**
     * 极光推送
     * @param messageInfoVO
     * @return
     */
    @RequestMapping(value = "/pushMsg/send", method = {RequestMethod.POST, RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult sendMessage(@ModelAttribute @Valid MessageInfoVO messageInfoVO) {
        //发送消息
        log.info("发送消息：apiKey:{},title:{},message:{}", messageInfoVO.getApiKey(), messageInfoVO.getTitle(), messageInfoVO.getMessage());

        NotificationResponse notificationResponse = null;
        ApiKeysInfoWithBLOBs apiKeysInfo = new ApiKeysInfoWithBLOBs();
        apiKeysInfo.setApiKey("7dc0a1e3f82685503d94c09b");
        apiKeysInfo.setSecretkey("044f730849d77ef5696451d7");
        apiKeysInfo.setPushtype(PushType.ANDROID);
        apiKeysInfo.setPushplatform(PushPlatformContant.JPUSH);
        apiKeysInfo.setId(123);
        CommonResult commonResult = new CommonResult();

        List<Integer> devicePks = new LinkedList<Integer>();
        notificationResponse = null;
        try {
            List<String> clientIds = new ArrayList<String>();
            messageInfoVO.setApiKeyId(apiKeysInfo.getId());//设置APIKEYID
            messageInfoVO.setUserKey(messageInfoVO.getApiKey());
            String messageId = String.valueOf(RandomUtils.nextInt());
            messageInfoVO.setMessageId(messageId);
            messageInfoVO.setSendScope(messageInfoVO.getBroadcastType());
            messageInfoVO.setSendType(messageInfoVO.getActionType());
            messageInfoVO.setDeviceId(messageInfoVO.getDeviceIds());
            //判断是否定时
            notificationHandler = this.getNotificationHandler(apiKeysInfo.getPushplatform());
            IPushService pushService = null;
            if(PushPlatformContant.BAIDU.equals(apiKeysInfo.getPushplatform())){
                pushService = pushServiceMap.get("baiduPushService");
            } else if(PushPlatformContant.JPUSH.equals(apiKeysInfo.getPushplatform())){
                pushService = this.pushServiceMap.get("jPushService");
            } else if(PushPlatformContant.IXINTUIV2.equals(apiKeysInfo.getPushplatform())
                    || PushPlatformContant.IXINTUIV1.equals(apiKeysInfo.getPushplatform())){
                pushService = this.pushServiceMap.get("ixintuiPushService");
            } else if(PushPlatformContant.XINGE.equals(apiKeysInfo.getPushplatform())){
                pushService = this.pushServiceMap.get("xingePushService");
            }


            if(BroadcastType.DEVICES.equals(messageInfoVO.getBroadcastType())){
                String deviceIds = messageInfoVO.getDeviceIds();
                String[] deviceIdArray = deviceIds.split(",");
                clientIds = Arrays.asList(deviceIdArray);
            }
            notificationResponse = pushService.push(notificationHandler, apiKeysInfo, messageInfoVO, clientIds);
            JSONObject dataJson = new JSONObject();
            dataJson.put("messageId", messageId);
            commonResult.setCode(ResultCode.SUCCESS.code());
            commonResult.setHttpCode(ResultCode.SUCCESS.httpCode());
            commonResult.setMessage(ResultCode.SUCCESS.message());
            commonResult.setData(dataJson);
        } catch (Exception e) {
            log.error("消息推送异常：", e.getMessage());
            commonResult.setCode(ResultCode.SERVER_ERROR.code());
            commonResult.setHttpCode(ResultCode.SERVER_ERROR.httpCode());
            commonResult.setMessage(e.getMessage());
            return commonResult;
        }
        return commonResult;
    }
    private NotificationHandler getNotificationHandler(String pushPlatform) {
        return (NotificationHandler) TspApplicationContext.getBean(pushPlatform);
    }


    /**
     * MQTT 推送
     * @param messageInfoVO
     * @return
     */
    @RequestMapping(value = "/pushMsg/api/send", method = {RequestMethod.POST, RequestMethod.GET},produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public CommonResult sendMessages(@RequestBody @Valid MessageInfoVO messageInfoVO) {
        //发送消息
        log.info("发送消息：apiKey:{},title:{},message:{}", messageInfoVO.getApiKey(), messageInfoVO.getTitle(), messageInfoVO.getMessage());
        JSONObject dataJson = new JSONObject();
        NotificationResponse notificationResponse = null;
        CommonResult commonResult = new CommonResult();
        ApiKeysInfoWithBLOBs apiKeysInfo = new ApiKeysInfoWithBLOBs();
        apiKeysInfo.setApiKey("7dc0a1e3f82685503d94c09b");
        apiKeysInfo.setSecretkey("044f730849d77ef5696451d7");
        apiKeysInfo.setPushtype(PushType.ANDROID);
        apiKeysInfo.setPushplatform(PushPlatformContant.JPUSH);
        apiKeysInfo.setId(123);

        List<Integer> devicePks = new LinkedList<Integer>();
        notificationResponse = null;
        try {
            List<String> clientIds = this.getDeviceIdsByRequest(apiKeysInfo, messageInfoVO, devicePks);

            messageInfoVO.setApiKeyId(apiKeysInfo.getId());//设置APIKEYID
            messageInfoVO.setUserKey(messageInfoVO.getApiKey());
            String messageId = "13123";
            messageInfoVO.setMessageId(messageId);
            messageInfoVO.setSendScope(messageInfoVO.getBroadcastType());
            messageInfoVO.setSendType(messageInfoVO.getActionType());
            messageInfoVO.setDeviceId(messageInfoVO.getDeviceIds());
            messageInfoVO.setTagList(messageInfoVO.getTagList());
            messageInfoVO.setCode(messageInfoVO.getAdminCode());
            //判断是否定时
            notificationHandler = this.getNotificationHandler(PushPlatformContant.MQTT);
            IPushService pushService = pushServiceMap.get("mqttPushService");
            notificationResponse = pushService.push(notificationHandler, apiKeysInfo, messageInfoVO, clientIds);
            dataJson.put("messageId", messageId);
            commonResult.setCode(ResultCode.SUCCESS.code());
            commonResult.setHttpCode(ResultCode.SUCCESS.httpCode());
            commonResult.setMessage(ResultCode.SUCCESS.message());
            commonResult.setData(dataJson);
        } catch (Exception e) {
            log.error("消息推送异常：", e.getMessage());
            commonResult.setCode(ResultCode.SERVER_ERROR.code());
            commonResult.setHttpCode(ResultCode.SERVER_ERROR.httpCode());
            commonResult.setMessage(ResultCode.SERVER_ERROR.message());
            return commonResult;
        }
        return commonResult;
    }
    private List<String> getDeviceIdsByRequest(ApiKeysInfoWithBLOBs apiKeysInfoWithBLOBs,
                                               MessageInfoVO messageInfoVO,
                                               List<Integer> devicePk) throws ParseException {
        List<String> deviceIds = new ArrayList<String>();
        Date date = new Date();
        String stringDay = DateUtil.formatCurrentByDay(date);
        //全部推送
        if (BroadcastType.ALL.equals(messageInfoVO.getBroadcastType())) {
            List<DeviceInfo> deviceInfoList = null;
            for (DeviceInfo deviceInfo : deviceInfoList) {
                if ((deviceInfo.getFazeStatus() == null || deviceInfo.getFazeStatus()) && (deviceInfo.getFazeStartTime() != null && deviceInfo.getFazeEndTime() != null)) {
                    //过滤那些开启了免打扰时段的设备
                    long startTime = convertTime(stringDay + " " + deviceInfo.getFazeStartTime());
                    long endTime = convertTime(stringDay + " " + deviceInfo.getFazeEndTime());
                    if (date.getTime() >= startTime && date.getTime() <= endTime) {
                        continue;
                    } else {
                        deviceIds.add(deviceInfo.getClientId());
                    }
                } else {
                    deviceIds.add(deviceInfo.getClientId());
                }

                devicePk.add(deviceInfo.getId());
            }
            //定向设备推送
        } else if (BroadcastType.DEVICES.equals(messageInfoVO.getBroadcastType())) {
            String deviceIdsString = messageInfoVO.getDeviceIds();
            String[] deviceArray = deviceIdsString.split(",");
            deviceIds = Arrays.asList(deviceArray);
            List<DeviceInfo> deviceInfoList = null;
            deviceIds = new ArrayList<String>();
            for (DeviceInfo deviceInfo : deviceInfoList) {
                if ((deviceInfo.getFazeStatus() == null || deviceInfo.getFazeStatus()) && (deviceInfo.getFazeStartTime() != null && deviceInfo.getFazeEndTime() != null)) {
                    //过滤那些开启了免打扰时段的设备
                    long startTime = convertTime(stringDay + " " + deviceInfo.getFazeStartTime());
                    long endTime = convertTime(stringDay + " " + deviceInfo.getFazeEndTime());
                    if (date.getTime() >= startTime && date.getTime() <= endTime) {
                        continue;
                    } else {
                        deviceIds.add(deviceInfo.getClientId());
                    }
                } else {
                    deviceIds.add(deviceInfo.getClientId());
                }
                devicePk.add(deviceInfo.getId());

            }
        } else if (BroadcastType.GROUP.equals(messageInfoVO.getBroadcastType())) { //按照标签分组推送
            // 按照城市代码发送或者按照分组发送
            List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
            if("1".equals(messageInfoVO.getGroupType())){
                String tags = messageInfoVO.getTagList();
                String[] tagsArray = tags.split(",");
                List<String> tagNames = Arrays.asList(tagsArray);
                deviceInfoList = null;
            } else {
                String[] codeArray = messageInfoVO.getAdminCode().split(",");
                for(String adminCode : codeArray){
                    List<DeviceInfo> deviceInfos = null;
                    deviceInfoList.addAll(deviceInfos);
                }
            }
            for (DeviceInfo deviceInfo : deviceInfoList) {
                if ((deviceInfo.getFazeStatus() == null || deviceInfo.getFazeStatus()) && (deviceInfo.getFazeStartTime() != null && deviceInfo.getFazeEndTime() != null)) {
                    //过滤那些开启了免打扰时段的设备
                    long startTime = convertTime(stringDay + " " + deviceInfo.getFazeStartTime());
                    long endTime = convertTime(stringDay + " " + deviceInfo.getFazeEndTime());
                    if (date.getTime() >= startTime && date.getTime() <= endTime) {
                        continue;
                    } else {
                        deviceIds.add(deviceInfo.getClientId());
                    }
                } else {
                    deviceIds.add(deviceInfo.getClientId());
                }
                devicePk.add(deviceInfo.getId());
            }
        }
        return deviceIds;
    }
    private long convertTime(String dateString) throws ParseException {
        Date date = formatter.parse(dateString);
        return date.getTime();
    }


}
