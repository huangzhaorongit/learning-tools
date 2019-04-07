package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecode;
import com.distribution.wamoli.domain.FBasecodetype;


public interface BaseCodeTypeService extends IService<FBasecodetype>{
	

	
    //@SystemLogs("查询数据字典列表(主)")
	public List<Map<String, Object>> getBaseCodeTypelist(FBasecodetype bean); 
	
	//@SystemLogs("查询数据字典信息(主)")
	public FBasecodetype getBasecodetypeinfo(FBasecodetype bean); 
	
	//@SystemLogs("查询数据字典列表(副)")
	public List<Map<String, Object>> getFbasecodelist(FBasecode bean);
	
	//@SystemLogs("添加或修改数据字典")
	public String saveorupdate(FBasecodetype bean, List<FBasecode> list);
	
	//@SystemLogs("删除数据字典")
	public String deleteFbasecodetype(String codetype);
	
}
