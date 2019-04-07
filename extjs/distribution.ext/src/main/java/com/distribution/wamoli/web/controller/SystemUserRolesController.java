package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.service.SystemUserRolesService;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserRoles;



@Controller
@RequestMapping("/platform/systemuserroles")
public class SystemUserRolesController {

	 @Autowired
	private SystemUserRolesService systemUserRolesService;

	//@SystemLogs("查询系统用户列表")
	@RequestMapping(value = "/getuserlist")
	@ResponseBody
	public List<Map<String, Object>> getList(FSystemuser user){
		return systemUserRolesService.getUserList(user);
	}

	//@SystemLogs("查询系统用户有关联的系统角色列表")
	@RequestMapping(value = "/getuserroleyes")
	@ResponseBody
	public List<Map<String, Object>> getUserRoleYes(FSystemrole role,FSystemuser user) {
		return systemUserRolesService.getUserRoleYes(role ,user);
	}

	//@SystemLogs("查询系统用户没有关联的系统角色列表")
	@RequestMapping(value = "/getuserroleno")
	@ResponseBody
	public List<Map<String, Object>> getUserRoleNo(FSystemrole role,FSystemuser user) {
		return systemUserRolesService.getUserRoleNo(role,user);
	}

	//@SystemLogs("删除用户角色信息")
	@RequestMapping(value = "/deletesystemuserroles")
	@ResponseBody
	public String deleteSystemUserRoles(FSystemuserRoles bean){
		return systemUserRolesService.deleteSystemUserRoles(bean);
	}

	//@SystemLogs("添加用户角色信息")
	@RequestMapping(value = "/savasystemuserroles")
	@ResponseBody
	public FSystemuserRoles savaSystemUserRoles(FSystemuserRoles bean){
		return systemUserRolesService.saveSystemUserRoles(bean);
	}

	//@SystemLogs("查询系统角色列表")
	@RequestMapping(value = "/getrolelist")
	@ResponseBody
	public List<Map<String, Object>> getRoleList(FSystemrole bean ){
		return systemUserRolesService.getRoleList(bean);
	}

	//@SystemLogs("查询系统角色有关联的系统用户")
	@RequestMapping(value = "/getuseryeslist")
	@ResponseBody
	public List<Map<String, Object>> getUserYesList(FSystemrole role , FSystemuser user){
		return systemUserRolesService.getUserYesList(user, role);
	}

	//@SystemLogs("查询系统角色没有关联的系统用户")
	@RequestMapping(value = "/getusernolist")
	@ResponseBody
	public  List<Map<String, Object>> getUserNoList(FSystemrole role , FSystemuser user){
		return systemUserRolesService.getUserNoList(role, user);
	}

}
