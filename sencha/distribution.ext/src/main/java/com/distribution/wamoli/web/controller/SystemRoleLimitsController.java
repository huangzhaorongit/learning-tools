package com.distribution.wamoli.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemroleLimits;
import com.distribution.wamoli.service.SystemRoleLimitsService;


@Controller
@RequestMapping("/platform/systemrolelimits")
public class SystemRoleLimitsController {

	 @Autowired
	private SystemRoleLimitsService systemRoleLimitsService;
	
	//@SystemLogs("查询系统角色列表")
	@RequestMapping(value="/getsystemrole")
	@ResponseBody
	public List<FSystemrole> getSystemrole(String rolecode) {
		return systemRoleLimitsService.getSystemrole(rolecode);
	}

	
	
	
	@RequestMapping(value = "/savelimit")
	@ResponseBody
	public Integer saveLimit(String roleid,@RequestList(clazz=FSystemroleLimits.class) List<FSystemroleLimits> list){
	  
		return systemRoleLimitsService.saveLimit(roleid,list);
	}
	
	//@SystemLogs("查询角色权限树形菜单")
	@RequestMapping(value = "/getmodultree")
	@ResponseBody
	public List<TreeNode> getModulTree(String roleid){
		return systemRoleLimitsService.getModulTree(roleid);
	}
}
