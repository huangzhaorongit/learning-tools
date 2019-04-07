package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.domain.FArea;
import com.distribution.wamoli.service.IAreaService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/platform/systemarea")
public class SystemAreaController {

	 @Autowired
	private IAreaService systemareaService;
	
	//@SystemLogs("查询区域树形菜单")
	@RequestMapping(value="/gettree")
	@ResponseBody
	public List<Map<String,Object>> getAreaTree(TreeParams params){
		return systemareaService.getAreaTree(params);
	}
//	
//	//@SystemLogs("查询系统模块树形菜单")
//	@RequestMapping(value="/getallmodule")
//	@ResponseBody
//	public List<TreeNode> getAllModule(){
//		return systemareaService.getAllModule();
//	}
	
	//@SystemLogs("查询区域信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public Map<String,Object> getAreaInfo(String areaid){
		return systemareaService.getAreaInfo(areaid);
	}
	
	//@SystemLogs("添加或修改区域信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public FArea saveOrUpdate(FArea bean){
		return systemareaService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("删除区域信息")
	@RequestMapping(value="/delete")
	@ResponseBody
	public String deleteArea(String Areaid){
		return systemareaService.deleteArea(Areaid);
	}
	
	//@SystemLogs("修改区域节点的排列顺序")
	@RequestMapping(value="/savemoveorder")
	@ResponseBody
	public String saveMoveOrder(String list){
		
		 Gson gson=new Gson();	
		 List<FArea> resultlist=gson.fromJson(list,  new TypeToken<List<FArea>>(){}.getType());  
		systemareaService.saveMoveOrder(resultlist);
		return "true";
	}
}
