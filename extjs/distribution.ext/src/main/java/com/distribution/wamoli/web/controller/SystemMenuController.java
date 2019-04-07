package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FSystemmenu;
import com.distribution.wamoli.service.SystemMenuService;


@Controller
@RequestMapping("/platform/systemmenu")
public class SystemMenuController {

	 @Autowired
	private SystemMenuService systemMenuService;
	
	//@SystemLogs("查询系统菜单树形菜单")
	@RequestMapping(value="/gettree")
	@ResponseBody
	public List<Map<String,Object>> getMenuTree(TreeParams params){
		return systemMenuService.getMenuTree(params);
	}
	
	//@SystemLogs("查询系统模块树形菜单")
	@RequestMapping(value="/getallmodule")
	@ResponseBody
	public List<TreeNode> getAllModule(){
		return systemMenuService.getAllModule();
	}
	
	//@SystemLogs("查询系统菜单信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public Map<String,Object> getMenuInfo(String menuid){
		return systemMenuService.getMenuInfo(menuid);
	}
	
	//@SystemLogs("添加或修改系统菜单信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public FSystemmenu saveOrUpdate(FSystemmenu bean){
		return systemMenuService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("删除系统菜单信息")
	@RequestMapping(value="/delete")
	@ResponseBody
	public String deleteMenu(String menuid){
		return systemMenuService.deleteMenu(menuid);
	}
	
	//@SystemLogs("修改系统菜单节点的排列顺序")
	@RequestMapping(value="/savemoveorder")
	@ResponseBody
	public String saveMoveOrder(@RequestList(clazz=FSystemmenu.class) List<FSystemmenu> list){
		systemMenuService.saveMoveOrder(list);
		return "true";
	}
}
