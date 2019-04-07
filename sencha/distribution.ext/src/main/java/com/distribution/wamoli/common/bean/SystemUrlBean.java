package com.distribution.wamoli.common.bean;

import java.io.Serializable;

/**
 * 系统Bean映射成url对象
 * @author LIUM
 * @version 创建时间：2016-5-9 下午2:19:00
 */
public class SystemUrlBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String beanname;
	
	private String methodname;
	
	private String url;
	
	public SystemUrlBean(){}
	
	public SystemUrlBean(String beanname,String methodname,String url){
		this.beanname = beanname;
		this.methodname = methodname;
		this.url = url;
	}

	public String getBeanname() {
		return beanname;
	}

	public void setBeanname(String beanname) {
		this.beanname = beanname;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
