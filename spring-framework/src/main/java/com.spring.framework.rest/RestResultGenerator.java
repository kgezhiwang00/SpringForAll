package com.spring.framework.rest;

public class RestResultGenerator {
	/**
	 * normal
	 * 
	 * @param success
	 * @param data
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> RestResult<T> genResult(String success, T data,
			String message) {
		RestResult<T> result = RestResult.newInstance();
		result.setCode(success);
		result.setData(data);
		result.setMessage(message);
		//add log
		return result;
	}

	/**
	 * success
	 * 
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> RestResult<T> genSuccessResult(T data) {

		return genResult(SuccessCode.SUCCESS.getCode(), data, SuccessCode.SUCCESS.getMessage());
	}

	/**
	 * error message
	 * 
	 * @param message
	 *            error message
	 * @param <T>
	 * @return
	 */
	public static <T> RestResult<T> genErrorResult(ErrorCode errorCode) {

		return genResult(errorCode.getCode(), null, errorCode.getMessage());
	}

	/**
	 * success no message
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static RestResult genSuccessResult() {
		return genSuccessResult(null);
	}
}
