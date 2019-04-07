package com.distribution.wamoli.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
	
	public static boolean is(Boolean b) {
		return b!=null && b.booleanValue();
	}
	
	public static boolean isEmpty(Object v) {
		return isEmpty(v, true);
	}
	
	public static boolean isEmpty(Object v, boolean trim) {
		if(v == null) return true;
		if(v instanceof String) {
			String sv = (String) v;
			return trim ? sv.trim().length()==0 : sv.length()==0;
		}else {
			return false;
		}
	}
	
	public static String getArrayToString(String[] array,String split){
		if(isEmpty(array))return "";
		String str = "";
		for (int i = 0; i < array.length; i++){ 
			if(isEmpty(array[i]))continue;
			str+=array[i]+split;
		}
		if(str.length()>0)str = str.substring(0,str.length()-split.length());
		return str;
	}
	
	/**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(c);
            } else {
                sb.append(toUpperAscii(c));
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }
	
	/**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }
    
    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static char toUpperAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c -= (char) 0x20;
        }
        return c;
    }
    
    /**  
     * 判断一个类是否为基本数据类型。  
     * @param clazz 要判断的类。  
     * @return true 表示为基本数据类型。  
     */ 
    public static boolean isBaseDataType(Class<?> clazz)  throws Exception{   
        return (   
            clazz.equals(String.class) ||   
            clazz.equals(Integer.class)||   
            clazz.equals(Byte.class) ||   
            clazz.equals(Long.class) ||   
            clazz.equals(Double.class) ||   
            clazz.equals(Float.class) ||   
            clazz.equals(Character.class) ||   
            clazz.equals(Short.class) ||   
            clazz.equals(BigDecimal.class) ||   
            clazz.equals(BigInteger.class) ||   
            clazz.equals(Boolean.class) ||   
            clazz.equals(Date.class) ||   
            clazz.isPrimitive()   
        );   
    }
}
