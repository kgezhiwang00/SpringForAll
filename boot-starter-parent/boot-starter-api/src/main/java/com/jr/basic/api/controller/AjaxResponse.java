//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class AjaxResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int SUCCESS_STATUS = 200;
    public static final String MESSAGE = "message";
    private int status;
    private String code;
    private String message;
    private String forwardUrl;
    private String confirmMsg;
    private Map<String, Object> datas;

    public AjaxResponse() {
        this.datas = new HashMap();
    }

    public AjaxResponse(int status) {
        this(status, (String)null, (String)null, (String)null, (String)null);
    }

    public AjaxResponse(int status, String message) {
        this(status, message, (String)null, (String)null, (String)null);
    }

    public AjaxResponse(int status, String message, String code) {
        this(status, message, code, (String)null, (String)null);
    }

    public AjaxResponse(int status, String message, String code, String forwardUrl, String confirmMsg) {
        this.datas = new HashMap();
        this.status = status;
        this.code = code;
        this.message = message;
        this.forwardUrl = forwardUrl;
        this.confirmMsg = confirmMsg;
    }

    public Boolean isError() {
        return this.status == 200;
    }

    public void addData(String key, Object value) {
        this.datas.put(key, value);
    }

    public static boolean isAjax(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

    public int getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getForwardUrl() {
        return this.forwardUrl;
    }

    public String getConfirmMsg() {
        return this.confirmMsg;
    }

    public Map<String, Object> getDatas() {
        return this.datas;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public void setConfirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AjaxResponse)) {
            return false;
        } else {
            AjaxResponse other = (AjaxResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getStatus() != other.getStatus()) {
                return false;
            } else {
                label73: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label73;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label73;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                label59: {
                    Object this$forwardUrl = this.getForwardUrl();
                    Object other$forwardUrl = other.getForwardUrl();
                    if (this$forwardUrl == null) {
                        if (other$forwardUrl == null) {
                            break label59;
                        }
                    } else if (this$forwardUrl.equals(other$forwardUrl)) {
                        break label59;
                    }

                    return false;
                }

                Object this$confirmMsg = this.getConfirmMsg();
                Object other$confirmMsg = other.getConfirmMsg();
                if (this$confirmMsg == null) {
                    if (other$confirmMsg != null) {
                        return false;
                    }
                } else if (!this$confirmMsg.equals(other$confirmMsg)) {
                    return false;
                }

                Object this$datas = this.getDatas();
                Object other$datas = other.getDatas();
                if (this$datas == null) {
                    if (other$datas != null) {
                        return false;
                    }
                } else if (!this$datas.equals(other$datas)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof AjaxResponse;
    }


    public String toString() {
        return "AjaxResponse(status=" + this.getStatus() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", forwardUrl=" + this.getForwardUrl() + ", confirmMsg=" + this.getConfirmMsg() + ", datas=" + this.getDatas() + ")";
    }
}
