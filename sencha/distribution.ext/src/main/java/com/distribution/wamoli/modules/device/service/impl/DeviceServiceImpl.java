package com.distribution.wamoli.modules.device.service.impl;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.common.utils.SqlHelper.QueryHelper;
import com.distribution.wamoli.modules.device.domain.Device;
import com.distribution.wamoli.modules.device.mapper.DeviceMapper;
import com.distribution.wamoli.modules.device.service.IDeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("deviceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceServiceImpl extends BaseService<Device> implements IDeviceService {

	@Autowired
	private DeviceMapper deviceMapper;

	public List<TreeNode> getTreeList() {
		String sql = "select hd.id,hd.parent_id , hd.name  as text  from hnu_device hd";
		List<TreeNode> dataList = sqlMapper.selectList(sql, TreeNode.class);
		return TreeBuilder.buildListToTree(dataList);
	}

	@Override
	public List<Map<String, Object>> getList(Device bean) {
		String sql = "select * from hnu_device hd where 1=1";

		if (!StringUtils.isEmpty(bean.getParentId())) {
			sql += " and hd.parent_id='" + bean.getParentId() + "' ";

		}


		if(!StringUtils.isEmpty(bean.getName())){
			sql +=" and (hd.name like '%"+bean.getName()+"%' or hd.name like '%"+bean.getName()+"%')";
		}
		return sqlMapper.selectList(QueryHelper.getQuerySql(sql));
	}

	@Override
	public void deleteDevice(List<Device> ids) {
		batchDeleteByPrimaryKey(mapper, ids);

	}

	@Override
	public Device getDeviceInfo(Device bean) {
		String sql = "select * from hnu_device hd where hd.id='" + bean.getId() + "'";
		return sqlMapper.selectOne(sql, Device.class);
	}

	@Override
	public Device saveOrUpdate(Device bean) {
		boolean add = StringUtils.isEmpty(bean.getId());
		if (add) {
			bean.setId(IDUtils.getUuid());

			mapper.insert(bean);
		} else {

			mapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

}
