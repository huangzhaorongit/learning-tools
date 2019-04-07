package com.distribution.wamoli.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.service.SystemBaseCodeService;
import com.distribution.wamoli.domain.FBasecodetype;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("systemBaseCodeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemBaseCodeServiceImpl extends BaseService<FBasecodetype> implements  SystemBaseCodeService{



	public List<Map<String,Object>> getViewList(String viewname){
		return getViewList(viewname, null, null, null,null);
	}

	public List<Map<String,Object>> getViewList(String viewname,String ids){
		return getViewList(viewname, ids, null, null,null);
	}

	/**b v
	 * 查询下拉列表数据
	 * @param viewname 	         视图名称
	 * @param ids	   	   id(格式:01,02)为null查询所有
	 * @param idfield	          缺省id
	 * @param textfield	          缺省text
	 * @return
	 */
	public List<Map<String,Object>> getViewList(String viewname,String ids,String idfield,String textfield,String orderbyfield){
//		Locale local  = ProjectUtils.getLanguage();
		String id = StringUtils.isEmpty(idfield)?"id":idfield;
		String text = StringUtils.isEmpty(textfield)?"text":textfield;
		String sql = "select a.codeid as "+id+","
				   +" (case when 'zh'='zh' then a.codename  else a.codename1 end) as "+text+", "
				   +" a.color as color,a.custom1 "
				   +" from f_basecode a "
				   +" inner join  f_basecodetype b on a.codetype = b.codetype"
				   +" where b.viewname='"+viewname+"' and a.isvalid = '1' ";
		if(!StringUtils.isEmpty(ids)){
			sql+=" and a.codeid in ('"+ids.replace(",", "','")+"')";
		}
		if (!StringUtils.isEmpty(orderbyfield)) {
			sql += "order by a." + orderbyfield + " ";
		} else{
			sql += "order by -(-a.codeid)";
		}
		return sqlMapper.selectList(sql);
	}

	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param viewname  试图名称
	 * @return
	 */
	public Map<String,String> getColumnMapping(String viewname) {
		List<Map<String,Object>> list = getViewList(viewname);
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0; i<list.size(); i++) {
			Map<String,Object> r = list.get(i);
			map.put(((String)r.get("id")).trim(),(String)r.get("text"));
		}
		return map;
	}

	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param viewname  试图名称
	 * @return
	 */
	public Map<String,String> getColumnMapping(String viewname,String keyfield, String namefield) {
		List<Map<String,Object>> list = getViewList(viewname);
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0; i<list.size(); i++) {
			Map<String,Object> r = list.get(i);
			map.put(((String)r.get(keyfield)).trim(),(String)r.get(namefield));
		}
		return map;
	}



	/**
	 *  把表数据转换成Map数据  根据匹配名称.转换为主键
	 * @param tablename  表名
	 * @param keyfield   主键
	 * @param namefield  匹配名称
	 * @param cdt  条件
	 * @return
	 */
	public Map<String,String> getColumnMapping(String tablename, String keyfield, String namefield, String cdt) {
		String sql = " select "+keyfield+" as K,"+namefield+" as N from "+tablename+" where 1=1 ";
		if(!StringUtils.isEmpty(cdt)) sql += " and " + cdt;
		List<Map<String,Object>> list = sqlMapper.selectList(sql);
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0; i<list.size(); i++) {
			Map<String,Object> r = list.get(i);
			map.put(((String)r.get("K")).trim(),(String)r.get("N"));
		}
		return map;
	}


}
