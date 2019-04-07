package com.distribution.wamoli.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserLimits;
import com.distribution.wamoli.service.SystemUserLimitsService;


@Controller
@RequestMapping("/platform/systemuserlimits")
public class SystemUserLimitsController {
	
	 @Autowired
	private SystemUserLimitsService systemUserLimitsService;
	
//	@SystemLogs("查询系统用户列表")
	@RequestMapping(value = "/getsystemuserlist")
	@ResponseBody
	public List<FSystemuser> getSystemuserList(String usercode) {
		return systemUserLimitsService.getSystemuserList(usercode);
	}
	
	//@SystemLogs("查询用户权限树形菜单")
	@RequestMapping(value = "/getmodultree")
	@ResponseBody
	public List<TreeNode> getModulTree(String userid){
		return systemUserLimitsService.getModulTree(userid);
	}
	
	//@SystemLogs("修改用户权限信息")
	@RequestMapping(value = "/savelimit")
	@ResponseBody
	public Integer saveLimit(String userid,@RequestList(clazz=FSystemuserLimits.class) List<FSystemuserLimits> list){
		
		return systemUserLimitsService.saveLimit(userid,list);
	}
	

}
