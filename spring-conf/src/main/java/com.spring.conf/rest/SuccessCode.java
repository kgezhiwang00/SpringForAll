package com.spring.conf.rest;

public enum SuccessCode {
	SUCCESS(1, "100000", "成功");

	private final int id;
	private final String code;
	private final String message;

	private SuccessCode(int id, String code, String message) {
		this.id = id;
		this.code = code;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
