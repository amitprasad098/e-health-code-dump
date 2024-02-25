package com.uos.electronic.healthcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uos.electronic.healthcare.model.ExceptionResponseBean;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionResponseBean> handleException(Exception exception) {
		ExceptionResponseBean exceptionResponseBean = ExceptionResponseBean.builder().code("Exception code")
				.message(exception.getLocalizedMessage()).status(HttpStatus.BAD_REQUEST.name()).build();
		return new ResponseEntity<>(exceptionResponseBean, HttpStatus.BAD_REQUEST);

	}
}
