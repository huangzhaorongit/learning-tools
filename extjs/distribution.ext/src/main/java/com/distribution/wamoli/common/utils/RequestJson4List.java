package com.distribution.wamoli.common.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RequestJson4List<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -369558847578246550L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private List<T> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static RequestJson4List fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(RequestList.class, clazz);
        return gson.fromJson(json, objectType);
    }
    
    public static <T> List<T> fromJsonList(String json, Class<T> clazz){
    	Gson gson = new Gson();
    	JsonParser parser = new JsonParser();
    	JsonArray array = parser.parse(json).getAsJsonArray();
    	List<T> lst = new ArrayList<T>();
    	for (final JsonElement el: array) {
			T entity = gson.fromJson(el, clazz);
			lst.add(entity);
		}
    	return lst;
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(RequestList.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}