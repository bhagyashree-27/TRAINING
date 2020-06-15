package com.example.springdatademo.response;

import java.util.List;

public class ErrorResponse {
	Long errorCode;
	List<String> errorMessage;
	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorResponse(Long errorCode, List<String> message) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	

}
