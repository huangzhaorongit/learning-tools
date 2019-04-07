package com.distribution.wamoli.common.context.ContextAware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MvcContextAware implements ApplicationContextAware{

	private static ApplicationContext appCtx;

	public void setApplicationContext(ApplicationContext context) {
		appCtx = context;
	}

	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}
}
