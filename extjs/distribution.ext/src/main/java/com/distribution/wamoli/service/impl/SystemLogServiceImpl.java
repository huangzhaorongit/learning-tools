package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.service.SystemLogService;
import com.distribution.wamoli.mapper.FSystemlogMapper;
import com.distribution.wamoli.domain.FSystemlog;

@Service("systemLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemLogServiceImpl extends BaseService<FSystemlog> implements SystemLogService {

	@Autowired
	private FSystemlogMapper mapper;

	@Transactional(propagation=Propagation.REQUIRED)
	public void add(FSystemlog log){
		try{
			mapper.insert(log);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//@SystemLogs("查询系统操作日志列表")
	public  List<Map<String, Object>> getSystemLogOperate(FSystemlog bean, String startDateValue, String endDateValue)
	{
		String sql = " select l.id , l.description , l.method , l.type , l.ip , l.exceptioncode , l.exceptiondetail  , l.params ,l.creater, l.createdate"
				   + " from f_systemlog l "
				   + " where l.type = '0' ";
		if(!StringUtils.isEmpty(bean.getDescription())){
			sql += "and description like '%"+bean.getDescription()+"%' or creater like '%"+bean.getDescription()+"%'";
		}
		if(!StringUtils.isEmpty(startDateValue)&&!StringUtils.isEmpty(endDateValue)){
			sql += "and createdate between '"+ startDateValue+ "' and '"+ endDateValue +"'";
		}
		sql += "order by createdate desc";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统异常操作日志列表")
	public  List<Map<String, Object>> getSystemLogException(FSystemlog bean){
		String sql = " select l.id , l.description , l.method , l.type , l.ip , l.exceptioncode , l.exceptiondetail  , l.params ,l.creater, l.createdate"
				   + " from f_systemlog l "
				   + " where l.type = '1' ";
		if(!StringUtils.isEmpty(bean.getDescription())){
			sql += "and description like '%"+bean.getDescription()+"%' or creater like '%"+bean.getDescription()+"%'";
		}
		sql += "order by createdate desc";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统日志详情")
	public FSystemlog getSystemLog(FSystemlog bean) {
		return mapper.selectOne(bean);
	}





}
