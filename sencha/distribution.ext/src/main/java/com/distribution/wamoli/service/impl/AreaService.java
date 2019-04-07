package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.domain.FArea;
import com.distribution.wamoli.service.IAreaService;

/**
 * 区域Service
 * @version 2014-05-16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AreaService extends BaseService<FArea> implements IAreaService  {

	public List<FArea> findAll(){
		return null; //UserUtils.getAreaList();
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void saveArea(FArea area) {
		super.save(area);
//		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteArea(FArea area) {
		super.delete(area);
//		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Override
	public String deleteArea(String areaid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getAreaTree(TreeParams params) {
		String sql = "select a.menuid as id,a.menuname,a.menutype,a.icon,a.iconCls,a.iconColor,a.isdisplay,a.parentId,b.modulename,a.custom1 as notdel,"
				   +" (case (select count(1) from f_systemmenu b where b.parentid = a.menuid) when 0 then 1 else 0 end ) as loaded,"
//				   +" (case a.menutype when '00' then 'classicon.gif' else 'sameheight.gif' end ) as icon,"
				   + "(case a.menutype when '00' then 0 else 1 end ) as leaf"
				   +" from f_area a "
				   +" where a.parentid = '"+params.getNode()+"'"
				   +" order by a.orderno";
		return TreeBuilder.updateTreeIcon(sqlMapper.selectList(sql));
	}

	@Override
	public Map<String, Object> getAreaInfo(String areaid) {
		String sql = "select a.menuid,a.menuname,a.menutype,a.parentid,a.icon,a.iconcls,a.iconcolor,a.isdisplay,a.moduleid,b.modulename"
				   +" from f_systemmenu a "
				   +" left join f_systemmodule b on a.moduleid = b.moduleid"
				   +" where a.menuid = '"+areaid+"'";
		return sqlMapper.selectOne(sql);
	}

	@Override
	public FArea saveOrUpdate(FArea bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMoveOrder(List<FArea> resultlist) {
		// TODO Auto-generated method stub

	}

}
