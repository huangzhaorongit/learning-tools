package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.service.SystemUserRolesService;
import com.distribution.wamoli.mapper.FSystemroleMapper;
import com.distribution.wamoli.mapper.FSystemuserMapper;
import com.distribution.wamoli.mapper.FSystemuserRolesMapper;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserRoles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("systemUserRolesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemUserRolesServiceImpl extends BaseService<FSystemuserRoles>  implements SystemUserRolesService{

	@Autowired
	private FSystemuserRolesMapper mapper;
	@Autowired
	private FSystemroleMapper roleMapper;
	@Autowired
	private FSystemuserMapper userMapper;



	//@SystemLogs("查询系统用户列表")
	public List<Map<String, Object>> getUserList(FSystemuser user){
		String sql = " select u.userid as id , u.usercode , u.username ,u.isvalid  , c .companyname "
				   + " from f_systemuser u left join f_company c on u.companyid = c.companyid "
				   + " where 1=1";
		if(!StringUtils.isEmpty(user.getName())){
			sql+=" and u.username like '%"+user.getName()+"%'or u.usercode like '%"+user.getName()+"%'";
		}
		sql+=" order by usercode ";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统用户有关联的系统角色列表")
	public List<Map<String, Object>> getUserRoleYes(FSystemrole role , FSystemuser user) {
		String sql = " select r.roleid as id, r.rolename , r.rolecode , r.isvalid " +
					 " from f_systemrole r  left join  f_systemuser_roles  ur on r.roleid = ur.roleid "+
					 " where ur.userid = '"+user.getUserid()+"' ";
		if(!StringUtils.isEmpty(role.getRolename())){
			sql+=" and r.rolename like '%"+role.getRolename()+"%'or r.rolecode like '%"+role.getRolename()+"%'";
		}
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统用户没有关联的系统角色列表")
	public List<Map<String, Object>> getUserRoleNo(FSystemrole role , FSystemuser user) {
		String sql = " select r.roleid as id , r.rolecode , r.rolename"
				   + " from  f_systemrole r "
				   + " where r.roleid not in"
				   + " (select ur.roleid from f_systemuser_roles ur where ur.userid = '"+user.getUserid()+"') ";
		if(!StringUtils.isEmpty(role.getRolename())){
			sql+=" and r.rolename like '%"+role.getRolename()+"%'or r.rolecode like '%"+role.getRolename()+"%'";
		}
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("删除用户角色信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteSystemUserRoles(FSystemuserRoles bean) {
		String sql = " delete from f_systemuser_roles "
			       + " where roleid = '"+bean.getRoleid()+"' and userid = '" +bean.getUserid()+ "' ";
		if(!StringUtils.isEmpty(bean.getRoleid())&&!StringUtils.isEmpty(bean.getUserid())){
			//删除工作流用户和组关联
			String usercode = userMapper.selectOne(new FSystemuser(bean.getUserid())).getUsername();
			String rolecode = roleMapper.selectOne(new FSystemrole(bean.getRoleid())).getRolecode();

			sqlMapper.delete(sql);
			return "1";
		}
		return null;
	}

	//@SystemLogs("添加用户角色信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemuserRoles saveSystemUserRoles(FSystemuserRoles bean) {
		if(!StringUtils.isEmpty(bean.getRoleid())&&!StringUtils.isEmpty(bean.getUserid())){
			bean.setCompanyid(Local.getCompanyid());
			//工作流用户和组关联
			String usercode = userMapper.selectOne(new FSystemuser(bean.getUserid())).getUsername();
			String rolecode = roleMapper.selectOne(new FSystemrole(bean.getRoleid())).getRolecode();

			mapper.insert(bean);
			return bean;
		}
		return null;
	}

	//@SystemLogs("查询系统角色列表")
	public List<Map<String , Object>> getRoleList(FSystemrole bean ){
		String sql = "select r.roleid as id , r.rolecode , r.rolename  from   f_systemrole  r where 1 = 1 ";
		if(!StringUtils.isEmpty(bean.getRolename())){
			sql+=" and r.rolecode like '%"+bean.getRolename()+"%' or r.rolename like '%"+bean.getRolename()+"%'";
		}
		sql+=" order by r.createdate desc";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统角色有关联的系统用户")
	public List<Map<String, Object>> getUserYesList(FSystemuser user , FSystemrole role){
		String sql = " select  u.userid as id ,u.usercode , u.username"
				   + " from  f_systemuser u left join  f_systemuser_roles ur on u.userid = ur.userid where 1 = 1 "
		           + " and ur.roleid='"+role.getRoleid()+"'";
		if(!StringUtils.isEmpty(role.getRoleid())&&!StringUtils.isEmpty(user.getName())){
			sql+=" and u.usercode like '%"+user.getName()+"%' or u.username like '%"+user.getName()+"%' ";
		}
		sql+=" order by u.createdate desc";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询系统角色没有关联的系统用户")
	public List<Map<String, Object>> getUserNoList(FSystemrole role , FSystemuser user){
		String sql = " select  u.userid as id ,u.usercode , u.username"
			       + " from  f_systemuser u where u.userid not in "
		           + " (select ur.userid from f_systemuser_roles ur where ur.roleid = '"+role.getRoleid()+"' ) ";
		if(!StringUtils.isEmpty(user.getName())){
			sql+=" and u.usercode like '%"+user.getName()+"%' or u.username like '%"+user.getName()+"%'";
		}
		sql+="order by u.createdate desc";
		return sqlMapper.selectList(sql);
	}


}
