package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FOrganization;


public interface OrganizationService extends IService<FOrganization> {

	
	
	//@SystemLogs("添加或修改内部组织信息")
	public FOrganization saveOrUpdate(FOrganization bean);
	
	//@SystemLogs("查询内部组织树形菜单")
	public List<Map<String, Object>> getOrganizationTree(TreeParams params);
	//@SystemLogs("查询内部组织信息")
	public Map<String, Object> getOrganizationInfo(String orgid);
	
	//@SystemLogs("删除内部组织信息")
	public String deleteOrganization(String orgid);
	
	//@SystemLogs("修改内部组织节点的排列顺序")
	public void saveMoveOrder(List<FOrganization> list) ;
}
