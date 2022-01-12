package com.huso.transformation.exception;

import lombok.Data;

@Data
public class ErrorResponse {
	private int errorCode;
	private String message;
}
