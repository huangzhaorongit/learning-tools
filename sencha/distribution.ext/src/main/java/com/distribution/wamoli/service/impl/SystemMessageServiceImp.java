package com.distribution.wamoli.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.wamoli.common.service.impl.BaseService;
import com.distribution.wamoli.common.utils.CommonUtils;
import com.distribution.wamoli.common.utils.IDUtils;
import com.distribution.wamoli.service.SystemMessageService;
import com.distribution.wamoli.mapper.FSystemmessageMapper;
import com.distribution.wamoli.domain.FSystemmessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("systemMessageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SystemMessageServiceImp extends BaseService<FSystemmessage> implements SystemMessageService{

	@Autowired
	public FSystemmessageMapper mapper;

	/**
	 * 读取聊天记录，并且聊天记录状态修改为已读
	 * @param bean
	 * @return
	 */
	public List<FSystemmessage> chatRecordList(FSystemmessage bean){
		String sql = "select * from f_systemmessage where"
				+" (fid = '"+bean.getFid()+"' and jid = '"+bean.getJid()+"')"
				+" or (fid = '"+bean.getJid()+"' and jid = '"+bean.getFid()+"')"
				+" order by time";
		List<FSystemmessage> list = sqlMapper.selectList(sql, FSystemmessage.class);
		sql = "update f_systemmessage set isread='1' where fid='"+bean.getFid()+"' and jid='"+bean.getJid()+"' ";
		sqlMapper.update(sql);
		return list;
	}

	/**
	 * 保存和修改发送消息
	 * @param bean
	 * @return
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public Integer saveOrUpdate(FSystemmessage bean){
		boolean isadd = CommonUtils.isEmpty(bean.getId());
		if(isadd){
			if(CommonUtils.isEmpty(bean.getType()))bean.setType("1");
			bean.setId(IDUtils.getUuid());
			return mapper.insert(bean);
		}else{
			return mapper.updateByPrimaryKeySelective(bean);
		}
	}
}
