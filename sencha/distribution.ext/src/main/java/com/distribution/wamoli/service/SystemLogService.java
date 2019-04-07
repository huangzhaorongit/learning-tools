package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemlog;


public interface SystemLogService extends IService<FSystemlog>{

	
	
	public void add(FSystemlog log);

	//@SystemLogs("查询系统日志详情")
	public FSystemlog getSystemLog(FSystemlog bean) ;

	public List<Map<String, Object>> getSystemLogOperate(FSystemlog bean, String startDateValue, String endDateValue);

	public List<Map<String, Object>> getSystemLogException(FSystemlog bean);
}
