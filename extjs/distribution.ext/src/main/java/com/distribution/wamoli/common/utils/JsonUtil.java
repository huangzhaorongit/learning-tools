package com.distribution.wamoli.common.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * http://penghuaiyi.iteye.com/blog/1922632
 * Java对象和JSON字符串相互转化工具类
 * @author penghuaiyi
 * @date 2013-08-10
 * 
 * 		Map<String,String> bean = gson.fromJson(report008,  new TypeToken<Map<String, String>>() {
		}.getType());
 */
public final class JsonUtil {
	
	private JsonUtil(){}
	
    /**
     * 对象转换成json字符串
     * @param obj 
     * @return 
     */
    public static String toJsonString(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type
     * @return 
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type 
     * @return 
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

}
