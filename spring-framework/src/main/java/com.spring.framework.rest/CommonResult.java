package com.spring.framework.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Auther: zcx
 * @Date: 2018/10/16 11:18
 * @Description:
 */
@Data
public class CommonResult<T> {
    @JsonIgnore
    private int httpCode;
    private int code;
    private String message;
    private T data;
    public CommonResult() {
    }

    public CommonResult(int httpCode, int code, String message, T data) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> newInstance(ReturnResult result, Class<T> clazz) {
        CommonResult<T> commonResult = new CommonResult();
        commonResult.fillResult((ReturnResult)(result == null ? ResultCode.SUCCESS : result));
        return commonResult;
    }

    public CommonResult<T> fillResult(ReturnResult result) {
        this.code = result.code();
        this.message = result.message();
        this.httpCode = result.httpCode();
        if (HttpHolder.getResponse() != null) {
            HttpHolder.getResponse().setStatus(this.httpCode);
        }

        return this;
    }
}
