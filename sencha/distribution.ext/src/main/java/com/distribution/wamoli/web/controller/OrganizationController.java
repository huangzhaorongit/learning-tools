package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.service.OrganizationService;
import com.distribution.wamoli.domain.FOrganization;



@Controller
@RequestMapping("/platform/organization")
public class OrganizationController {

	 @Autowired
	private OrganizationService organizationService;
	
	//@SystemLogs("添加或修改内部组织信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public FOrganization saveOrUpdate(FOrganization bean){
		return organizationService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("查询内部组织树形菜单")
	@RequestMapping(value="/getOrganizationTree")
	@ResponseBody
	public List<Map<String, Object>> getOrganizationTree(TreeParams params){
		return organizationService.getOrganizationTree(params);
	}
	
	//@SystemLogs("查询内部组织信息")
	@RequestMapping(value="/getOrganizationInfo")
	@ResponseBody
	public Map<String, Object> getOrganizationInfo(String orgid){
		return organizationService.getOrganizationInfo(orgid);
	}
	
	//@SystemLogs("删除内部组织信息")
	@RequestMapping(value="/deleteOrganization")
	@ResponseBody
	public String deleteOrganization(String orgid){
		return organizationService.deleteOrganization(orgid);
	}
	
	//@SystemLogs("修改内部组织节点的排列顺序")
	@RequestMapping(value="/saveMoveOrder")
	@ResponseBody
	public String saveMoveOrder(@RequestList(clazz=FOrganization.class) List<FOrganization> list){
		organizationService.saveMoveOrder(list);
		return "true";
	}
}
