package com.spring.push.vo;

import lombok.Data;

/**
 *
 */
@Data
public class MessageInfoVO extends MessageInfo{

    private String apiKey; //应用标识
    private String actionType; //发送时间类型 1：即时，2：定时
    private String broadcastType; //用户范围(广播)类型 全部: 1  定向: 2  分组: 3
    private String deviceIds; //定向发送的设备标识 broadcastType=2时必填，多个用英文逗号分隔
    private String apnsType; //当基于苹果推送时候此参数必填，dev:开发环境，pro:生产环境
    private String adminCode;
    private String keyword;//搜索时的关键字

}
