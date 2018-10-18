package com.spring.push.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MessageInfo {

    @ApiModelProperty(value = "消息id", required = false)
    private Long id;
    @ApiModelProperty(value = "消息创建时间", required = false)
    private Date createTime;
    @ApiModelProperty(value = "异常信息", required = false)
    private String exception;
    @ApiModelProperty(value = "消息", required = false)
    private String message;
    @ApiModelProperty(value = "消息id", required = false)
    private String messageId;
    @ApiModelProperty(value = "消息类型", required = false)
    private Integer messageType;
    @ApiModelProperty(value = "消息参数", required = false)
    private String params;
    @ApiModelProperty(value = "未知", required = false)
    private String stackTrace;
    @ApiModelProperty(value = "是否成功", required = false)
    private Boolean success;
    @ApiModelProperty(value = "标签", required = false)
    private String tagList;
    @ApiModelProperty(value = "标题", required = false)
    private String title;
    @ApiModelProperty(value = "应用id", required = false)
    private Integer apiKeyId;
    @ApiModelProperty(value = "推送类型", required = false)
    private String sendType;
    @ApiModelProperty(value = "推送范围", required = false)
    private String sendScope;
    @ApiModelProperty(value = "截止天数", required = false)
    private String deadlineDay;
    @ApiModelProperty(value = "截止小时", required = false)
    private String deadlineHour;
    @ApiModelProperty(value = "用户key", required = false)
    private String userKey;
    @ApiModelProperty(value = "任务id", required = false)
    private String jobId;
    @ApiModelProperty(value = "服务名称", required = false)
    private String serviceName;
    @ApiModelProperty(value = "设备id", required = false)
    private String deviceId;
    @ApiModelProperty(value = "组类型", required = false)
    private String groupType;
    @ApiModelProperty(value = "", required = false)
    private String actionTime;
    @ApiModelProperty(value = "", required = false)
    private String code;


}