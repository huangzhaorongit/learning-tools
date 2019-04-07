package com.distribution.wamoli.common.interceptor.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * 处理spring mvc 对象绑定注解<br/>
 * clazz 缺省转换为List<Map>
 * @author lium
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestList {
	Class<?> clazz() default Map.class;
	String value() default "_def_param_list";
}