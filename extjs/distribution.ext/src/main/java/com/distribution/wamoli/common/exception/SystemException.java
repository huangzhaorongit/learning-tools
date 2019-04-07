/*
 * Created on 2006-10-24
 * 
 */
package com.distribution.wamoli.common.exception;

/**
 * 
 * 
 * 
 */
public class SystemException extends ApplicationRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3502512403089659123L;

    /**
     * 
     */
    public SystemException() {
        super();
    }

    /**
     * @param message
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
