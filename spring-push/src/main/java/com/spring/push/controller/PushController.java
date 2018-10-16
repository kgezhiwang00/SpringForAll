package com.spring.push.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.push.Handler.NotificationHandler;
import com.spring.push.Util.TspApplicationContext;
import com.spring.push.constant.BroadcastType;
import com.spring.push.constant.PushPlatformContant;
import com.spring.push.constant.PushType;
import com.spring.push.rest.CommonResult;
import com.spring.push.rest.ResultCode;
import com.spring.push.service.IPushService;
import com.spring.push.vo.ApiKeysInfoWithBLOBs;
import com.spring.push.vo.MessageInfoVO;
import com.spring.push.vo.NotificationResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
                pushService = this.getPushService("baiduPushService");
            } else if(PushPlatformContant.JPUSH.equals(apiKeysInfo.getPushplatform())){
                pushService = this.getPushService("jPushService");
            } else if(PushPlatformContant.IXINTUIV2.equals(apiKeysInfo.getPushplatform())
                    || PushPlatformContant.IXINTUIV1.equals(apiKeysInfo.getPushplatform())){
                pushService = this.getPushService("ixintuiPushService");
            } else if(PushPlatformContant.XINGE.equals(apiKeysInfo.getPushplatform())){
                pushService = this.getPushService("xingePushService");
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
    private IPushService getPushService(String pushName) {
        return (IPushService) TspApplicationContext.getBean(pushName);
    }
}
