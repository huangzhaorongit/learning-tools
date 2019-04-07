package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemcfg;


public interface SystemcfgService  extends IService<FBasecodetype>  {


	
	/***************************标准的查询************************************/
	
	public List<FSystemcfg> getList(FSystemcfg bean);
	
	public FSystemcfg getInfo(FSystemcfg bean);
	
	public String saveOrUpdate(FSystemcfg bean);
	
	public Integer delete(FSystemcfg bean);
	

	/***************************个性化的查询************************************/
	
	public List<Map<String,Object>> getMenucfgList(FSystemcfg bean);
}
