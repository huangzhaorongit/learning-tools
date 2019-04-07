package com.distribution.wamoli.service;

import java.util.List;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemroleLimits;


public interface SystemRoleLimitsService extends IService<FSystemroleLimits>{

	
	//@SystemLogs("查询系统角色列表")
	public List<FSystemrole> getSystemrole(String rolecode) ;
	
	//@SystemLogs("查询角色权限树形菜单")
	public List<TreeNode> getModulTree(String roleid);
	
//	@SystemLogs("修改角色的权限信息")
	public Integer saveLimit(String roleid, List<FSystemroleLimits> list) ;
}
