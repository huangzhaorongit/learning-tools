package com.distribution.wamoli.service;

import java.util.List;
import java.util.Map;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FBasecodetype;
 

public interface SystemBaseCodeService extends IService<FBasecodetype>  {
	
	public List<Map<String,Object>> getViewList(String viewname);
	
	public List<Map<String,Object>> getViewList(String viewname,String ids);
	
	/**b v
	 * 查询下拉列表数据
	 * @param viewname 	         视图名称
	 * @param ids	   	   id(格式:01,02)为null查询所有
	 * @param idfield	          缺省id
	 * @param textfield	          缺省text
	 * @return
	 */
	public List<Map<String,Object>> getViewList(String viewname,String ids,String idfield,String textfield,String orderbyfield);

	
	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param viewname  试图名称
	 * @return
	 */
	public Map<String,String> getColumnMapping(String viewname) ;
	
	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param viewname  试图名称
	 * @return
	 */
	public Map<String,String> getColumnMapping(String viewname,String keyfield, String namefield);
	
	
	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param tablename  表名
	 * @param keyfield   主键
	 * @param namefield  匹配名称
	 * @param cdt  条件
	 * @return
	 */
	public Map<String,String> getColumnMapping(String tablename, String keyfield, String namefield, String cdt);

}
