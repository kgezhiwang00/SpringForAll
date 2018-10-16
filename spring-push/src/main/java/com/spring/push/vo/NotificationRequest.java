package com.spring.push.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 09:22
 * @Description:
 */
@Data
public class NotificationRequest {

    private String apiKey;
    private Map configMap = new HashMap();
    private String channelId;
    private List channelIdList;
    private String content;
    private JSONObject customContent = new JSONObject();
    private NotificationAndroid notificationAndroid = new NotificationAndroid();
    private NotificationIOS notificationIOS = new NotificationIOS();
    private ApnsConfig apnsConfig = new ApnsConfig();
    private NotificationMqtt notificationMqtt = new NotificationMqtt();
    private Integer msgType;
    private Integer msgExpires;
    private Integer deployStatus;
    private String topicId;
    private Integer deviceType;
    private String tag;

    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("NotificationRequest", this);
        return jsonObject.toJSONString();
    }
}
