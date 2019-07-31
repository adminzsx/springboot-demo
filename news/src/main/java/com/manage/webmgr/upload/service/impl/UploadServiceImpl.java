package com.manage.webmgr.upload.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.manage.core.utils.RandomUtil;
import com.manage.core.utils.TimeUtil;
import com.manage.webmgr.result.ResultCode;
import com.manage.webmgr.result.ResultPojo;
import com.manage.webmgr.upload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Override
	public ResultPojo uploadFile(String fileName,String fileType, String wh, MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		 ResultPojo pojo = new ResultPojo();
		 
		 if(StringUtils.isBlank(fileName)){
			 pojo.setResultCode(ResultCode.FAIL_CODE);
			 pojo.setResultMsg("缺少参数");
			 return pojo;
		 }
		 
		 Map map = new HashMap<>();
//		 String ext = "." + FileUtil.getExtensionName(fileName.toLowerCase());
		 String path = new File(new File(ResourceUtils.getURL("classpath:").getPath() + File.separator + "static/").getAbsolutePath(), "").getAbsolutePath() + File.separator;
//		 String path = new File(new File(request.getSession().getServletContext().getRealPath("/")).getAbsolutePath(), "").getAbsolutePath() + File.separator;
         //图片
		 String filePath = "";
		 String newfilename = TimeUtil.formatDate(new Date(), "yyyyMMddHHmmssSSS")+RandomUtil.generateNum(3);
		 if("image/jpeg".equals(fileType)){
//			  path = path + filePath;
			 filePath = "pics"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
			  InputStream is=null;
		        try{

		            is = new ByteArrayInputStream(file.getBytes());
		            BufferedImage src = ImageIO.read(is);
		          
		            int imgWidth = src.getWidth() ;
		            int imgHeight = src.getHeight();
		            
		            if(imgWidth>=imgHeight){
		            	//横屏
		            	map.put("sizeType", 1);
		            }else{
		            	//竖屏
		            	map.put("sizeType", 2);
		            }
		            map.put("resolution", imgWidth+"*"+imgHeight);
		            
		            
//		            String fileze=FileUtil.getFileSize(file.getSize());
//		            map.put("resolution", imgWidth+"*"+imgHeight);
		            
		        }catch (Exception e){
		            throw new Exception("操作失败!");
		        }finally {
		            IOUtils.closeQuietly(is);
		        }
			 
		 }else if("video/mp4".equals(fileType)){
//			 path = path + "video"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
			 filePath = "video"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
		 }else if("application/x-zip-compressed".equals(fileType)){
			 //压缩包
			 filePath = "zipFile"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
			 newfilename = "_" + newfilename + "_" + fileName.split("\\.")[0];
		 }else if("application/zip".equals(fileType)){
			 //压缩包
			 filePath = "zipFile"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
			 newfilename = "_" + newfilename + "_" + fileName.split("\\.")[0];
		 }else{
			 filePath = "other"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
			  
		 }
		 
		filePath = filePath + newfilename + "." + fileName.split("\\.")[1];
        path = path + filePath;
		FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
		filePath = filePath.replaceAll("\\\\", "\\/");
		 map.put("path", "/" + filePath);
		 pojo.setResultObj(map);
	       
		return pojo;
	}
	
	@Override
	public void fileDowload(HttpServletRequest request, HttpServletResponse response) {
	 
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/octet-stream");
		FileInputStream fis = null;
		try {
//			fileUrl = request.getSession().getServletContext().getRealPath("/") + fileUrl;
//			fileUrl = ResourceUtils.getURL("classpath:").getPath();
			File file =ResourceUtils.getFile("classpath:static/Template.zip"); //new File(fileUrl);
			fis = new FileInputStream(file);
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(file.getName().getBytes("utf-8"),"ISO_8859_1"));
			IOUtils.copy(fis, response.getOutputStream());
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		 
	}

}
