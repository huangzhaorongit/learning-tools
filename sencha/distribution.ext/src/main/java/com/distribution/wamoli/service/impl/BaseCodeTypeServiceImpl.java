package com.distribution.wamoli.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.BaseCodeTypeService;
import com.distribution.wamoli.mapper.FBasecodeMapper;
import com.distribution.wamoli.mapper.FBasecodetypeMapper;
import com.distribution.wamoli.domain.FBasecode;
import com.distribution.wamoli.domain.FBasecodetype;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("baseCodeTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BaseCodeTypeServiceImpl extends BaseService<FBasecodetype> implements BaseCodeTypeService {



	@Autowired
	private FBasecodetypeMapper mapper;

	@Autowired
	private FBasecodeMapper fBaseCodeMapper;

	//@SystemLogs("查询数据字典列表(主)")
	public List<Map<String, Object>> getBaseCodeTypelist(FBasecodetype bean) {
		String sql = " select b.codetype , b.coderemark , b.viewname , b.isvalid , b.remark  "
				   + " from f_basecodetype b "
				   + " where  b.isvalid = '1' and b.isvalid = '1'";
		if(!StringUtils.isEmpty(bean.getViewname())){
			sql+= "and b.coderemark like '%"+ bean.getViewname() +"%'or b.viewname like '%"+ bean.getViewname() +"%'";
		}
		sql += " order by b.codetype";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("查询数据字典信息(主)")
	public FBasecodetype getBasecodetypeinfo(FBasecodetype bean) {
		return mapper.selectOne(bean);
	}

//	@SystemLogs("查询数据字典列表(副)")
	public List<Map<String, Object>> getFbasecodelist(FBasecode bean) {
		String sql = " select * from f_basecode b where b.codetype = '" +bean.getCodetype()+ "'";
		return sqlMapper.selectList(sql);
	}

	//@SystemLogs("添加或修改数据字典")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String saveorupdate(FBasecodetype bean, List<FBasecode> list) {
		boolean add = StringUtils.isEmpty(bean.getCodetype());
		if(add){
			bean.setCodetype(IDUtils.getUuid());
			mapper.insert(bean);
		}else{
			mapper.updateByPrimaryKeySelective(bean);
			sqlMapper.delete("delete from f_basecode where codetype='" + bean.getCodetype() + "'");
		}
		for (int i = 0; i < list.size(); i++) {
			FBasecode fBasecode = list.get(i);
			fBasecode.setCodetype(bean.getCodetype());
			fBaseCodeMapper.insert(fBasecode);
		}
		return bean.getCodetype();
	}

	//@SystemLogs("删除数据字典")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public String deleteFbasecodetype(String codetype) {
		String sql = " delete  from f_basecodetype  where  codetype = '"+codetype+"'";
		String sql1 = " delete  from f_basecode   where  codetype = '" +codetype+ "'";
		sqlMapper.delete(sql);
		sqlMapper.delete(sql1);
		return "1";
	}




}
