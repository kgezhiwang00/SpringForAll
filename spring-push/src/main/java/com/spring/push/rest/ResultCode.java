//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spring.push.rest;

public enum ResultCode implements ReturnResult {
    SUCCESS(200, "操作成功", 200),
    PARAM_ERROR(100400, "请求参数错误!", 400),
    SERVER_ERROR(100500, "服务器内部错误!", 500),
    TCP_GATEWAY_CHANNEL_ONLINE(101001, "终端在线", 200),
    TCP_GATEWAY_CHANNEL_OFFLINE(101002, "终端不在线", 500),
    TCP_GATEWAY_REMOVE_CHANNEL_FAILURE(101003, "关闭tid对应链路失败", 500),
    NOTIFICATION_OTHER_ERROR(103000, "其他错误", 500),
    NOTIFICATION_INVALID_CONFIG_ERROR(103001, "第三方平台注册信息错误", 500),
    NOTIFICATION_UNKNOWN_DEVICE_ERROR(103002, "未知的设备类型", 500),
    NOTIFICATION_MSGTYPE_ERROR(103003, "消息类型错误", 500),
    NOTIFICATION_IOS_DEPLOYTYPE_ERROR(103004, "IOS设备部署类型错误", 500),
    NOTIFICATION_CHANELID_IS_NULL_ERROR(103005, "设备ID为空", 500),
    NOTIFICATION_CONTENT_IS_NULL_ERROR(103006, "消息内容为空", 500),
    NOTIFICATION_TAG_IS_NULL_ERROR(103007, "Tag为空", 500),
    NOTIFICATION_METHOD_INVALID_ERROR(10308, "该方法不可用", 500),
    JOB_ADMIN_INVOKE_ERROR(104000, "任务管理平台异常", 500),
    JOB_NOT_EXIST_ERROR(104001, "任务不存在错误", 500),
    JOB_NOT_NEED_SUSPEND_ERROR(104002, "任务已是暂停状态，不需要暂停", 500),
    JOB_NOT_NEED_RECOVERY_ERROR(104003, "任务已是暂停状态，不需要暂停", 500),
    JOB_CLIENT_INVOKE_ERROR(104004, "调用jobclient失败", 500),
    JOB_CLIENT_PARAM_ERROR(104005, "调用jobclient参数错误", 500),
    JOB_TYPE_ERROR(104006, "任务类型错误", 500),
    JOB_LTS_TRACKER_NOT_FOUND_ERROR(104007, "没有找到JobTracker节点", 500),
    JOB_LTS_SUBMIT_FAILED_AND_SAVE_FOR_LATER_ERROR(104008, "提交失败并且写入文件", 500),
    JOB_LTS_REQUEST_FILED_CHECK_ERROR(104009, "请求参数检查失败", 500),
    JOB_LTS_SUBMIT_TOO_BUSY_AND_SAVE_FOR_LATER_ERROR(104010, "提交太块", 500),
    JOB_LTS_SYSTEM_ERROR(104011, "LTS系统错误", 500),
    JOB_OTHER_ERROR(104999, "其他错误", 500);

    private int code;
    private int httpCode;
    private String message;

    private ResultCode(int code, String message, int httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    public static String getMessage(int code) {
        ResultCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultCode resultCode = var1[var3];
            if (resultCode.code() == code) {
                return resultCode.message();
            }
        }

        return null;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public int httpCode() {
        return this.httpCode;
    }
}
