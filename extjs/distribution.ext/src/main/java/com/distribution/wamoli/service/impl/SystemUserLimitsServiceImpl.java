package com.distribution.wamoli.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.service.SystemUserLimitsService;
import com.distribution.wamoli.mapper.FSystemuserLimitsMapper;
import com.distribution.wamoli.domain.FSystemuser;
import com.distribution.wamoli.domain.FSystemuserLimits;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemUserLimitsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemUserLimitsServiceImpl extends BaseService<FSystemuserLimits> implements   SystemUserLimitsService{







	@Autowired
    private FSystemuserLimitsMapper mapper;

	//@SystemLogs("查询系统用户列表")
	public List<FSystemuser> getSystemuserList(String usercode) {
		String sql = "select * from f_systemuser f where f.isvalid='1' and f.islocked='0' ";
		if(!StringUtils.isEmpty(usercode)){
			sql+= "and f.username like'%"+usercode+"%' or f.usercode like'%"+usercode+"%'";
		}
		return sqlMapper.selectList(sql,FSystemuser.class);
	}

	//@SystemLogs("查询用户权限树形菜单")
	public List<TreeNode> getModulTree(String userid) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if(!StringUtils.isEmpty(userid)){
			String sql = "select a.menuid as id,a.menuname as text,a.parentid,a.iconcls,a.orderno,a.menuid as param1,'00' as param2,'00' param3,"
					+" 		case when (select count(b.menuid) from f_systemuser_limits b where b.menuid = a.menuid and  b.operatecode='00' and b.userid='"+userid+"')>0 then 1 else 0 end as param4,"
					+" 		case when ((select count(c.menuid) from f_systemrole_limits c where c.menuid=a.menuid and c.operatecode='00' and c.roleid in (select d.roleid from f_systemuser_roles d where d.userid='"+userid+"')))>0  then 1 else 0 end as param5"
					+" from f_systemmenu a where a.companyid='"+Local.getCompanyid()+"' and a.isdisplay='1' "
					+" union all "
				+" select "+sf.link(new String[]{"e.menuid","'_'","d.operatecode"})+" as id,d.operatename as text,e.menuid as parentid,"
				//+" select  CONCAT(e.menuid ,'_',d.operatecode)       as id,d.operatename as text,e.menuid as parentid,"
					+" 		'' as iconcls,e.orderno,e.menuid as param1,d.operatecode as param2,'01' param3,"
					+" 		case when (select count(b.menuid) from f_systemuser_limits b where b.menuid = e.menuid and  b.operatecode=d.operatecode and b.userid='"+userid+"')>0 then 1 else 0 end as param4,"
					+" 		case when ((select count(c.menuid) from f_systemrole_limits c where c.menuid=e.menuid and c.operatecode=d.operatecode and c.roleid in (select d.roleid from f_systemuser_roles d where d.userid='"+userid+"')))>0  then 1 else 0 end as param5"
					+" from f_moduleoperate d  "
					+" inner join f_systemmenu e on e.moduleid = d.moduleid "
					+" order by orderno";
			list = sqlMapper.selectList(sql, TreeNode.class);
			for (int i = 0; i < list.size(); i++) {
				TreeNode tn = list.get(i);
				double userLimits = tn.getParam4();
				double roleLimits = tn.getParam5();
				double sumLimits = userLimits+roleLimits;
				tn.setChecked(sumLimits>0?true:false);
				tn.setDisabled(roleLimits>0?true:false);
			}
			TreeBuilder.addGroupNode(list, "01", "按钮权限", "plugin.gif");
		}
		return  TreeBuilder.buildListToTree(list);
	}

//@SystemLogs("修改用户权限信息")
	public Integer saveLimit(String userid,List<FSystemuserLimits> list) {
		sqlMapper.delete("delete s.* from f_systemuser_limits s where s.userid='"+userid+"'");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCompanyid(Local.getCompanyid());
		}
		return batchInsertSelective(mapper, list);
	}

}
