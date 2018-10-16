package com.spring.push.exception;

import com.spring.push.rest.ResultCode;
import com.spring.push.vo.NotificationResponse;

public class NotificationException extends RuntimeException {
    private int code;
    private String message;

    public NotificationException(int code) {
        this.code = code;
        this.message = this.getDefaultMessage(code);
    }

    public NotificationException(String errorMsg) {
        this.message = errorMsg;
    }

    private String getDefaultMessage(int code) {
        switch(code) {
        case -7:
            this.message = "Tag为空";
            break;
        case -6:
            this.message = "消息内容为空";
            break;
        case -5:
            this.message = "设备ID为空";
            break;
        case -4:
            this.message = "IOS设备部署类型错误";
            break;
        case -3:
            this.message = "IOS设备消息类型错误";
            break;
        case -2:
            this.message = "未知的设备类型";
            break;
        case -1:
            this.message = "第三方平台注册信息错误";
            break;
        case 0:
            this.message = "未知错误";
            break;
        default:
            this.message = "未知错误";
        }

        return this.message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public NotificationResponse buildNotificationResp(NotificationResponse notificationResponse) {
        switch(this.code) {
        case -7:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_TAG_IS_NULL_ERROR);
            break;
        case -6:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_CONTENT_IS_NULL_ERROR);
            break;
        case -5:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_CHANELID_IS_NULL_ERROR);
            break;
        case -4:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_IOS_DEPLOYTYPE_ERROR);
            break;
        case -3:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_MSGTYPE_ERROR);
            break;
        case -2:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_UNKNOWN_DEVICE_ERROR);
            break;
        case -1:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_INVALID_CONFIG_ERROR);
            break;
        case 0:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
            break;
        default:
            notificationResponse.fillResult(ResultCode.NOTIFICATION_OTHER_ERROR);
        }

        return notificationResponse;
    }
}
