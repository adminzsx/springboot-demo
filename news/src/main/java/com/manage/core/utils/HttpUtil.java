package com.manage.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class HttpUtil {

	/**
	 * 获取访问的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("X-Real-IP");
	    }
	    if(null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    if("0:0:0:0:0:0:0:1".equals(ip)){
	    	ip="127.0.0.1";
	    }
	    return ip;
	}
	
	 /**
     * 验证IP是否属于某个IP段
     * @return
     */
    public static boolean ipExistsInRange(String ip,String beginIP,String endIP) {
        boolean result=false;
        if(StringUtils.isBlank(beginIP)|| StringUtils.isBlank(endIP)){
            return result;
        }
        try{
            if(getIp2long(beginIP)<=getIp2long(ip) &&getIp2long(ip)<=getIp2long(endIP)){
                result=true;
            }
        }catch (NumberFormatException ex){
            result=false;
        }
        return result;
    }

    public static long getIp2long(String ip) {

        ip = ip.trim();
        String[] ips = ip.split("\\.");
        long ip2long = 0L;
        for (int i = 0; i < 4; ++i) {
            ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
        }
        return ip2long;

    }
    
    /** 
     * 通过IP地址获取MAC地址 
     * @param ip String,127.0.0.1格式 
     * @return mac String 
     * @throws Exception 
     */  
    public static String getMACAddress(String ip) {  
        String line = "";  
        String macAddress = "";  
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";  
        final String LOOPBACK_ADDRESS = "127.0.0.1";  
        //如果为127.0.0.1,则获取本地MAC地址。  
        if (LOOPBACK_ADDRESS.equals(ip)) {  
            byte[] mac = null;
			try {
				InetAddress  inetAddress = InetAddress.getLocalHost();
				mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			}catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(mac==null){
				return macAddress;
			}
            //下面代码是把mac地址拼装成String  
            StringBuilder sb = new StringBuilder();  
            for (int i = 0; i < mac.length; i++) {  
                if (i != 0) {  
                    sb.append("-");  
                }  
                //mac[i] & 0xFF 是为了把byte转化为正整数  
                String s = Integer.toHexString(mac[i] & 0xFF);  
                sb.append(s.length() == 1 ? 0 + s : s);  
            }  
            //把字符串所有小写字母改为大写成为正规的mac地址并返回  
            macAddress = sb.toString().trim().toUpperCase();  
            return macAddress;  
        }  
        //获取非本地IP的MAC地址  
        BufferedReader br=null;
        try {  
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
            InputStreamReader isr = new InputStreamReader(p.getInputStream());  
            br = new BufferedReader(isr);  
            while ((line = br.readLine()) != null) {  
                if (line != null) {  
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);  
                    if (index != -1) {  
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();  
                    }  
                }  
            }  
            br.close();  
        } catch (IOException e) {  
           e.printStackTrace();
        }finally {
			IOUtils.closeQuietly(br);
		}  
        return macAddress;  
    }
    
    public static void main(String[] args) throws Exception {
		
    	String mac=getMACAddress("127.0.0.1");
    	System.out.println("mac:"+mac);
	}
    
}
