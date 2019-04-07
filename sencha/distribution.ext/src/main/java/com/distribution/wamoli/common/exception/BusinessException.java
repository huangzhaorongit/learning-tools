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
public class BusinessException extends ApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = 3951750653227499608L;

    /**
     * 
     */
    public BusinessException() {
        super();
    }

    /**
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
