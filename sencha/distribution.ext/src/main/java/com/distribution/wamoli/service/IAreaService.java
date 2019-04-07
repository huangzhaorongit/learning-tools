package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FArea;


public interface IAreaService extends IService<FArea>{
	public List<FArea> findAll();
	public void saveArea(FArea area);
	public String deleteArea(String areaid);
	public List<Map<String, Object>> getAreaTree(TreeParams params);
	public Map<String,Object> getAreaInfo(String areaid);
	public FArea saveOrUpdate(FArea bean);
	public void saveMoveOrder(List<FArea> resultlist);
}
