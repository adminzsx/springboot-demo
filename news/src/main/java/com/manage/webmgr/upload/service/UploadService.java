package com.manage.webmgr.upload.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.manage.webmgr.result.ResultPojo;

 
public interface UploadService {

	
	/**
	 * @param fileName 文件名称
	 * @param fileType 文件类型  1 图片  2：视频
	 * @param wh  1：横屏    2：竖屏
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	public ResultPojo uploadFile(String fileName,String fileType,String wh, MultipartFile file,  HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * @Title: fileDowload
	 * @Description: 文件下载
	 * @author ChenXiaofen
	 * @date 2019年3月21日
	 */
	public void fileDowload(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
