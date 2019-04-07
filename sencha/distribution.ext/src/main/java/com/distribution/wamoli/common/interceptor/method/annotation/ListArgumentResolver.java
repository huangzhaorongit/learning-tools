package com.distribution.wamoli.common.interceptor.method.annotation;

import java.util.Arrays;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.common.utils.CommonUtils;
import com.distribution.wamoli.common.utils.JsonUtils;
import com.distribution.wamoli.common.utils.RequestJson4List;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lium
 */
public class ListArgumentResolver implements HandlerMethodArgumentResolver {

	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		RequestList requestList = parameter.getParameterAnnotation(RequestList.class);
		return requestList != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		RequestList requestList = parameter.getParameterAnnotation(RequestList.class);
		try {
			if (requestList != null) {
				String _param = requestList.value();
				if (_param.equals("_def_param_list")) {
					_param = parameter.getParameterName();
				}
				String str = webRequest.getParameter(_param);
				if (CommonUtils.isEmpty(str))return null;
				Class<?> clazz = requestList.clazz();
				Object obj = null;
				if(clazz.isAssignableFrom(Map.class)){
					obj = JsonUtils.toObject(str);
				}else{
					obj = RequestJson4List.fromJsonList(str,clazz);
				}
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}