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
public class ApplicationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7201648156140933835L;

    /**
     * 
     */
    public ApplicationException() {
        super();
    }

    /**
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

}
