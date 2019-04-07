package com.distribution.wamoli.common.exception;


public class FileException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public FileException() {
		super();
	}
	
	public FileException(String message) {
		super(message);
	}
	
	public FileException(Throwable cause) {
		super(cause);
	}
	
	public FileException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

	

}
