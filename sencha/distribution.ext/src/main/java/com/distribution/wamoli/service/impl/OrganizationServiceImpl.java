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
import com.distribution.wamoli.service.OrganizationService;
import com.distribution.wamoli.mapper.FOrganizationMapper;
import com.distribution.wamoli.domain.FOrganization;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("organizationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrganizationServiceImpl extends BaseService<FOrganization> implements OrganizationService{


	@Autowired
	private FOrganizationMapper mapper;

	//@SystemLogs("添加或修改内部组织信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FOrganization saveOrUpdate(FOrganization bean){
		boolean add = StringUtils.isEmpty(bean.getOrgid());
		if(add){
			bean.setOrgid(IDUtils.getUuid());
			bean.setCompanyid(Local.getCompanyid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			bean.setOrderno((int)selectMax("f_systemmenu","Orderno","parentid = '"+bean.getParentid()+"'")+1);
			mapper.insert(bean);
		}else{
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

	//@SystemLogs("查询内部组织树形菜单")
	public List<Map<String, Object>> getOrganizationTree(TreeParams params){
		String sql = "select o.orgid as id,o.orgcode,o.orgname,o.orgtype,o.manager,o.telephone,o.address,o.remarks,o.attachsnum,o.isvalid,o.orderno,o.parentid,c.companyname,"
			+" (case (select count(1) from f_organization g where g.parentid = o.orgid) when 0 then 1 else 0 end ) as loaded "
			+" from f_organization o"
			+" left join f_company c on o.companyid = c.companyid "
			+" where o.parentid = '"+params.getNode()+"' and o.isvalid = '1'"
			+" order by o.orderno ";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询内部组织信息")
	public Map<String, Object> getOrganizationInfo(String orgid) {
		String sql = " select o.orgid,o.orgname, o.orgtype , o.manager,o.telephone ,o.address,o.remarks ,o.isvalid , o.attachsnum ,o.companyid,c.companyname "
			+" FROM  f_organization o "
			+" LEFT JOIN f_company c  on o.companyid = c.companyid "
			+" where orgid = '"+ orgid+"'";
		return sqlMapper.selectOne(sql);
	}

	//@SystemLogs("删除内部组织信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteOrganization(String orgid) {
		List<Map<String, Object>> list = sqlMapper.selectList("select orgid from f_organization where parentid = '"+ orgid +"'");
		int count = list.size();
		if(count>0) return "-1";
	    count = sqlMapper.delete("delete  from  f_organization  where orgid = '"+ orgid +"'");
		return String.valueOf(count);
	}

	//@SystemLogs("修改内部组织节点的排列顺序")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void saveMoveOrder(List<FOrganization> list) {
		for (int i = 0; i < list.size(); i++) {
			FOrganization bean = list.get(i);
			bean.setOrderno(i);
			mapper.updateByPrimaryKeySelective(bean);
		}
	}

}
