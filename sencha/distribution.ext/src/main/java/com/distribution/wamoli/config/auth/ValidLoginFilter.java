package com.distribution.wamoli.config.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.context.ContextAware.SContextAware;
import com.distribution.wamoli.common.critical.CriticalObject;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.pojo.ErrorType;
import com.distribution.wamoli.common.utils.CommonUtils;
import com.distribution.wamoli.common.utils.Globals;
import com.distribution.wamoli.common.utils.ProjectUtils;
import com.distribution.wamoli.common.utils.SessionUtils;



public class ValidLoginFilter implements Filter {
	public void doFilter(ServletRequest requ, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) requ;
	final HttpServletResponse response = (HttpServletResponse)resp;
	String url = request.getRequestURL().toString();
	boolean beFilter = true;
	for (String s : Globals.NOFILTERS) {
		if (url.endsWith(s)) {
			beFilter = false;
			break;
		}
	}
	if (beFilter) {
		HttpSession session = request.getSession();
		UserBean bean = (UserBean)session.getAttribute(Globals.SYSTEM_USER);
		if(bean==null || CommonUtils.isEmpty(bean.getUserid())){
			String sessionid = request.getParameter("JSESSIONID");
			String companyid = request.getParameter("companyid");
			if(!CommonUtils.isEmpty(sessionid)){
				HttpSession old_session = SessionUtils.SessionContext.get(sessionid);
				session.setAttribute(Globals.SYSTEM_USER,old_session.getAttribute(Globals.SYSTEM_USER));
			}else if(url.indexOf("platform/personnel/getphoto.do")>0 && !CommonUtils.isEmpty(companyid)){
				bean = new UserBean();
				bean.setCompanyid(companyid);
				session.setAttribute(Globals.SYSTEM_USER,bean);
			}else{
				ProjectUtils.sendError(ErrorType.E999,request,response);
				return;
			}
		}
	}
	setBasePath(request);
	setCriticalObject(request, response);
	if(beFilter && !ProjectUtils.requestVerification()){
		ProjectUtils.sendError(ErrorType.E998,request,response);
		return;
	}
	chain.doFilter(request, response);
}

@Override
public void destroy() {

}

@Override
public void init(FilterConfig arg0) throws ServletException {

}


/**
 * 设置项目路径到request中
 * @param request
 */
private void setBasePath(HttpServletRequest request){
	request.setAttribute(Globals.BASE_PATH,request.getContextPath());
}

/**
 * 设置当前请求的本地对象
 * @param request
 * @param response
 */
private void setCriticalObject(final HttpServletRequest request,final HttpServletResponse response){
	Local.setCriticalObject(new CriticalObject() {
		@Override
		public HttpServletResponse getResponse() {
			return response;
		}

		@Override
		public HttpServletRequest getRequest() {
			return request;
		}
		@Override
		public UserBean getUserBean() {
			UserBean bean = (UserBean)request.getSession(false).getAttribute(Globals.SYSTEM_USER);
			return bean;
		}

		@Override
		public String getRoot() {
			return SContextAware.getServletContext().getRealPath("/");
		}

		@Override
		public HttpSession getSession() {
			return request.getSession(false);
		}

		@Override
		public String getBasePath() {
			return request.getContextPath();
		}

		@Override
		public String getContextPath() {
			return request.getContextPath();
		}
	});
}}
