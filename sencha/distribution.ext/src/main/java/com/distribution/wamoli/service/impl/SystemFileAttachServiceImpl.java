package com.distribution.wamoli.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.critical.Local;
import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.DateUtils;
import com.distribution.wamoli.service.SystemFileAttachService;
import com.distribution.wamoli.mapper.FSystemfileattachMapper;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemfileattach;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemFileAttachService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemFileAttachServiceImpl extends BaseService<FBasecodetype> implements SystemFileAttachService {

	@Autowired
	private FSystemfileattachMapper fSystemfileattachMapper;

	public List<FSystemfileattach> getSystemFileAttachList(FSystemfileattach bean){

		String sql = "select * from f_systemfileattach a where a.companyid = '"+bean.getCompanyid()+"' and a.sourcetablename = '"+bean.getSourcetablename()+"'"
				   +" and a.sourcefieldname='"+bean.getSourcefieldname()+"' and a.sourcerecordkey= '"+bean.getSourcerecordkey()+"' ";
		if(!StringUtils.isEmpty(bean.getTitle())){
			sql+=" and a.title like '%"+bean.getTitle()+"%'";
		}
		if(!StringUtils.isEmpty(bean.getFiletype())){
			sql+=" and a.filetype = '"+bean.getFiletype()+"'";
		}
		if(!StringUtils.isEmpty(bean.getFilesuffix())){
			sql+=" and a.filesuffix = '"+bean.getFilesuffix().toLowerCase()+"'";
		}
		sql += " order by createdate desc";
		return    sqlMapper.selectList(sql,FSystemfileattach.class);

		}

	public FSystemfileattach getSystemFileAttachInfo(FSystemfileattach bean){
		return fSystemfileattachMapper.selectOne(bean);
	}
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void addFSystemfileattach(FSystemfileattach bean,String nfile){
		bean.setCreater(Local.getUsername());
		bean.setCreatedate(DateUtils.getCurrentTimestamp());
		bean.setLastmodifier(Local.getUsername());
		bean.setLastmodifydate(DateUtils.getCurrentTimestamp());
		fSystemfileattachMapper.insert(bean);
		updateTableAttachsNum(bean.getSourcetablename(),bean.getSourcefieldname(),bean.getSourcerecordkey(),nfile,true);

	}

	/**
	 * 修改附件数量
	 * @param table 表名称
	 * @param field 主键名称
	 * @param id    主键id
	 * @param isAdd 是否新增
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void updateTableAttachsNum(String tableName, String fieldname, String fieldvalue,String nfield,boolean isAdd){
		if(StringUtils.isEmpty(nfield))return;
		try{
			String sql = "select "+nfield+" as attachs from "+tableName+" where "+fieldname+" = '"+fieldvalue+"'";
			List<Map<String,Object>> list = sqlMapper.selectList(sql);
			if(list.size()>0){
				Integer attachs =  (Integer)list.get(0).get("attachs");
				attachs = attachs==null?0:attachs;
				sql = "update  "+tableName+" set "+nfield+" = "+(isAdd?(attachs+1):(attachs-1))+" where "+fieldname+" = '"+fieldvalue+"'";
				sqlMapper.update(sql);
			}
		}catch(Exception e){
			Logger.getLogger(SystemFileAttachService.class).error("(tableName:"+tableName+",fieldname:"+fieldname+",fieldvalue:"+fieldname+")修改附件数量出现异常!"+e.getMessage());
		}
	}

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public void removeFile(FSystemfileattach bean,String nfield){
		bean = fSystemfileattachMapper.selectOne(bean);
		if(bean==null)return;
		File path = Local.getLocalSpace().getFileAttachSpace(bean.getCompanyid());
		File file = new File(path,bean.getId());
		mapper.deleteByPrimaryKey(bean);
		updateTableAttachsNum(bean.getSourcetablename(),bean.getSourcefieldname(),bean.getSourcerecordkey(),nfield,false);
		if(file.isFile())file.delete();
	//	LuceneUtils.deleteSearchObject(bean.getId());
	}

	/**
	 * 修改下载次数
	 * @param id
	 * @return
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemfileattach updateDownloadNum(String id){

		FSystemfileattach bean = new FSystemfileattach();
		bean.setId(id);
		bean = fSystemfileattachMapper.selectOne(bean);
		if(bean==null)return null;
		bean.setDownloadnum(bean.getDownloadnum()+1);
		fSystemfileattachMapper.updateByPrimaryKeySelective(bean);
		return bean;
	}

	/**
	 * 修改下载次数
	 * @param id
	 * @return
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public FSystemfileattach updateViewNum(String id){

		FSystemfileattach bean = new FSystemfileattach();
		bean.setId(id);
		bean = fSystemfileattachMapper.selectOne(bean);
		if(bean==null)return null;
		bean.setViewnum(bean.getViewnum()+1);
		fSystemfileattachMapper.updateByPrimaryKeySelective(bean);
		return bean;
	}
}
