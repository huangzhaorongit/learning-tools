package com.distribution.wamoli.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.distribution.wamoli.common.bean.TreeBuilder;
import com.distribution.wamoli.common.bean.TreeNode;
import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.common.utils.SqlHelper.QueryHelper;
import com.distribution.wamoli.service.PersonnelService;
import com.distribution.wamoli.mapper.FPersonnelMapper;
import com.distribution.wamoli.domain.FPersonnel;


@Service("personnelService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonnelServiceImpl extends BaseService<FPersonnel> implements PersonnelService{



	@Autowired
	private FPersonnelMapper mapper;

	//@SystemLogs("添加或修改人员信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FPersonnel saveOrUpdate(FPersonnel bean){
		boolean add = StringUtils.isEmpty(bean.getPersonnelid());
		if(add){
			bean.setPersonnelid(IDUtils.getUuid());
			bean.setCompanyid(Local.getCompanyid());
			bean.setCreater(Local.getUsername());
			bean.setCreatedate(DateUtils.getCurrentTime());
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			bean.setPersonnelcode(String.valueOf(((int)selectMax("f_personnel","personnelcode")+1)));
			mapper.insert(bean);
		}else{
			bean.setLastmodifier(Local.getUsername());
			bean.setLastmodifydate(DateUtils.getCurrentTime());
			mapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

	//@SystemLogs("查询人员列表信息")
	public List<Map<String,Object>> getList(FPersonnel bean) {
		String sql ="select f.*,o.orgname "
				   + " from f_personnel f inner join f_organization o on f.departmentid=o.orgid where f.isvalid='1' ";
		if(!StringUtils.isEmpty(bean.getDepartmentid())){
			sql +=" and f.departmentid='"+bean.getDepartmentid()+"' ";
		}
		if(!StringUtils.isEmpty(bean.getPersonnelname())){
			sql +=" and (f.personnelcode like '%"+bean.getPersonnelname()+"%' or f.personnelname like '%"+bean.getPersonnelname()+"%')";
		}
		sql+=" order by f.personnelcode,f.createdate ";
		return sqlMapper.selectList(QueryHelper.getQuerySql(sql));
	}

	//@SystemLogs("查询人员信息")
	public FPersonnel getPersonnelInfo(FPersonnel bean) {
		String sql = "select * from f_personnel f where f.personnelid='"+bean.getPersonnelid()+"'";
		return sqlMapper.selectOne(sql, FPersonnel.class);
	}

	//@SystemLogs("批量删除人员")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void deletePersonnel(List<FPersonnel> ids) {
		batchDeleteByPrimaryKey(mapper,ids);
	}

	//@SystemLogs("查询部门树型菜单")
	public List<TreeNode> getTreeList() {
		String sql="select o.orgid as id,o.parentid,o.orgname as text from f_organization o";
		List<TreeNode> dataList = sqlMapper.selectList(sql,TreeNode.class);
		return TreeBuilder.buildListToTree(dataList);
	}

	//@SystemLogs("批量修改人员所属部门")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void removeTot(List<FPersonnel> ids) {
		batchUpdateByPrimaryKeySelective(mapper,ids);
		for (int i = 0; i < ids.size(); i++) {
			String personnelid = ids.get(i).getPersonnelid();
			File path = Local.getLocalSpace().getPeronnelPhotoSpace(Local.getCompanyid());
			File photofile = new File(path,personnelid);
			if(photofile.isFile())photofile.delete();
		}
	}

	//@SystemLogs("上传人员照片信息")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String uploadPhoto(MultipartHttpServletRequest re,FPersonnel bean){
		CommonsMultipartFile mfile = (CommonsMultipartFile)re.getFile("photofile");
		if(mfile != null){
			try {
				File path = Local.getLocalSpace().getPeronnelPhotoSpace(Local.getCompanyid());
				File realfile = new File(path,bean.getPersonnelid());
				if(!realfile.isFile()){
					bean.setPhoto(bean.getPersonnelid());
					mapper.updateByPrimaryKeySelective(bean);
				}
				mfile.getFileItem().write(realfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "true";
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void cleanPhoto(String personnelid){
		File path = Local.getLocalSpace().getPeronnelPhotoSpace(Local.getCompanyid());
		File photofile = new File(path,personnelid);
		if(photofile.isFile())photofile.delete();
		sqlMapper.update("update f_personnel set photo = null where personnelid = '"+personnelid+"'");
	}

}
