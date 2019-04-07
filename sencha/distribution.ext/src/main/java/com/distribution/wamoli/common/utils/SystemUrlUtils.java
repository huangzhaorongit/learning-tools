package com.distribution.wamoli.common.utils;

import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 程序的简单说明 
 * @author LIUM
 * @version 创建时间：2016-5-6 下午2:52:48
 */
public class SystemUrlUtils {
	
	private RequestMappingHandlerMapping rmhp;

	private static Map<String,String> urlmap;

	public Map<String, String> getUrlmap() {
		return urlmap;
	}

	public void setUrlmap(Map<String, String> urlmap) {
		Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();  
		for(RequestMappingInfo info : map.keySet()){
			PatternsRequestCondition prc = info.getPatternsCondition();
			if(prc!=null && prc.getPatterns().size()==1){
				String url = (String) prc.getPatterns().toArray()[0];
				HandlerMethod method = map.get(info);
				String beanName = method.getBean().toString();
				String methodName = method.getMethod().getName();
				String key = beanName+"_"+methodName;
				urlmap.put(key,url);
			}
		}
	} 
}
