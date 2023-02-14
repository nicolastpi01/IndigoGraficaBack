package com.indigo.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	private String code;
	private HttpStatus httpStatus;

	 public CustomException(String code, String message) {
		 super(message);
		 this.setCode(code);
	 }

	 public CustomException(String code, String message, Throwable cause) {
		 super(message, cause);
		 this.setCode(code);
	 }

	 public CustomException(HttpStatus status, String message) {
		 super(message);
		 this.setHttpStatus(status);
	}

	public String getCode() {
	     return code;
	 }

	 public void setCode(String code) {
	     this.code = code;
	 }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
