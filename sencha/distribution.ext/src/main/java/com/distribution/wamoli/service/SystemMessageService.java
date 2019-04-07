package com.distribution.wamoli.service;

import java.util.List;

import com.distribution.wamoli.common.service.IService;
import com.distribution.wamoli.domain.FSystemmessage;


public interface SystemMessageService extends IService<FSystemmessage> {
	
	
	
	
	/**
	 * 读取聊天记录，并且聊天记录状态修改为已读
	 * @param bean
	 * @return
	 */
	public List<FSystemmessage> chatRecordList(FSystemmessage bean);
	
	/**
	 * 保存和修改发送消息
	 * @param bean
	 * @return
	 */
	public Integer saveOrUpdate(FSystemmessage bean);

}
