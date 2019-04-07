package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FModuledatastate;
import com.distribution.wamoli.domain.FModuleoperate;
import com.distribution.wamoli.domain.FSystemmodule;


public interface SystemModuleService extends IService<FSystemmodule>{


	
	
	//@SystemLogs("查询系统模块树形菜单")
	public List<Map<String,Object>> getModuleTree(TreeParams params);
	
	//@SystemLogs("查询系统模块信息")
	public FSystemmodule getModuleInfo(FSystemmodule bean);
	
	//@SystemLogs("查询系统模块操作列表")
	public List<FModuleoperate> getModuleOperateList(FModuleoperate bean);
	
	//@SystemLogs("查询系统模块数据状态列表")
	public List<FModuledatastate> getModuleDataStateList(FModuledatastate bean);
	
	//@SystemLogs("删除系统模块信息")
	public String deleteModule(String moduleid);
	
	//@SystemLogs("添加或修改系统模块信息")
	public String saveOrUpdate(FSystemmodule bean,List<FModuleoperate> list1,List<FModuledatastate> list2);
}
