package com.osi.urm.exception;

/**
 * @author rdonepudi
 *
 */
public class AuthException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int httpStatus;
	private String errorMessage;
	private String errorCode;
	private String developerMessage;
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	public AuthException(int httpStatus, String errorMessage, String errorCode, String developerMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.developerMessage = developerMessage;
	}
	
}
