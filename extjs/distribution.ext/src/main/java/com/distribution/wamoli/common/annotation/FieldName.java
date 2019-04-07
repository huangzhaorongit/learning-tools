/**
 * Copyright &copy; 2015-2020 <a href="http://www.yihere.org/">JeePlus</a> All rights reserved.
 */
package com.distribution.wamoli.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * bean中文名注解
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface FieldName {

	String value();
	
}
