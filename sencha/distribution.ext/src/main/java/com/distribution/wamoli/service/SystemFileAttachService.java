package com.distribution.wamoli.service;

import java.util.List;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.domain.FSystemfileattach;


public interface SystemFileAttachService extends IService<FBasecodetype> {
	
	
	
	public List<FSystemfileattach> getSystemFileAttachList(FSystemfileattach bean);
	
	public FSystemfileattach getSystemFileAttachInfo(FSystemfileattach bean);
	
	public void addFSystemfileattach(FSystemfileattach bean,String nfile);
	
	/**
	 * 修改附件数量
	 * @param table 表名称
	 * @param field 主键名称
	 * @param id    主键id
	 * @param isAdd 是否新增
	 */ 
	public void updateTableAttachsNum(String tableName, String fieldname, String fieldvalue,String nfield,boolean isAdd);
	
	public void removeFile(FSystemfileattach bean,String nfield);
	
	/**
	 * 修改下载次数
	 * @param id
	 * @return
	 */
	public FSystemfileattach updateDownloadNum(String id);
	
	/**
	 * 修改下载次数
	 * @param id
	 * @return
	 */
	public FSystemfileattach updateViewNum(String id);
}
