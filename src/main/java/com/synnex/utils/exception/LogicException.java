package com.synnex.utils.exception;

public class LogicException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	// 不同异常，对应不同的错误码
	private Integer errorCode = -999;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	public LogicException() {
		super();
	}
	
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(String message,Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public LogicException(Throwable cause) {
		super(cause);
	}

}
