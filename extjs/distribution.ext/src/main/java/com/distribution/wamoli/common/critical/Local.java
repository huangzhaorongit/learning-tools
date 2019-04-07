package com.distribution.wamoli.common.critical;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;

import com.distribution.wamoli.common.bean.UserBean;
import com.distribution.wamoli.common.context.LocalSpace;
import com.distribution.wamoli.common.context.ProjectContext;
import com.distribution.wamoli.common.context.ProjectSpace;
import com.distribution.wamoli.common.context.ContextAware.AppContextAware;
import com.distribution.wamoli.common.context.ContextAware.MvcContextAware;

public class Local {

	private static final ThreadLocal<CriticalObject> CriticalObjectStore = new ThreadLocal<CriticalObject>();
//
//	public static SessionFactory sessionFactory;
//
//	public void setSessionFactory(SessionFactory sessionFactory){
//		Local.sessionFactory = sessionFactory;
//	}

	//=====================================用户信息===========================================

	/**
	 * 获取用户对象
	 * @return
	 */
	public static UserBean getUserBean() {
		return getCriticalObject().getUserBean();
	}

	/**
	 * 获取用户公司id
	 * @return
	 */
	public static String getCompanyid() {
		return getUserBean().getCompanyid();
	}

	/**
	 * 获取用户id
	 * @return
	 */
	public static String getUserid() {
		return getUserBean().getUserid();
	}

	/**
	 * 获取用户编号
	 * @return
	 */
	public static String getUsercode() {
		return getUserBean().getUsername();
	}

	/**
	 * 获取用户名称
	 * @return
	 */
	public static String getUsername() {
		return getUserBean().getUsername();
	}

	/**
	 * 获取本地对象
	 * @return
	 */
	public static CriticalObject getCriticalObject() {
		return CriticalObjectStore.get();
	}

	/**
	 * 设置本地对象
	 * @param criticalObject
	 */
	public static void setCriticalObject(CriticalObject criticalObject) {
		CriticalObjectStore.set(criticalObject);
	}

	/**
	 * 项目名称
	 * @return
	 */
	public static String getContextPath(){
		return getCriticalObject().getContextPath();
	}

	/**
     * 这是一个便利的方法，帮助我们快速得到一个Bean
     * @param beanName bean的名字
     * @return 返回一个bean对象
     */
    public static Object getBean(String name) {
    	Object obj = null;
    	try{
        	obj = AppContextAware.getApplicationContext().getBean(name);
    	}catch (BeansException e) {
    		obj = MvcContextAware.getApplicationContext().getBean(name);
		}
        return obj;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个Bean
     * @param requiredType bean对象Class
     * @return 返回一个bean对象
     */
    public static <T> T getBean(Class<T> requiredtype) {
    	T bean = null;
    	try{
    		bean = AppContextAware.getApplicationContext().getBean(requiredtype);
    	}catch (BeansException e) {
    		bean = MvcContextAware.getApplicationContext().getBean(requiredtype);
		}
        return bean;
    }

	/**==================================================当前线程的对象==========================================*/

	/**
	 * 获取当前请求的Request对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return getCriticalObject().getRequest();
	}

	/**
	 * 获取当前请求的Response对象
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return getCriticalObject().getResponse();
	}

	/**==================================================本地文件目录==========================================*/

	/**
	 * 获取项目发布空间
	 * @return
	 */
	public static ProjectSpace getProjectSpace() {
		return getBean(ProjectSpace.class);
	}

	/**
	 * 获取项目本地空间
	 * @return
	 */
	public static LocalSpace getLocalSpace() {
		return getBean(LocalSpace.class);
	}

}
