package com.distribution.wamoli.common.utils;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 淘淘商城自定义响应结构
 */
public class JsonUtils {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 * <p>
	 * Title: pojoToJson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param beanType
	 *            对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>
	 * Title: jsonToList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static MappingJacksonValue jsonp(Object value, String callback) {
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

	/**
	 * 对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJsonString(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转为json对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Object toObject(String str) {
		JsonObject Object = new JsonParser().parse(str).getAsJsonObject();
		return Object;

	}

	/**
	 * json字符串转为json数组
	 * 
	 * @param obj
	 * @return
	 */
	public static JsonArray toJsonArray(String str) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = parser.parse(str).getAsJsonArray();
		return Jarray;
	}

	/**
	 * json字符串转成对象
	 * 
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
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

}
