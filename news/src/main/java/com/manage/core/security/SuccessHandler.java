package com.manage.core.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.manage.core.entity.UserInfo;

@Component 
public class SuccessHandler extends  SavedRequestAwareAuthenticationSuccessHandler {
 
	 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
 
		setAlwaysUseDefaultTargetUrl(true);
		
		UserInfo user = LoginUser.getLoginUser();
		
		request.getSession().setAttribute("user", user);

		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	 
}
