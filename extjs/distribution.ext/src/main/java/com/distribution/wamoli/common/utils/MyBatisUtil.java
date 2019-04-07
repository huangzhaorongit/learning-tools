package com.distribution.wamoli.common.utils;

import java.lang.reflect.Field;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.distribution.wamoli.common.context.ContextAware.AppContextAware;
import com.distribution.wamoli.common.service.impl.BaseService;





public class MyBatisUtil {

	private static SqlSessionFactory factory;

	 /**
     * 获取SqlSessionFactory
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
		ApplicationContext wac = AppContextAware.getApplicationContext();
		if(factory==null)factory = (SqlSessionFactory) wac.getBean("sqlSessionFactory");
        return factory;
    }

    /**
     * 获取SqlSession
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }

    /**
     * 获取SqlSession
     * @param isAutoCommit
     *            true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务 false
     *            表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.
     *            commit()提交事务
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        return getSqlSessionFactory().openSession(isAutoCommit);
    }

    @SuppressWarnings("unchecked")
	public static <T> T getSystemService(Class<T> clazz){
    	T t = null;
    	try {
    		Class<?> c = Class.forName(clazz.getName());
    		t = (T) c.newInstance();
    		SqlSession sqlSession = getSqlSession();
    		if(t instanceof BaseService){
    			BaseService adapter = (BaseService) t;
    			adapter.initAdapter(sqlSession);
    		}
    		Field[] fields = c.getDeclaredFields();
    		for(Field field : fields){
    			Autowired meta = field.getAnnotation(Autowired.class);
                if(meta!=null){
                	Object mapper = sqlSession.getMapper(field.getType());
                	field.setAccessible(true);
                	field.set(t,mapper);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return t;
    }
}
