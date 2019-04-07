package com.distribution.wamoli.service;

import java.util.List;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserLimits;


public interface SystemUserLimitsService extends IService<FSystemuserLimits>{
    

	
//	@SystemLogs("查询系统用户列表")
	public List<FSystemuser> getSystemuserList(String usercode); 
	
//	@SystemLogs("查询用户权限树形菜单")
	public List<TreeNode> getModulTree(String userid);
	
	//@SystemLogs("修改用户权限信息")
	public Integer saveLimit(String userid,List<FSystemuserLimits> list) ;

	//public Integer saveLimit(String userid, BaseArryList<FSystemuserLimits> userArryList);
}
