package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.service.SystemcfgService;
import com.distribution.wamoli.domain.FSystemcfg;

@Controller
@RequestMapping("/platform/systemcfg")
public class SystemcfgController {

	 @Autowired
	private SystemcfgService systemcfgService;
	
	
	/***************************标准的查询************************************/
	
	//@SystemLogs("查询系统配置列表信息")
	@RequestMapping(value="/getlist")
	@ResponseBody
	public List<FSystemcfg> getList(FSystemcfg bean){
		return systemcfgService.getList(bean);
	}
	
	//@SystemLogs("查询系统配置信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public FSystemcfg getInfo(FSystemcfg bean){
		return systemcfgService.getInfo(bean);
	}
	
	//@SystemLogs("保存/修改系统配置信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody
	public String saveOrUpdate(FSystemcfg bean){
		return systemcfgService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("删除系统配置信息")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Integer delete(FSystemcfg bean){
		return systemcfgService.delete(bean);
	}
	

	/***************************个性化的查询************************************/
	//@SystemLogs("查询系统配置列表信息")
	@RequestMapping(value="/getmenulist")
	@ResponseBody
	public List<Map<String,Object>> getMenucfgList(FSystemcfg bean){
		return systemcfgService.getMenucfgList(bean);
	}
}
