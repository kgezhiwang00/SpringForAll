package com.spring.framework.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "响应信息", description = "restResult")
public class RestResult<T> {
	@ApiModelProperty(name= "code", value = "响应码", dataType="String",example="10000:成功",required = true) 
	private String code;
	@ApiModelProperty(value = "消息提示", required = true) 
	private String message;
	@ApiModelProperty(value = "数据", required = true) 
	private T data;

	private RestResult() {}

	public static <T> RestResult<T> newInstance() {
		return new RestResult<T>();
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
	return "RestResult{" +
			"code=" + code +
			", message='" + message + '\'' +
			", data=" + data +
			'}';
	    	}
}
