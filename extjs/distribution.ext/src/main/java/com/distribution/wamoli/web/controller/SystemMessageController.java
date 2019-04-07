package com.distribution.wamoli.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.domain.FSystemmessage;
import com.distribution.wamoli.service.SystemMessageService;



@Controller
@RequestMapping("/platform/systemmessage")
public class SystemMessageController {

	@Resource
	private SystemMessageService service;
	
	//@SystemLogs("查询聊天记录")
	@RequestMapping(value = "/chatrecordlist")
	@ResponseBody
	public List<FSystemmessage> chatRecordList(FSystemmessage bean){
		return service.chatRecordList(bean);
	}
}
