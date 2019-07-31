package com.manage.core.security;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import com.manage.core.entity.UserInfo;

@Service
public class LoginUser {

	public static UserInfo getLoginUser() {
		if(SecurityContextHolder.getContext().getAuthentication()!=null
				&&SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserInfo){
			return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return null;
	}

	/**
	 * 如果用户未登陆  返回-1
	 * @return
	 */
	public static Integer getLoginUserId() {
		UserInfo userInfo=getLoginUser();
		if(userInfo==null){
			return -1;
		}
		return userInfo.getUserid();
	}
	
	/**
	 * 如果用户未登陆  返回 ""
	 * @return
	 */
	public static String getLoginUserName() {
		UserInfo userInfo=getLoginUser();
		if(userInfo==null){
			return "";
		}
		return userInfo.getName();
	}
}
