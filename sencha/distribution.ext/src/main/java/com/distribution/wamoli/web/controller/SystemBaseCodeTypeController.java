package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.common.interceptor.bind.annotation.RequestBean;
import com.distribution.wamoli.common.interceptor.bind.annotation.RequestList;
import com.distribution.wamoli.domain.FBasecode;
import com.distribution.wamoli.domain.FBasecodetype;
import com.distribution.wamoli.service.BaseCodeTypeService;




@Controller
@RequestMapping("/platform/basecodetype")
public class SystemBaseCodeTypeController {
	
	@Resource
	private BaseCodeTypeService service;
	
	
	@RequestMapping(value = "/getbasecodetypelist")
	@ResponseBody
	public List<Map<String, Object>> getBaseCodeTypelist(FBasecodetype bean ){
		return service.getBaseCodeTypelist(bean);
	}
	
	
	@RequestMapping(value = "/getbasecodetypeinfo")
	@ResponseBody
	public FBasecodetype getBasecodetypeinfo(FBasecodetype bean){
		return service.getBasecodetypeinfo(bean);
	}
	
	
	@RequestMapping(value = "/getfbasecodelist")
	@ResponseBody
	public List<Map<String, Object>> getFbasecodelist(FBasecode bean){
		return service.getFbasecodelist(bean);
	}
	
	@RequestMapping(value = "/saveorupdate")
	@ResponseBody
	public String saveorupdate(@RequestBean FBasecodetype bean ,@RequestList(clazz=FBasecode.class) List<FBasecode> list){
		return  service.saveorupdate(bean ,list);
	}
	
	
	@RequestMapping(value = "/deletefbasecodetype")
	@ResponseBody
	public String deleteFbasecodetype(String codetype){
		return service.deleteFbasecodetype(codetype);
	}
}
