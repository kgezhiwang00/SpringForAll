package com.spring.push.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
@ApiModel(value = "MessageInfoVO", description = "推送消息请求参数")
public class MessageInfoVO extends MessageInfo{

    @ApiModelProperty(value = "应用标识", required = false)
    private String apiKey; //应用标识
    @ApiModelProperty(value = "发送时间类型 1：即时，2：定时", required = false)
    private String actionType; //发送时间类型 1：即时，2：定时
    @ApiModelProperty(value = "用户范围(广播)类型 全部: 1  定向: 2  分组: 3", required = false)
    private String broadcastType; //用户范围(广播)类型 全部: 1  定向: 2  分组: 3
    @ApiModelProperty(value = "定向发送的设备标识 broadcastType=2时必填，多个用英文逗号分隔", required = false)
    private String deviceIds; //定向发送的设备标识 broadcastType=2时必填，多个用英文逗号分隔
    @ApiModelProperty(value = "当基于苹果推送时候此参数必填，dev:开发环境，pro:生产环境", required = false)
    private String apnsType; //当基于苹果推送时候此参数必填，dev:开发环境，pro:生产环境
    @ApiModelProperty(value = "", required = false)
    private String adminCode;
    @ApiModelProperty(value = "搜索时的关键字", required = false)
    private String keyword;//搜索时的关键字

}
