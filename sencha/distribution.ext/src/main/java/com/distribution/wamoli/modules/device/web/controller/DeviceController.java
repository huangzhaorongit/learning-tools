package com.distribution.wamoli.modules.device.web.controller;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.modules.device.domain.Device;
import com.distribution.wamoli.modules.device.service.IDeviceService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/bussiness/device")
public class DeviceController {

	 @Autowired
	private IDeviceService deviceService;


	@RequestMapping(value="/gettreelist")
	@ResponseBody
	public List<TreeNode> getTreeList(){
		return deviceService.getTreeList();
	}



	@RequestMapping(value="/getlist")
	@ResponseBody
	public List<Map<String,Object>> getList(Device bean){
		return deviceService.getList(bean);
	}


	@RequestMapping(value="/saveorupdate")
	@ResponseBody
	public Device saveOrUpdate(Device bean){
		return deviceService.saveOrUpdate(bean);
	}



	@RequestMapping(value="/getinfo")
	@ResponseBody
	public Device getDeviceInfo(Device bean){
		return deviceService.getDeviceInfo(bean);
	}


	@RequestMapping(value="/delete")
	@ResponseBody
	public void deleteDevice(String ids){
		 Gson gson=new Gson();
		 List<Device> resultlist=gson.fromJson(ids,  new TypeToken<List<Device>>(){}.getType());
		 deviceService.deleteDevice(resultlist);
	}

}
