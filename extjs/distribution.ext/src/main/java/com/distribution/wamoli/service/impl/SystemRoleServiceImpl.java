package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemRoleService;
import com.distribution.wamoli.mapper.FSystemroleMapper;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemrole;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemRoleServiceImpl extends BaseService<FBasecodetype> implements  SystemRoleService{


	@Autowired
	private FSystemroleMapper mapper;

	//@SystemLogs("查询系统角色列表")
	public List<FSystemrole> getSystemRoleList(FSystemrole bean) {
		String sql = " select * from  f_systemrole r"
			 	   + " where isvalid = '1' ";
		if(!StringUtils.isEmpty(bean.getRolename())){
			sql+=" and r.rolename like '%"+bean.getRolename()+"%'or r.rolecode like '%"+bean.getRolename()+"%'";
		}
		sql+="order by rolecode asc";

		return sqlMapper.selectList(sql,FSystemrole.class);
	}

	//@SystemLogs("添加或修改系统角色信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemrole saveorupdate(FSystemrole bean){
		boolean add = StringUtils.isEmpty(bean.getRoleid());
		if(add){
			bean.setRoleid(IDUtils.getUuid());
			bean.setCompanyid(Local.getCompanyid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.insert(bean);
		//	wfservice.saveGroup(bean.getRolecode()); //添加角色
		}else{
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

//	@SystemLogs("查询系统角色信息")
	public Map<String, Object> getFSystemroleInfo(FSystemrole bean){
		String sql = " select r.roleid as id , r.rolecode  , r.rolename , r.companyid , r.roledescription ,r.isvalid ,c.companyname "
			   + " from  f_systemrole r left join f_company c on r.companyid = c.companyid "
			   + " where r.roleid = '" +bean.getRoleid()+ "'";
		return sqlMapper.selectOne(sql);
	}

    //	@SystemLogs("批量删除系统角色信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteFSystemRole(List<FSystemrole> list) {
		for (int i = 0; i <list.size(); i++) {
			FSystemrole bean = list.get(i);
			//wfservice.deleteGroup(mapper.selectOne(bean).getRolecode());//删除工作流用户
			mapper.delete(bean);
			String rolelimit_sql = "delete from  f_systemrole_limits  where roleid = '"+bean.getRoleid()+"'";
			sqlMapper.delete(rolelimit_sql);
			String userroles_sql = "delete from f_systemuser_roles where  roleid = '"+bean.getRoleid()+"'";
			sqlMapper.delete(userroles_sql);
		}
		return "1";
	}

}
