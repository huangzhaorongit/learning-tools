package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestBean;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FModuledatastate;
import com.distribution.wamoli.domain.FModuleoperate;
import com.distribution.wamoli.domain.FSystemmodule;
import com.distribution.wamoli.service.SystemModuleService;


@Controller
@RequestMapping("/platform/systemmodule")
public class SystemModuleController {

	 @Autowired
	private SystemModuleService systemModuleService;
	
	//@SystemLogs("查询系统模块树形菜单")
	@RequestMapping(value="/gettree")
	@ResponseBody
	public List<Map<String,Object>> getModuleTree(TreeParams params){
		return systemModuleService.getModuleTree(params);
	}
	
	//@SystemLogs("查询系统模块信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public FSystemmodule getModuleInfo(FSystemmodule bean){
		return systemModuleService.getModuleInfo(bean);
	}
	
	//@SystemLogs("查询系统模块操作列表")
	@RequestMapping(value="/getoperatelist")
	@ResponseBody
	public List<FModuleoperate> getModuleOperateList(FModuleoperate bean){
		return systemModuleService.getModuleOperateList(bean);
	}
	
	///@SystemLogs("查询系统模块数据状态列表")
	@RequestMapping(value="/getdatastatelist")
	@ResponseBody
	public List<FModuledatastate> getModuleDataStateList(FModuledatastate bean){
		return systemModuleService.getModuleDataStateList(bean);
	}
	
	//@SystemLogs("删除系统模块信息")
	@RequestMapping(value="/delete")
	@ResponseBody
	public String deleteModule(String moduleid){
		return systemModuleService.deleteModule(moduleid);
	}
	
//	@SystemLogs("添加或修改系统模块信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public String saveOrUpdate(@RequestBean FSystemmodule bean,@RequestList(clazz=FModuleoperate.class) List<FModuleoperate> list1,@RequestList(clazz=FModuledatastate.class) List<FModuledatastate> list2){
		return systemModuleService.saveOrUpdate(bean,list1,list2);
	}
}
