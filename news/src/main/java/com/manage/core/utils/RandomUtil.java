package com.manage.core.utils;

import java.security.SecureRandom;

public class RandomUtil {
	
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";
	
	/** 
	 * 返回一个定长的随机字符串(只包含大小写字母、数字) 
	 *  
	 * @param length 
	 *            随机字符串长度 
	 * @return 随机字符串 
	 */  
	public static String generateString(int length) {  
	    StringBuffer sb = new StringBuffer();  
	    SecureRandom random = new SecureRandom();
	    for (int i = 0; i < length; i++) {  
	        sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
	    }  
	    return sb.toString();  
	}  
	
	/** 
	 * 返回一个定长数值字符串 
	 *  
	 * @param length 
	 *            随机字符串长度 
	 * @return 随机字符串 
	 */ 
	public static String generateNum(int length) {  
	    StringBuffer sb = new StringBuffer();  
	    SecureRandom random = new SecureRandom();
	    for (int i = 0; i < length; i++) {  
	        sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));  
	    }  
	    return sb.toString();  
	}  

}
