package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.bean.TreeParams;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemModuleService;
import com.distribution.wamoli.mapper.FModuledatastateMapper;
import com.distribution.wamoli.mapper.FModuleoperateMapper;
import com.distribution.wamoli.mapper.FSystemmoduleMapper;
import com.distribution.wamoli.domain.FModuledatastate;
import com.distribution.wamoli.domain.FModuleoperate;
import com.distribution.wamoli.domain.FSystemmodule;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemModuleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemModuleServiceImpl extends BaseService<FSystemmodule> implements SystemModuleService{


	@Autowired
	private FSystemmoduleMapper mapper;

	@Autowired
	private FModuleoperateMapper operateMapper;

	@Autowired
	private FModuledatastateMapper datastateMapper;


   //@SystemLogs("查询系统模块树形菜单")
	public List<Map<String,Object>> getModuleTree(TreeParams params){
		String sql = "select a.moduleid as id,a.modulename,a.modulecode,a.moduletype,a.moduleurl,a.parentid,a.isdisplay,"
				   +" (case (select count(1) from f_systemmodule b where b.parentid = a.moduleid)when 0 then 1 else 0 end ) as leaf"
				   +" from f_systemmodule a where a.parentid = '"+params.getNode()+"'"
				   +" order by a.modulecode";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统模块信息")
	public FSystemmodule getModuleInfo(FSystemmodule bean){
		return mapper.selectOne(bean);
	}

	//@SystemLogs("查询系统模块操作列表")
	public List<FModuleoperate> getModuleOperateList(FModuleoperate bean){
		return operateMapper.select(bean);
	}

//	@SystemLogs("查询系统模块数据状态列表")
	public List<FModuledatastate> getModuleDataStateList(FModuledatastate bean){
		return datastateMapper.select(bean);
	}

	//@SystemLogs("删除系统模块信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteModule(String moduleid){
		List<Map<String,Object>> list = sqlMapper.selectList("select count(1) C from f_systemmodule where parentid  = '"+moduleid+"' ");
		String c = String.valueOf(list.get(0).get("C"));
		if(!c.equals("0"))return "-1";
		int count = sqlMapper.delete("delete from f_systemmodule where moduleid = '"+moduleid+"'");
		return String.valueOf(count);
	}

//	@SystemLogs("添加或修改系统模块信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String saveOrUpdate(FSystemmodule bean,List<FModuleoperate> list1,List<FModuledatastate> list2){
		boolean add = StringUtils.isEmpty(bean.getModuleid());
		if(add){
			bean.setModuleid(IDUtils.getUuid());
			bean.setCompanyid(Local.getCompanyid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			bean.setOrderno((int)selectMax("f_systemmodule","Orderno","parentid = '"+bean.getParentid()+"'")+1);
			mapper.insert(bean);
		}else{
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.updateByPrimaryKeySelective(bean);
			sqlMapper.delete("delete from f_moduleoperate where moduleid = '"+bean.getModuleid()+"'");
			sqlMapper.delete("delete from f_moduledatastate where moduleid = '"+bean.getModuleid()+"'");
		}
		for (int i = 0; i < list1.size(); i++) {
			list1.get(i).setOrderno(i);
			list1.get(i).setModuleid(bean.getModuleid());
			operateMapper.insert(list1.get(i));
		}
		for (int i = 0; i < list2.size(); i++) {
			list2.get(i).setOrderno(i);
			list2.get(i).setModuleid(bean.getModuleid());
			datastateMapper.insert(list2.get(i));
		}
		return bean.getModuleid();
	}

}
