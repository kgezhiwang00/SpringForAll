//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class NotificationMqtt {
    private int messageType;
    private String messageId;
    private String message;
    private String title;
    private JSONObject params;


}
