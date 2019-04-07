package com.distribution.wamoli.common.context.ContextAware;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 配置为SpringFramework的Bean，用来获取ServletContext实例
 */
@Component
public class SContextAware implements ServletContextAware{

	private static ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		SContextAware.servletContext = servletContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

}
