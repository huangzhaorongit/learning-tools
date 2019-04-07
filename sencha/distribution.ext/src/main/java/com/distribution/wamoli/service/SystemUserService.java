package com.distribution.wamoli.service;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemuser;

@Service
public interface SystemUserService extends IService<FSystemuser>{



//	@SystemLogs("添加或修改用户信息")
	public FSystemuser saveOrUpdate(FSystemuser bean);

//	@SystemLogs("批量删除系统用户信息")
	public Integer deleteUser(List<FSystemuser> users);

//	@SystemLogs("查询系统用户信息")
	public Map<String, Object> getInfo(String userid);

	public List<Map<String, Object>> getUserList(FSystemuser bean);
}
