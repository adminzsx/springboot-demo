package com.manage.webmgr.utils;

import java.math.BigDecimal;

public class FileUtil {

	/**
	 * 获得文件扩展名
	 *
	 * @param filename 文件原名
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	/**
	 * 获取文件大小 自适应单位 B KB MB GB
	 * @param len
	 * @return
	 */
	 public static String getFileSize(Long len) {
 
       String fileSize = "";
       if (len<1024) {
            fileSize = String.valueOf(len)+"B";
       } else if (len>=1024&&len<1048576) {
           fileSize = BigDecimal.valueOf(len / 1024).setScale(2,BigDecimal.ROUND_HALF_UP) +"KB";
       } else if (len>=1048576&&len<1073741824) {
           fileSize = BigDecimal.valueOf(len / 1048576).setScale(2,BigDecimal.ROUND_HALF_UP)+"MB";
       } else {
           fileSize = BigDecimal.valueOf(len / 1073741824).setScale(2,BigDecimal.ROUND_HALF_UP)+"GB";
       }
       return fileSize;
	 }
 
}
