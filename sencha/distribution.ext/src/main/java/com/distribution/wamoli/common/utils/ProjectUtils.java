package com.distribution.wamoli.common.utils;



import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.distribution.wamoli.common.bean.DownloadFile;
import com.distribution.wamoli.common.bean.FSystemlog;
import com.distribution.wamoli.common.bean.SystemUrlBean;
import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.exception.ApplicationRuntimeException;
import com.distribution.wamoli.common.pojo.ErrorType;
import com.distribution.wamoli.common.service.IService;





public class ProjectUtils {
    //本地异常日志记录对象  
    private static final Logger logger = LoggerFactory.getLogger(ProjectUtils.class);  
	
    //文件资源bundle
	private final static Map<String,ResourceBundle> bundleMap = new HashMap<String,ResourceBundle>();
	
	//系统全部URL对照
	private static Map<String,SystemUrlBean> urlMap = new HashMap<String,SystemUrlBean>();
	
	//系统配置（application.properties）
	private static Map<String,String> cfgMap = new HashMap<String, String>();

	/**
	 * 获取当前用户的首选语言
	 * 
	 * @return
	 */
	public static Locale getLanguage() {
		String local = CookieUtils.getCookie(Globals.COOKIE_LANGUAGE);
		local = CommonUtils.isEmpty(local) ? "zh" : local;
		return new Locale(local);
	}

	/**
	 * 设置首选语言
	 * @param localeResolver
	 * @param local
	 */
	public static void setLanguage(LocaleResolver localeResolver, Locale local) {
		HttpServletResponse response = Local.getCriticalObject().getResponse();
		HttpServletRequest request = Local.getCriticalObject().getRequest();
		localeResolver.setLocale(request, response, local);
	}
	
	/****************************************语言资源管理****************************************************/

	/**
	 * 获取当前语言下key对应的值
	 * @param key
	 * @return
	 */
	public static String getMessages(String key) {
		return getResourceBundle("messages/messages",ProjectUtils.getLanguage()).getString(key);
	}
	
	/**
	 * 获取当前语言下key对应的值 
	 * @param keys 数组
	 * @return
	 */
	public static Map<String, String> getMessages(String[] keys) {
		Map<String, String> map = new HashMap<String, String>();
		if(keys==null)return map;
		for (int i = 0; i < keys.length; i++)map.put(keys[i], getMessages(keys[i]));
		return map;
	}

