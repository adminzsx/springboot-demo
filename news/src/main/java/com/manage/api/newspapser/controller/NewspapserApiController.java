package com.manage.api.newspapser.controller;

import com.manage.api.newspapser.entity.Newspapser;
import com.manage.api.newspapser.service.NewspapserService;
import com.manage.webmgr.result.ResultCode;
import com.manage.webmgr.result.ResultPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class NewspapserApiController {
	@Autowired
    NewspapserService newspapserService;


	@RequestMapping("/news")
	@ResponseBody
	public ResultPojo news( Newspapser newspapser) {
		ResultPojo result = new ResultPojo();
		try {
			
			List<Newspapser> news=newspapserService.getNewspapserByType(newspapser.getType());
			
			result.setResultObj(news);
		
		}catch(Exception e) {
			
			result.setResultMsg("操作失败");
			
			result.setResultCode(ResultCode.FAIL_CODE);
		}
		
		return result;
	}


	
}
