package com.distribution.wamoli.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.distribution.wamoli.security.jwt.JWTConfigurer;
import com.distribution.wamoli.web.rest.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.aop.annotation.SystemControllerLog;
import com.distribution.wamoli.common.utils.Globals;
import com.distribution.wamoli.common.utils.SessionUtils;
import com.distribution.wamoli.service.LoginService;

import java.util.Collections;


@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Inject
    private AuthenticationManager authenticationManager;

	@SystemControllerLog(description = "用户登陆")
	@RequestMapping(value="/validate")
	@ResponseBody
    public ResultBean validate(HttpServletRequest request,HttpServletResponse response,String j_username,String j_password,Boolean invalidate) {
        if(j_username == null || j_username.length() < 4){
           return new ResultBean(false, 2);
        }else if(j_password == null || j_password.length() < 4){
            return new ResultBean(false, 3);
        }

	    UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(j_username, j_password);
        ResultBean result = null;
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            result = new ResultBean(true,"");
        } catch (AuthenticationException exception) {
            return new ResultBean(false, "3");
        }
//	    ResultBean result = loginService.login(j_username, j_password,invalidate);
//		if(!result.isSuccess())return result;
		UserBean bean = login(request, j_username);
		result.setData(bean);
		return result;
    }

	private UserBean login(HttpServletRequest request,String usercode){
		HttpSession session = request.getSession();
		String sessionid = session.getId();
		session.setMaxInactiveInterval(1800);  //单位秒
		UserBean bean = loginService.getUserInfo(usercode);

		session.setAttribute(Globals.SYSTEM_USER, bean);
		bean.setSessionid(sessionid);
		bean.setBasepath(request.getContextPath());
		SessionUtils.SessionContext.put(sessionid, session);
		return bean;
	}

	@SystemControllerLog(description = "获取登录用户对象")
	@RequestMapping(value="/getuserbean")
	@ResponseBody
	public UserBean getUserBean(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (UserBean)session.getAttribute(Globals.SYSTEM_USER);
	}

	@SystemControllerLog(description = "用户登出")
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	@ResponseBody
    public String logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sessionid = session.getId();
		SessionUtils.SessionContext.remove(sessionid);
		session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
		return "true";
    }

}
