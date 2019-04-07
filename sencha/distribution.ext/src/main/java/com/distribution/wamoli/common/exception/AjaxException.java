package com.distribution.wamoli.common.exception;

/**
 * Created by Administrator on 2016/5/16.
 */
public class AjaxException  extends ApplicationRuntimeException  {

    public AjaxException(String message){
        super(message);
    }

	public AjaxException() {
		super();
		
	}

	public AjaxException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public AjaxException(Throwable cause) {
		super(cause);
	}
    
    
    
}
