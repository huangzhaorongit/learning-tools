package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemrole;

public interface SystemRoleService extends IService<FBasecodetype>{



	//@SystemLogs("查询系统角色列表")
	public List<FSystemrole> getSystemRoleList(FSystemrole bean) ;
//	@SystemLogs("添加或修改系统角色信息")
	public FSystemrole saveorupdate(FSystemrole bean);

	//@SystemLogs("查询系统角色信息")
	public Map<String, Object> getFSystemroleInfo(FSystemrole bean);

	//@SystemLogs("批量删除系统角色信息")
	public String deleteFSystemRole(List<FSystemrole> list) ;
}
