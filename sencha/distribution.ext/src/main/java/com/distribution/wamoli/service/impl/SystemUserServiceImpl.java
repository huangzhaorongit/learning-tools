package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemUserService;
import com.distribution.wamoli.mapper.FSystemuserMapper;
import com.distribution.wamoli.domain.FSystemuser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemUserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemUserServiceImpl extends BaseService<FSystemuser> implements SystemUserService {

	@Autowired
	private FSystemuserMapper fSystemuserMapper;

    @Autowired
    private PasswordEncoder encoder;

//	@SystemLogs("查询系统用户列表")
	public List<Map<String, Object>> getUserList(FSystemuser bean) {
		String sql = "select b.companyname, a.* "
				   +" from f_systemuser a "
				   +" inner join f_company b on a.companyid = b.companyid"
				   +" where a.companyid = '"+Local.getCompanyid()+"'";
		if(!StringUtils.isEmpty(bean.getUsername())){
			sql+=" and (a.usercode like '%"+bean.getUsername()+"%' or a.username like '%"+bean.getUsername()+"%') ";
		}
		sql+=" order by usercode";
		//return sqlMapper.selectList(QueryHelper.getQuerySql(sql));

		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("添加或修改用户信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemuser saveOrUpdate(FSystemuser bean){
		boolean add = StringUtils.isEmpty(bean.getUserid());
		if(add){
			bean.setUserid(IDUtils.getUuid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			bean.setIslongvalid("1");
			bean.setPassword(encoder.encode(bean.getPassword()));
			bean.setCompanyid(Local.getCompanyid());
			fSystemuserMapper.insert(bean);

		}else{
			if(!StringUtils.isEmpty(bean.getPassword())){
			    bean.setPassword(encoder.encode(bean.getPassword()));
			}else{
				bean.setPassword(null);
			}
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			fSystemuserMapper.updateByPrimaryKeySelective(bean);
		}
		bean.setPassword(null);
		return bean;
	}

	//@SystemLogs("批量删除系统用户信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public Integer deleteUser(List<FSystemuser> users) {
		for (int i = 0; i < users.size(); i++) {
			FSystemuser bean = users.get(0);

			String userlimit_sql = " delete  from  f_systemuser_limits  where userid =  '"+bean.getUserid()+"'";
			String userrole_sql = " delete from f_systemuser_roles where userid = '"+bean.getUserid()+"'";
			sqlMapper.delete(userlimit_sql);
			sqlMapper.delete(userrole_sql);
		}
		return batchDeleteByPrimaryKey(mapper, users);
	}

	//@SystemLogs("查询系统用户信息")
	public Map<String, Object> getInfo(String userid) {
		String sql = "select  a.* ,b.companyname,c.personnelname "
				+" from f_systemuser a "
				+" inner join f_company b on a.companyid = b.companyid "
				+" left join f_personnel  c on a.personnelid = c.personnelid "
				+" where a.userid  = '"+userid+"'";
		return sqlMapper.selectOne(sql);
	}

}
