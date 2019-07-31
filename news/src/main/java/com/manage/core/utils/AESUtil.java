package com.manage.core.utils;

import java.math.BigInteger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manage.core.configuration.ApplicationConfig;

 

@Component
public class AESUtil {

	private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

	@Autowired
	ApplicationConfig applicationConfig;
	
	
	/** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);	// 这里的1代表正数  
    }
    
    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串            
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.isEmpty())
            throw new IllegalArgumentException("this hexString must not be empty");
 
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @return
	 */
	public String encryptDefaultKey(String content) {
		return encrypt(content,applicationConfig.getAesKey());
	}
	
	/**
	 * 
	 * @param content
	 * @param cKey
	 * @return
	 */
	public String encrypt(String content,String cKey) {
		try {

			// 密钥补位
	        int plus= 16-cKey.length();        
	        byte[] data = cKey.getBytes("utf-8");
	        byte[] raw = new byte[16];
	        byte[] plusbyte={ 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
	        for(int i=0;i<16;i++)
	        {
	        	if (data.length > i)
	        		raw[i] = data[i];
	        	else
	        		raw[i] = plusbyte[plus];
	        }

			SecretKeySpec key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器CBC超级模式
			IvParameterSpec iv = new IvParameterSpec(raw);// 向量iv
																								// 必须16位
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);// 初始化
			byte[] result = cipher.doFinal(content.getBytes("utf-8"));
			return binary(result, 16); //十六进制
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @return
	 */
	public String decryptDefaultKey(String content) {
		 return decrypt(content,applicationConfig.getAesKey());
	}
	
	/**
	 * 
	 * @param content
	 * @param cKey
	 * @return
	 */
	public String decrypt(String content,String cKey) {
		try {
			 // 密钥补位
            int plus= 16-cKey.length();
            byte[] data = cKey.getBytes("utf-8");
            byte[] raw = new byte[16];
            byte[] plusbyte={ 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for(int i=0;i<16;i++)
            {
            	if (data.length > i)
            		raw[i] = data[i];
            	else
            		raw[i] = plusbyte[plus];
            }
            
			SecretKeySpec key = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器CBC超级模式
			IvParameterSpec iv = new IvParameterSpec(raw);// 向量iv
																								// 必须16位
			cipher.init(Cipher.DECRYPT_MODE, key, iv);// 初始化
			byte[] decryptFrom = toByteArray(content);//十六进制
			byte[] result = cipher.doFinal(decryptFrom);
			return new String(result, "utf-8"); // 解密
	 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}
	 
	
	public static void main(String[] args) {
		
		
		String cc="123";
		
		String ckey="ktvo3er0313,/.11";
		
		AESUtil util=new AESUtil();
		 
         String enyyrt=util.encrypt(cc,ckey);

         System.out.println("========================cc:"+cc);
        System.out.println("========================enyyrt:"+enyyrt);
        System.out.println("========================decrypt:"+util.decrypt(enyyrt,ckey));
		
	}
}
