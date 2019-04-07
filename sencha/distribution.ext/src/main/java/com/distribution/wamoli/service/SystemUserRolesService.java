package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserRoles;

public interface SystemUserRolesService extends IService<FSystemuserRoles>{


	//@SystemLogs("查询系统用户列表")
	public List<Map<String, Object>> getUserList(FSystemuser user);

	//@SystemLogs("查询系统用户有关联的系统角色列表")
	public List<Map<String, Object>> getUserRoleYes(FSystemrole role , FSystemuser user);

	//@SystemLogs("查询系统用户没有关联的系统角色列表")
	public List<Map<String, Object>> getUserRoleNo(FSystemrole role , FSystemuser user) ;
	//@SystemLogs("删除用户角色信息")
	public String deleteSystemUserRoles(FSystemuserRoles bean);

	//@SystemLogs("添加用户角色信息")
	public FSystemuserRoles saveSystemUserRoles(FSystemuserRoles bean);

//	@SystemLogs("查询系统角色列表")
	public List<Map<String , Object>> getRoleList(FSystemrole bean );

	///@SystemLogs("查询系统角色有关联的系统用户")
	public List<Map<String, Object>> getUserYesList(FSystemuser user , FSystemrole role);
	//@SystemLogs("查询系统角色没有关联的系统用户")
	public List<Map<String, Object>> getUserNoList(FSystemrole role , FSystemuser user);


}
