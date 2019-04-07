package com.distribution.wamoli.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.distribution.wamoli.service.SystemBaseCodeService;



@Controller
@RequestMapping("/platform/basecode")
public class SystemBaseCodeController {
	
	@Autowired
	private SystemBaseCodeService systemBaseCodeService; 
	
	@RequestMapping(value = "/getviewlist")
	@ResponseBody
	public List<Map<String,Object>> getViewList(String viewname,String ids,String idfield,String textfield,String orderbyfield){
		return systemBaseCodeService.getViewList(viewname,ids,idfield,textfield,orderbyfield);
	}
	
}
