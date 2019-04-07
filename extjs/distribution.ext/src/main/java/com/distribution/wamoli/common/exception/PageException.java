package com.distribution.wamoli.common.exception;

/**
 * Created by Administrator on 2016/3/16.
 */
public class PageException  extends ApplicationRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageException(String message){
        super(message);
    }

	public PageException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PageException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