	/**
	 * 获取当前语言下全部的值
	 * @return
	 */
	public static Map<String, String> getAllMessages() {
		ResourceBundle bundle = getResourceBundle("messages/messages",ProjectUtils.getLanguage());
		Enumeration<String> em = bundle.getKeys();
		Map<String, String> map = new HashMap<String, String>();
		while (em.hasMoreElements()) {
			String key = em.nextElement();
			String value = bundle.getString(key);
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 获取application资源下key对应的值
	 * @param key
	 * @return
	 */
	public static String getInitParameter(String resourceName,String key) {
		String v = cfgMap.get(key);
		try {
			if(v == null){
				v = getResourceBundle(resourceName,null).getString(key);
				cfgMap.put(key, v);
			}
		} catch (Exception e) {
		}
		return v;
	}
	/**
	 * 获取当前语言下key对应的值 
	 * @param keys 数组
	 * @return
	 */
	public static Map<String, String> getInitParameter(String resourceName,String[] keys) {
		Map<String, String> map = new HashMap<String, String>();
		if(keys==null)return map;
		ResourceBundle bundle = getResourceBundle(resourceName,null);
		for (int i = 0; i < keys.length; i++)map.put(keys[i],bundle.getString(keys[i]));
		return map;
	}
	
	/**
	 * 获取资源文件配置
	 * @param baseName
	 * @param locale
	 * @return
	 */
	public static ResourceBundle getResourceBundle(String baseName,Locale locale) {
		String key = locale==null?baseName:baseName+locale.toString();
		ResourceBundle bundle = bundleMap.get(key);
		if(bundle == null){
			if(locale==null){
				bundle = ResourceBundle.getBundle(baseName);
			}else{
				bundle = ResourceBundle.getBundle(baseName,locale);
			}
		}
		return bundle;
	}
	
	/**
	 * 通过Response返回JSON数据
	 * @param retVal
	 */
	public static void writeJson(Object retVal){
		writeData(retVal,true,true);
	}
	
	/**
	 * 通过Response返回字符串数据
	 * @param retVal
	 */
	public static void writeHtml(Object retVal){
		writeData(retVal,false,true);
	}
	
	/**
	 * 通过Response返回数据
	 * @param retVal
	 */
	public static void writeData(Object retVal,boolean isjson,boolean close){
    	HttpServletResponse response = Local.getResponse();
    	PrintWriter out = null;  
		try {
            out = response.getWriter();  
			response.setCharacterEncoding("UTF-8");
			if(isjson){
				response.setContentType("application/json; charset=utf-8"); 
	            out.write(JsonUtil.toJsonString(retVal));
			}else{
				response.setContentType("text/html;charset=UTF-8"); 
	            out.write(String.valueOf(retVal));
			}
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {  
            if (close && out != null) {  
                out.close();  
            }  
        }
	}
	
	public static void sendError(ErrorType type,HttpServletRequest request,HttpServletResponse response){
		//为 null，则为传统同步请求； 为 XMLHttpRequest，则为 Ajax 异步请求。
		String head = request.getHeader("x-requested-with");
		try {
			if(head==null){
				if(type == ErrorType.E999){
					response.sendRedirect(request.getContextPath() + Globals.USER_LOGIN_BASEPATH);
				}else if(type == ErrorType.E998){
					writeData("没有访问权限！",false,false);
				}
			}else{
				response.sendError(type.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断当前用户是否有访问url权限
	 * @return
	 */
	public static boolean requestVerification(){
		UserBean bean = Local.getUserBean();
		if(bean==null)return true;
		String basepath = bean.getBasepath();
		String url = Local.getRequest().getRequestURL().toString();
		url = url.substring(url.indexOf(basepath)+basepath.length()+1,url.length());
		Map<String,Boolean> map = bean.getUrllimits();
		Boolean limits = map.get(url);
		return limits==null?true:limits;
	}
	
	 /** 
     * 获取注解中对方法的描述信息
     * @param joinPoint 切点 
     * @return FSystemlog对象
     * @throws Exception 
     */  
    public static FSystemlog getFSystemlog(JoinPoint joinPoint) throws Exception {
    	Annotation[] annotations = getAnnotation(joinPoint);
    	return getFSystemlog(annotations);
    }
    
    /** 
     * 获取注解中对方法的描述信息
     * @param joinPoint 切点 
     * @return FSystemlog对象
     * @throws Exception 
     */  
    public static FSystemlog getFSystemlog(Annotation[] annotations) throws Exception {
    	FSystemlog slogs = null;
    	for (int i = 0; i < annotations.length; i++) {
    		if(annotations[i] instanceof FSystemlog){
    			slogs = (FSystemlog) annotations[i];
    			break;
    		}
		}
    	return slogs;
    }
    
    /** 
     * 获取方法的全部注解
     * @param joinPoint 切点 
     * @return FSystemlog对象
     * @throws Exception 
     */  
    public static Annotation[] getAnnotation(JoinPoint joinPoint) throws Exception {
    	Annotation[] as = null;
        String classType = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Class<?> className = Class.forName(classType);  
        Method[] methods = className.getMethods();
        for (int i = 0; i < methods.length; i++) {
        	 Method method = methods[i];
        	 if(method.getName().equals(methodName)){
        		 as = method.getAnnotations();
        		 break;
        	 }
		}
    	return as;
    }
    

	/**
	 * 如果是JavaException或其派生类，则返回最顶层抛出的消息，否则返回当前异常信息
	 * @param throwable
	 * @return
	 */
	public static String getErrorMessage(Throwable throwable) {
		String msg = null;
		if(throwable instanceof ApplicationRuntimeException) {
			msg = ((ApplicationRuntimeException)throwable).getOriginalMessage();
		}
		if(msg==null || msg.length()==0) {
			msg = throwable.getMessage();
		}
		return msg;
	}
	
	/**
	 * 获取异常跟踪信息
	 * @param throwable
	 * @return
	 */
	public static String getErrorStackTrace(Throwable throwable) {
		if(throwable instanceof ApplicationRuntimeException) {
			return ((ApplicationRuntimeException) throwable).toString();
		}else {
			String msg = throwable.toString();
			if(msg == null) msg = "";
			if(throwable != null) {
				StackTraceElement[] stackTrace = throwable.getStackTrace();
				if(stackTrace != null) for(int i=0; i<stackTrace.length; i++) msg += "\n\t\tat "+stackTrace[i]+"";
			}
			return msg;
		}
	}
	
	/**
	 * 系统全部URL对照
	 * @return
	 */
	public static Map<String,SystemUrlBean> getSystemUrls(Map<String,Object> includeMap) {
		if(urlMap.size()==0){
			RequestMappingHandlerMapping rmhp = Local.getBean(RequestMappingHandlerMapping.class);
			Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();  
			for(RequestMappingInfo info : map.keySet()){
				PatternsRequestCondition prc = info.getPatternsCondition();
				if(prc!=null && prc.getPatterns().size()==1){
					String url = (String) prc.getPatterns().toArray()[0];
					HandlerMethod method = map.get(info);
					String beanName = method.getBean().toString();
					String methodName = method.getMethod().getName();
					String key = beanName+"_"+methodName;
					url = url.substring(1,url.length())+".do";
					beanName = StringUtils.capitalize(beanName); 
					urlMap.put(key.toUpperCase(),new SystemUrlBean(beanName, methodName, url));
				}
			}
		}
		return urlMap;
	}
	
	/****************************************错误日志****************************************************/
	/**
	 * 写错误日志
	 * @param method  方法名称	 例:com.nsc.platform.controller.Login.validate()
	 * @param params  参数拼接字符串
	 * @param description  描述     例:用户登陆
	 * @param logService 
	 * @param e
	 */
	public static void writeErrorLog(String method,String params,String description,IService logService,Throwable e){
		try{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
	        HttpSession session = request.getSession();  
	        UserBean user = (UserBean) session.getAttribute(Globals.SYSTEM_USER);
	        String ip = request.getRemoteAddr();  
	        FSystemlog log = new FSystemlog();
	        log.setId(IDUtils.getUuid());
	        log.setDescription(description);  
	        log.setMethod(method);  
	        log.setType("1");  
	        log.setIp(ip);
	        log.setExceptioncode(e.getClass().getName());  
	        log.setExceptiondetail(e.getMessage());  
	        log.setParams(params); 
	        log.setCreater(CommonUtils.isEmpty(user.getUsername())?"临时用户":user.getUsername());  
	        log.setCreatedate(DateUtils.getCurrentTime());  
	        logService.save(log); 
		}  catch (Exception ex) {  
            logger.error("异常信息:{}", ex.getMessage());  
        }
	//	logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}",method, e.getClass().getName(), e.getMessage(), params);  
	}

	/****************************************返回文件流管理****************************************************/
	
	/**
	 * 附件下载
	 * @param df 
	 * @throws IOException
	 */
	public static void returnDownFile(DownloadFile df) throws IOException{
		HttpServletResponse response = Local.getResponse();
		OutputStream os = response.getOutputStream();
		response.setCharacterEncoding("utf-8");     
		if(!CommonUtils.isEmpty(df.getContentType())) response.setContentType(df.getContentType());
		if(df.isDialog()){
			if(CommonUtils.isEmpty(df.getFileName()))df.setFileName("unknown");
			response.setHeader("content-disposition",String.format("attachment;filename*=utf-8'zh_cn'%s",URLEncoder.encode(df.getFileName(),"utf-8")));
		}
		switch(df.getType()) {
			case 0: FileUtils.copy(df.getInputStream(), os);break;
			case 1: df.getExcel().write(os); os.close(); break;
		}
	}
	
	/**
	 * 下载文件异常错误跳转
	 * @param e
	 */
	public static void returnDownError(Exception e){
		try {
			Local.getRequest().setAttribute(Globals.FILE_DOWNLOAD_ERROR,e.getMessage());
			RequestDispatcher dispatcher = Local.getRequest().getRequestDispatcher(Globals.ERROR_DOWNLOAD_BASEPATH);
			dispatcher.forward(Local.getRequest(),Local.getResponse());
		} catch (Exception e1) { 
			
		} 
	}
}
