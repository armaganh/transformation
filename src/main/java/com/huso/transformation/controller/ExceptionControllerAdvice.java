package com.huso.transformation.controller;

import com.huso.transformation.exception.ErrorResponse;
import com.huso.transformation.exception.LetterValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(500);
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LetterValidationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(LetterValidationException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(1001);
		error.setMessage("The items can includes only letters!");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
