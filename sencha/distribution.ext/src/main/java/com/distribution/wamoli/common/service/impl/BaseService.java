package com.distribution.wamoli.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.distribution.wamoli.common.jdbc.JdbcAdapterFactory;
import com.distribution.wamoli.common.jdbc.SqlFunction;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.common.utils.SqlMapper.SqlMapper;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liuzh on 2014/12/11.
 */
public abstract class BaseService<T> implements IService<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected Mapper<T> mapper;

	public SqlMapper sqlMapper;


	public SqlFunction sf;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@PostConstruct
	public void InjectedSessionFactory() {
		initAdapter(sqlSessionTemplate);
	}

	public void initAdapter(SqlSession sqlSession) {
		sqlMapper = new SqlMapper(sqlSession);

	//	JdbcAdapterFactory jdbcadapterfactory = new JdbcAdapterFactory();
		//sf=JdbcAdapterFactory.getJdbcAdapter(ProjectUtils.getInitParameter("db","jdbc.dbType"));

		sf=JdbcAdapterFactory.getJdbcAdapter("mysql");
	}




	public SqlMapper getSqlMapper() {
		return sqlMapper;
	}


	public Mapper<T> getMapper() {

		return mapper;
	}

	public double selectMax(String tableName, String columName) {
		return this.selectMax(tableName, columName, null);
	}

	/**
	 *
	 * 获取某个表某列的最大值
	 *
	 * @param tableName
	 * @param columName
	 * @param condition
	 * @return
	 */
	public double selectMax(String tableName, String columName, String condition) {
		String maxSql = "select  Max(" + columName + ") max  from  " + tableName;
		if (!StringUtils.isEmpty(condition)) {
			maxSql = maxSql + "  where " + condition;
		}
		Map<String, Object> maxMAp = sqlMapper.selectOne(maxSql);

		if(maxMAp!=null){
		return Double.valueOf(maxMAp.get("max").toString());
		}else{

			return 1;
		}
	}

	public <T> int batchDeleteByPrimaryKey(Mapper<T> mapper, List<T> listT) {

		int i = 0;
		for (T t : listT) {
			i += mapper.deleteByPrimaryKey(t);
		}
		return i;
	}

	public <T> int batchUpdateByPrimaryKeySelective(Mapper<T> mapper, List<T> listT) {
		int i = 0;
		for (T t : listT) {
			i += mapper.updateByPrimaryKeySelective(t);
		}
		return i;
	}

	public <T> int batchInsertSelective(Mapper<T> mapper, List<T> listT) {
		int i = 0;
		for (T t : listT) {
			i += mapper.insertSelective(t);
		}
		return i;
	}

	@Override
	public T selectByKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	public int save(T entity) {
		return mapper.insert(entity);
	}

	public int delete(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	public int updateAll(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	public int updateNotNull(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

	// TODO 其他...
}
