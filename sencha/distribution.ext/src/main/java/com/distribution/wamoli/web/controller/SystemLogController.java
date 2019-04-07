package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.service.SystemLogService;
import com.distribution.wamoli.domain.FSystemlog;



@Controller
@RequestMapping("/platform/systemlog")
public class SystemLogController {
	
	 @Autowired
	private SystemLogService systemLogService;

		@RequestMapping(value="/getsystemoperate")
		@ResponseBody
		public List<Map<String, Object>> getSystemLogOperate(FSystemlog bean ,String startDateValue , String endDateValue){
			return systemLogService.getSystemLogOperate(bean, startDateValue ,endDateValue );
		}
		

		@RequestMapping(value="/getsystemexception")
		@ResponseBody
		public  List<Map<String, Object>> getSystemLogException(FSystemlog bean){
			return systemLogService.getSystemLogException(bean);
		}
		
		
		@RequestMapping(value = "/getsystemlog")
		@ResponseBody
		public FSystemlog getSystemLog(FSystemlog bean){
			return systemLogService.getSystemLog(bean);
		}
}
