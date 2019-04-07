/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.distribution.wamoli.common.exception;

public abstract interface Nestable {
	public abstract String getOriginalMessage();

	public abstract String getFullMessage();

	public abstract String getMessage();

	public abstract Throwable getCause();

	public abstract Throwable getOriginalThrowable();

	public abstract Throwable getMessageThrowable();
}