package com.spring.conf.rest;

public enum ErrorCode {
	//general error
	SERVER_ERROR						("100001", "服务器开小差啦..."), 
	ILLEGAL_PARAMS						("100002", "参数错误"),
	NOT_CONFORM_RULES					("100003", "参数不合法"),
	;

	private final String code;
	private final String message;

	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
