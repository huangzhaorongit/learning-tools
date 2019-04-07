package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.service.SystemRoleService;

@Controller
@RequestMapping("/platform/systemrole")
public class SystemRoleController {

	 @Autowired
	private SystemRoleService systemRoleService;

	//@SystemLogs("查询系统角色列表")
	@RequestMapping(value = "/getlist")
	@ResponseBody
	public List<FSystemrole> getSystemRoleList(FSystemrole bean){
		return systemRoleService.getSystemRoleList(bean);
	}

	//@SystemLogs("查询系统角色信息")
	@RequestMapping(value = "/getinfo")
	@ResponseBody
	public Map<String, Object> getFStstemRoleInfo(FSystemrole bean){
		return systemRoleService.getFSystemroleInfo(bean);
	}

	//@SystemLogs("添加或修改系统角色信息")
	@RequestMapping(value = "/saveorupdate")
	@ResponseBody
	public FSystemrole saveorupdate(FSystemrole bean){
		return systemRoleService.saveorupdate(bean);
	}

	//@SystemLogs("批量删除系统角色信息")
	@RequestMapping(value = "/deletesystemrole")
	@ResponseBody
	public String deleteFSystemRole(@RequestList(clazz=FSystemrole.class) List<FSystemrole> list ){


		return systemRoleService.deleteFSystemRole(list);
	}
}
