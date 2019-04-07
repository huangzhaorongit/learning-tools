package com.distribution.wamoli.common.exception;



public class EnumException extends ApplicationRuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	public EnumException() {
		super();
	}
	
	public EnumException(String message) {
		super(message);
	}
	
	public EnumException(Throwable cause) {
		super(cause);
	}
	
	public EnumException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

	

}
