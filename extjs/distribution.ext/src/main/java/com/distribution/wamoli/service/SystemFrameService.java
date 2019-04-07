package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FPersonnel;
import com.distribution.wamoli.domain.FSystemuser;


public interface SystemFrameService extends IService<FBasecodetype> {


	//@SystemLogs("获取系统左侧树形菜单")
	public List<TreeNode> getMenuTree();
	
	
//	@SystemLogs("修改用户及人员信息")
	public ResultBean updateUser(FSystemuser user, FPersonnel personnel, String opassword, String npassword, String qpassword) ;


	//@SystemLogs("获取登录用户及人员信息")
	public Map<String, Object> getInfo(String userid, String personnelid);
	
	//@SystemLogs("获取当前用户指定模块的权限")
	public List<Map<String,Object>> getSystemLimits(String userid,String modulurl) ;


	public List<TreeNode> getOnlineUsers();


	public List<TreeNode> getOfflineUsers();


}
