package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.bean.ResultBean;
import com.distribution.wamoli.common.bean.SystemUrlBean;
import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.utils.ProjectUtils;
import com.distribution.wamoli.domain.FPersonnel;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.service.SystemFrameService;




@Controller
@RequestMapping("/platform/systemframe")
public class SystemFrameController {

	@Autowired
	private SystemFrameService systemFrameService;

	// @SystemLogs("获取系统左侧树形菜单")
	@RequestMapping(value = "/getmenutree")
	@ResponseBody
	public List<TreeNode> getMenuTree() {
		List<TreeNode> dataList = systemFrameService.getMenuTree();

		for (TreeNode treenode : dataList) {

			if (treenode.getHasChart()!=null&&treenode.getHasChart().equals("1")) {
				treenode.setUrl(treenode.getUrl() + "Chart");
			}

			if (treenode.getHasGrid()!=null&&treenode.getHasGrid().equals("1")) {
				treenode.setUrl(treenode.getUrl() + "Grid");
			}
		}

		return TreeBuilder.buildListToTree(dataList);
	}

	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Map<String,Object> getUserInfo(String userid,String personnelid){
		return systemFrameService.getInfo(userid,personnelid);
	}
	
	//@SystemLogs("修改用户及人员信息")
	@RequestMapping("/updateUser")
	@ResponseBody
	public ResultBean updateUser(FSystemuser user,FPersonnel personnel,String opassword,String npassword,String qpassword){
		return systemFrameService.updateUser(user,personnel,opassword,npassword,qpassword);
	}
	
	//@SystemLogs("获取系统URL对象")
	@RequestMapping(value="/getsystemurls")
	@ResponseBody
    public Map<String,SystemUrlBean> getSystemUrls() {
		return ProjectUtils.getSystemUrls(null);
    }
	
	//@SystemLogs("获取当前用户指定模块的权限")
	@RequestMapping(value="/getsystemlimits")
	@ResponseBody
    public List<Map<String,Object>> getSystemLimits(String modulurl) {
		return systemFrameService.getSystemLimits(Local.getUserid(),modulurl);
    }
	
	//@SystemLogs("获取系统在线用户")
	@RequestMapping(value="/getonlineusers")
	@ResponseBody
    public List<TreeNode> getOnlineUsers() {
		return systemFrameService.getOnlineUsers();
    }
	
//	@SystemLogs("获取系统离线用户")
	@RequestMapping(value="/getofflineusers")
	@ResponseBody
    public List<TreeNode> getOfflineUsers() {
		return systemFrameService.getOfflineUsers();
    }
	
}