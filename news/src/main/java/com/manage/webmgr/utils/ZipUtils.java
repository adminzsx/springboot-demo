package com.manage.webmgr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import com.manage.core.utils.RandomUtil;
import com.manage.core.utils.TimeUtil;
import com.manage.webmgr.result.ResultCode;
import com.manage.webmgr.result.ResultPojo;

public class ZipUtils {
	/** 缓冲器大小 */
    private static final int BUFFER = 512;

	public static ResultPojo decompression(String filePath, HttpServletRequest request) {

		ResultPojo result = new ResultPojo();
        
        try {
//        String prePath = request.getSession().getServletContext().getRealPath("/");
        	String prePath = ResourceUtils.getURL("classpath:").getPath() + "static/";
        	prePath = prePath.replaceAll("\\\\", "\\/");
        	
        	String zipFileName = prePath.substring(0, prePath.length() - 1) + filePath;
        	String dstPath = prePath + "zipFile"+File.separator+TimeUtil.formatDate(new Date(), "yyyyMMdd")+File.separator;
        	dstPath = dstPath + File.separator + RandomUtil.generateNum(10);
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName),Charset.forName("GBK"));
            ZipEntry       zipEntry       = null;
            byte[]         buffer         = new byte[BUFFER];//缓冲器
            int            readLength     = 0;//每次读出来的长度
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {//若是zip条目目录，则需创建这个目录
                	File dir = new File(new String((dstPath + "/" + zipEntry.getName()).getBytes("gbk"),"utf-8"));
 
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;//跳出
                }
 
                File file = createFile(dstPath, zipEntry.getName());//若是文件，则需创建该文件
 
               // OutputStream outputStream = new FileOutputStream(file);
                FileOutputStream fos = new FileOutputStream(file);
                
                while ((readLength = zipInputStream.read(buffer, 0, BUFFER)) != -1) {
                   // outputStream.write(buffer, 0, readLength);
                	fos.write(buffer, 0, readLength);
             
                }
     
 
              //  outputStreamWriter.close();
                IOUtils.closeQuietly(fos);
                
            }
//            result.setResultObj(dstPath + File.separator + filePath.split("_")[2].split("\\.")[0]);
            result.setResultObj(dstPath + File.separator);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result.setResultCode(ResultCode.FAIL_CODE);
            result.setResultMsg(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
            result.setResultCode(ResultCode.FAIL_CODE);
        }
        return result;

	}
	
    private static File createFile(String dstPath, String fileName) throws IOException {
        String[] dirs = fileName.split("/");//将文件名的各级目录分解
        File     file = new File(new String((dstPath).getBytes("gbk"),"utf-8"));
 
        if (dirs.length > 1) {//文件有上级目录
            for (int i = 0; i < dirs.length - 1; i++) {
                file = new File(file, dirs[i]);//依次创建文件对象知道文件的上一级目录
            }
 
            if (!file.exists()) {
                file.mkdirs();//文件对应目录若不存在，则创建
                System.out.println("mkdirs: " + file.getCanonicalPath());
            }
 
            file = new File(file, dirs[dirs.length - 1]);//创建文件
 
            return file;
        } else {
            if (!file.exists()) {
                file.mkdirs();//若目标路径的目录不存在，则创建
                System.out.println("mkdirs: " + file.getCanonicalPath());
            }
 
            file = new File(file, dirs[0]);//创建文件
 
            return file;
        }
    }

}
