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
import com.distribution.wamoli.service.SystemRoleLimitsService;
import com.distribution.wamoli.mapper.FSystemroleLimitsMapper;
import com.distribution.wamoli.domain.FSystemrole;
import com.distribution.wamoli.domain.FSystemroleLimits;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemRoleLimitsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemRoleLimitsServiceImpl extends BaseService<FSystemroleLimits> implements SystemRoleLimitsService{






	@Autowired
	private FSystemroleLimitsMapper mapper;

	//@SystemLogs("查询系统角色列表")
	public List<FSystemrole> getSystemrole(String rolecode)  {
		String sql = "select * from f_systemrole f where f.isvalid ='1'";
		if(!StringUtils.isEmpty(rolecode)){
			sql+= "and f.rolename like'%"+rolecode+"%' or f.rolecode like'%"+rolecode+"%'";
		}
		return sqlMapper.selectList(sql,FSystemrole.class);
	}

  //@SystemLogs("查询角色权限树形菜单")
	public List<TreeNode> getModulTree(String roleid) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if(!StringUtils.isEmpty(roleid)){
			String sql = "select a.menuid as id,a.menuname as text,a.parentid,a.iconcls,a.orderno,a.menuid as param1,'00' as param2,'00' param3,"
					+" case when ((select count(c.menuid) from f_systemrole_limits c where c.menuid=a.menuid and c.operatecode='00' and c.roleid ='"+roleid+"'))>0  then 1 else 0 end as checked"
					+" from f_systemmenu a where a.companyid='"+Local.getCompanyid()+"' and a.isdisplay='1' "
					+" union all "
					+" select "+sf.link(new String[]{"e.menuid","'_'","d.operatecode"})+" as id,d.operatename as text,e.menuid as parentid,'' as iconcls,"

                  //  +" select  CONCAT(e.menuid ,'_',d.operatecode)      as id,d.operatename as text,e.menuid as parentid,'' as iconcls,"
			        +"   e.orderno,e.menuid as param1,d.operatecode as param2,'01' param3,"
					+"   case when ((select count(c.menuid) from f_systemrole_limits c where c.menuid=e.menuid and c.operatecode=d.operatecode and c.roleid ='"+roleid+"'))>0  then 1 else 0 end as checked"
					+" from f_moduleoperate d  "
					+" inner join f_systemmenu e on e.moduleid = d.moduleid "
					+" order by orderno";
			list = sqlMapper.selectList(sql, TreeNode.class);
			TreeBuilder.addGroupNode(list, "01", "按钮权限", "plugin.gif");
		}
		return  TreeBuilder.buildListToTree(list);
	}

	//@SystemLogs("修改角色的权限信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public Integer saveLimit(String roleid, List<FSystemroleLimits> list) {
		sqlMapper.delete("delete s.* from f_systemrole_limits s where s.roleid='"+roleid+"'");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCompanyid(Local.getCompanyid());
		}
		return batchInsertSelective(mapper, list);
	}

}
