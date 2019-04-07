package com.distribution.wamoli.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.common.utils.FileUtils;
import com.distribution.wamoli.service.PersonnelService;
import com.distribution.wamoli.domain.FPersonnel;


@Controller
@RequestMapping("/platform/personnel")
public class PersonnelController {

	 @Autowired
	private PersonnelService personnelService;
	
//	@SystemLogs("添加或修改人员信息")
	@RequestMapping(value="/saveorupdate")
	@ResponseBody	
	public FPersonnel saveOrUpdate(FPersonnel bean){
		return personnelService.saveOrUpdate(bean);
	}
	
	//@SystemLogs("查询人员列表信息")
	@RequestMapping(value="/getlist")
	@ResponseBody
	public List<Map<String,Object>> getList(FPersonnel bean){
		return personnelService.getList(bean);	
	}
	
//	@SystemLogs("查询人员信息")
	@RequestMapping(value="/getinfo")
	@ResponseBody
	public FPersonnel getPersonnelInfo(FPersonnel bean){
		return personnelService.getPersonnelInfo(bean);
	}
	
//	@SystemLogs("批量删除人员")
	@RequestMapping(value="/delete")
	@ResponseBody
	public void deletePersonnel(String ids){
		 Gson gson=new Gson();	
		 List<FPersonnel> resultlist=gson.fromJson(ids,  new TypeToken<List<FPersonnel>>(){}.getType());  
		personnelService.deletePersonnel(resultlist);
	}
	
	//@SystemLogs("查询部门树型菜单")
	@RequestMapping(value="/gettreelist")
	@ResponseBody
	public List<TreeNode> getTreeList(){
		return personnelService.getTreeList();
	}
	
//	@SystemLogs("批量修改人员所属部门")
	
	@RequestMapping(value="/removetot")
	@ResponseBody
	public void removeTot(@RequestList(clazz=FPersonnel.class) List<FPersonnel> ids){
		personnelService.removeTot(ids);
	}
	
//	@SystemLogs("上传人员照片信息")
	@RequestMapping(value="/uploadphoto")
	@ResponseBody
	public String uploadPhoto(HttpServletRequest request,HttpServletResponse response,FPersonnel bean){
		MultipartHttpServletRequest re=(MultipartHttpServletRequest)request;
		return personnelService.uploadPhoto(re, bean);
	}
	
	//@SystemLogs("获取人员照片流")
	@RequestMapping(value="/getphoto")
	@ResponseBody
	public void getPhoto(HttpServletRequest request,HttpServletResponse response,String photoid){
		if(StringUtils.isEmpty(photoid))photoid = "default";
		File path = Local.getLocalSpace().getPeronnelPhotoSpace(Local.getCompanyid());
		File photofile = new File(path,photoid);
		try {
			if(!photofile.isFile()){
				photofile = new File(path,"default");
				if(!photofile.isFile()){
					File input = new File(Local.getProjectSpace().getRoot(),"resources/images/system/photo.jpg");
					FileUtils.copy(input,photofile);
				}
			}
			FileUtils.copy(photofile,response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//@SystemLogs("清除人员照片流")
	@RequestMapping(value="/cleanphoto")
	@ResponseBody
	public void cleanPhoto(String personnelid){
		personnelService.cleanPhoto(personnelid);
	}
}
