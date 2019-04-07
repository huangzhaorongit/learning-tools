package com.distribution.wamoli.common.exception;

public class MD5Exception extends ApplicationException {
	private static final long serialVersionUID = 1L;

	public MD5Exception() {
		super();
	}

	public MD5Exception(String message) {
		super(message);
	}

	public MD5Exception(Throwable cause) {
		super(cause);
	}

	public MD5Exception(String message, Throwable cause) {
		super(message, cause);
	}

}
