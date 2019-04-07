package com.distribution.wamoli.common.exception;

public class DownloadException extends ApplicationRuntimeException {
	private static final long serialVersionUID = 1L;
	private boolean showmsg;
	
	public DownloadException() {
		super();
	}
	
	public DownloadException(String message) {
		super(message);
	}
	
	public DownloadException(Throwable cause) {
		super(cause);
	}
	
	public DownloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public DownloadException(String string, boolean b) {
	   
	}

}
