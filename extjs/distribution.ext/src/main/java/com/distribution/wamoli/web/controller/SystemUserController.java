package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.service.SystemUserService;



@Controller
@RequestMapping("/platform/systemuser")
public class SystemUserController {

	 @Autowired
	private SystemUserService systemUserService;
	
	//@SystemLogs("查询系统用户列表")
	@RequestMapping(value="/getlist")
	@ResponseBody

	public List<Map<String,Object>> getUserList(FSystemuser bean){
		return systemUserService.getUserList(bean);
	}
	
	//@SystemLogs("查询系统用户信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public Map<String, Object> getInfo(String userid){
		Map<String, Object> map = systemUserService.getInfo(userid);
		map.remove("password");
		return map;
	}
	
	//@SystemLogs("添加或修改用户信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public FSystemuser saveOrUpdate(FSystemuser bean){
		return systemUserService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("批量删除系统用户信息")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Integer deleteUser(@RequestList(clazz=FSystemuser.class) List<FSystemuser> users){
		return systemUserService.deleteUser(users);
	}
}
