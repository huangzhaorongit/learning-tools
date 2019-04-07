package com.distribution.wamoli.modules.device.service;

import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.modules.device.domain.Device;

import java.util.List;
import java.util.Map;



public interface IDeviceService extends IService<Device> {

	public List<TreeNode> getTreeList();


	public List<Map<String,Object>> getList(Device bean);


	public void deleteDevice(List<Device> ids);


	public Device getDeviceInfo(Device bean);


	public Device saveOrUpdate(Device bean);
}
