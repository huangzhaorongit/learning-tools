package com.distribution.wamoli.common.exception;

/**
 * Created by Administrator on 2016/4/9.
 */
public class MapperException extends ApplicationRuntimeException {
    public MapperException(String message) {
        super(message);
    }

	public MapperException() {
		super();
		
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public MapperException(Throwable cause) {
		super(cause);
	
	}
    
    
    
}
