package com.spring.push.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MessageInfo {

    private Long id;

    private Date createTime;

    private String exception;

    private String message;

    private String messageId;

    private Integer messageType;

    private String params;

    private String stackTrace;

    private Boolean success;

    private String tagList;

    private String title;

    private Integer apiKeyId;

    private String sendType;

    private String sendScope;

    private String deadlineDay;

    private String deadlineHour;

    private String userKey;

    private String jobId;

    private String serviceName;

    private String deviceId;

    private String groupType;

    private String actionTime;

    private String code;


}