package com.distribution.wamoli.common.utils;


import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.ReflectionUtils;




import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 创 建 人：Wesley
 * 创建日期：2015-05-25
 * 修 改 人：
 * 修改日 期：
 * 描   述：扩展org.apache.commons.beanutils.BeanUtils。  
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    /** 
     * 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性 
     *  
     * @param dest 
     *            目标对象，标准的JavaBean 
     * @param orig 
     *            源对象，可为Map、标准的JavaBean
     */  
    @SuppressWarnings("rawtypes")  
    public static void applyIf(Object dest, Object orig) throws Exception {  
        try {  
            if (orig instanceof Map) {  
                Iterator names = ((Map) orig).keySet().iterator();  
                while (names.hasNext()) {  
                    String name = (String) names.next();  
                    if (PropertyUtils.isWriteable(dest, name)) {
                        Object value = ((Map) orig).get(name);  
                        if (value != null) {  
                            PropertyUtils.setSimpleProperty(dest, name, value);  
                        }  
                    }  
                }  
            } else {  
                Field[] fields = orig.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {  
                    String name = fields[i].getName();
                    Field destField= ReflectionUtils.findField(dest.getClass(), name);
                    if(destField==null){
                        continue;
                    }
                    if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {  
                        Object value = PropertyUtils.getSimpleProperty(orig, name);  
                        if (value != null) {  
                            PropertyUtils.setSimpleProperty(dest, name, value);  
                        }  
                    }  
                }  
            }  
        } catch (Exception e) {  
            throw new Exception("将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性", e);  
        }  
    }  
  


    
    


	/**
	 * 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性
	 * 
	 * @param orig
	 *            源对象，标准的JavaBean
	 * @param dest
	 *            排除检查的属性，Map
	 * 
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkObjProperty(Object orig, Map dest) throws Exception {
		try {
			java.lang.reflect.Field[] fields = orig.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if (!dest.containsKey(name)) {
					if (PropertyUtils.isReadable(orig, name)) {
						Object value = PropertyUtils.getSimpleProperty(orig, name);
						if (value == null) {
							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception("将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性", e);
		}
	}

	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}


}  