package com.spring.conf.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private <T> RestResult<T> runtimeExceptionHandler(Exception e) {
		//add log
		return RestResultGenerator.genErrorResult(ErrorCode.SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private <T> RestResult<T> illegalParamsExceptionHandler(
			MethodArgumentNotValidException e) {
		//add log
		return RestResultGenerator.genErrorResult(ErrorCode.ILLEGAL_PARAMS);
	}
}
