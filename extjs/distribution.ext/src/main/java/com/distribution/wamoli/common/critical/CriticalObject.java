/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.distribution.wamoli.common.critical;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.distribution.wamoli.common.bean.UserBean;





public abstract interface CriticalObject {
	public abstract String getContextPath();

	public abstract String getBasePath();

	public abstract HttpSession getSession();

	public abstract HttpServletResponse getResponse();

	public abstract String getRoot();

	public abstract UserBean getUserBean();

	public abstract HttpServletRequest getRequest();
}