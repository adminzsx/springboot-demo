package com.manage.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manage.webmgr.result.ResultPojo;

@ControllerAdvice
public class MyExceptionAdvice {
	
	
 
    
    @ExceptionHandler(value = TokenException.class)
    @ResponseBody
    public ResultPojo errorHandler(TokenException ex) {
    	ResultPojo pojo=new ResultPojo();
    	pojo.setResultCode(Integer.valueOf(ex.getCode()));
    	pojo.setResultMsg(ex.getMessage());
	    return pojo;
    }
    
}
