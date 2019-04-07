package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FPersonnel;


public interface PersonnelService extends IService<FPersonnel>  {

	
	//@SystemLogs("添加或修改人员信息")
	public FPersonnel saveOrUpdate(FPersonnel bean);

	//@SystemLogs("查询人员列表信息")
	public List<Map<String,Object>> getList(FPersonnel bean);

	//@SystemLogs("查询人员信息")
	public FPersonnel getPersonnelInfo(FPersonnel bean);
	
//	@SystemLogs("批量删除人员")
	public void deletePersonnel(List<FPersonnel> ids);
	
	//@SystemLogs("查询部门树型菜单")
	public List<TreeNode> getTreeList();
	
	//@SystemLogs("批量修改人员所属部门")
	public void removeTot(List<FPersonnel> ids);
	
	//@SystemLogs("上传人员照片信息")
	public String uploadPhoto(MultipartHttpServletRequest re,FPersonnel bean);
	
	public void cleanPhoto(String personnelid);
}
