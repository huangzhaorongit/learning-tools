package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemmenu;

public interface SystemMenuService extends IService<FSystemmenu> {

	
	
	
	//@SystemLogs("查询系统菜单树形菜单")
	public List<Map<String,Object>> getMenuTree(TreeParams params);
	
	//@SystemLogs("查询系统菜单信息")
	public Map<String,Object> getMenuInfo(String menuid);
	
//	@SystemLogs("查询系统模块树形菜单信息")
	public List<TreeNode> getAllModule();
	
//	@SystemLogs("添加或修改系统菜单信息")
	public FSystemmenu saveOrUpdate(FSystemmenu bean);
	
//	@SystemLogs("删除系统菜单信息")
	public String deleteMenu(String menuid);
//	@SystemLogs("修改系统菜单节点的排列顺序")
	public void saveMoveOrder(List<FSystemmenu> list);
}
