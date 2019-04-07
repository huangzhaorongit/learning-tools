package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemcfgService;
import com.distribution.wamoli.mapper.FSystemcfgMapper;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemcfg;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemcfgService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemcfgServiceImpl  extends BaseService<FBasecodetype> implements SystemcfgService{

	@Autowired
	private FSystemcfgMapper fSystemcfgMapper;





	@Resource
	private FSystemcfgMapper mapper;

	/***************************标准的查询************************************/

	public List<FSystemcfg> getList(FSystemcfg bean){
		bean.setUserid(Local.getUserid());
		return  mapper.select(bean);
	}

	public FSystemcfg getInfo(FSystemcfg bean){
		bean.setUserid(Local.getUserid());
		return mapper.selectOne(bean);
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String saveOrUpdate(FSystemcfg bean){
		delete(bean);
		bean.setId(IDUtils.getUuid());
		bean.setCreater(Local.getUsername());
		bean.setCreatedate(DateUtils.getCurrentTime());
		bean.setUserid(Local.getUserid());
		String where = " type = '"+bean.getType()+"'" + " and userid = '"+Local.getUserid()+"' ";
		bean.setOrderno((int)selectMax("f_systemcfg","orderno",where)+1);
		mapper.insert(bean);
		return bean.getId();
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public Integer delete(FSystemcfg bean){
		if(StringUtils.isEmpty(bean.getId())){
			bean.setUserid(Local.getUserid());
			return mapper.delete(bean);
		}else{
			return mapper.deleteByPrimaryKey(bean);
		}
	}


	/***************************个性化的查询************************************/

	public List<Map<String,Object>> getMenucfgList(FSystemcfg bean){
		String sql = "select b.menuid as id,b.menuname text,b.parentid,c.moduleurl as url,b.icon,b.iconCls,"
				   + " b.iconColor,c.moduletype as type,a.id as themecfgid,1 as leaf "
				   + " from f_systemcfg a "
				   + " inner join f_systemmenu b on a.`value` = b.menuid "
				   + " inner join f_systemmodule c on b.moduleid = c.moduleid "
				   + " where a.type = '"+bean.getType()+"' and a.userid = '"+Local.getUserid()+"'";
		if(!StringUtils.isEmpty(bean.getValue())){
			sql+=" and a.`value` = '"+bean.getValue()+"'";
		}
		sql +=" order by a.orderno";
		return sqlMapper.selectList(sql);
	}

}
