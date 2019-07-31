package com.manage.webmgr.upload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manage.webmgr.result.ResultCode;
import com.manage.webmgr.result.ResultPojo;
import com.manage.webmgr.upload.service.UploadService;

@Controller
public class UploadController {
 
	@Autowired
	UploadService uploadService;
    /**
     * 上传文件
     *
     * @return
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResultPojo uploadFile(
    		 @RequestParam("name") String fileName,
            @RequestParam("type") String fileType,
            @RequestParam("size") String size,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
    	ResultPojo pojo=new ResultPojo();
        try {
        	pojo= uploadService.uploadFile(fileName,fileType, size, file, request, response);
		} catch (Exception e) {
			pojo.setResultCode(ResultCode.FAIL_CODE);
			pojo.setResultMsg(e.getMessage());
		}
        
        return pojo;
    }
    /**
     * @Title: dowloadFile
     * @Description: 下载文件
     * @author ChenXiaofen
     * @date 2019年3月21日
     */
    @RequestMapping("/dowloadTemplate")
	public void dowloadFile(String fileUrl, HttpServletRequest request, HttpServletResponse response) {
    	 
		try {
			uploadService.fileDowload(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
}
