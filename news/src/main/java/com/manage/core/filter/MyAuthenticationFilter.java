package com.manage.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.manage.core.exception.ExceptionCode;
import com.manage.core.utils.AESUtil;
import com.manage.webmgr.config.Constants;
import com.manage.webmgr.user.dao.UserMapper;
import com.manage.webmgr.user.entity.User;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	AESUtil aesUtil;
	@Autowired
	UserMapper userMapper;
	/**
	 * 
	 */
	public MyAuthenticationFilter() {
		// 默认处理登录的页面是/login,方式是POST
		// super();
		AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/loginin", "POST");
		this.setRequiresAuthenticationRequestMatcher(requestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String validCodeString = (String) request.getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_CODE);

		String inputValidCode = request.getParameter(Constants.REQUEST_PARM_CAPTCHA);

		if (validCodeString == null || !validCodeString.equalsIgnoreCase(inputValidCode)) {
 			throw new AuthenticationServiceException(ExceptionCode.login_captcha_error);
		}

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		} else {
			String username = this.obtainUsername(request);
			String pd = this.obtainPassword(request);
			if (username == null) {
				username = "";
			}

			if (pd == null) {
				pd = "";
			}
			 
			if (StringUtils.isEmpty(pd)) {
				throw new AuthenticationServiceException(ExceptionCode.login_captcha_error);
			}
			String cKey = (String) request.getSession().getAttribute(Constants.SESSION_KEY_OF_TOKEN);
			if (StringUtils.isBlank(cKey)) {
				throw new AuthenticationServiceException(ExceptionCode.login_incomplete_data);
			}
			
			request.getSession().setAttribute(Constants.SESSION_KEY_OF_USERNAME+request.getSession().getId(), username);
			
			//request.getSession().removeAttribute(Constants.SESSION_KEY_OF_TOKEN);
			
			User exUser = userMapper.selectByUserName(username);
			
			if(exUser!=null)
				request.getSession().setAttribute("userRole",exUser.getRole());
			
			String pd1 =aesUtil.encryptDefaultKey(aesUtil.decrypt(pd, cKey));

			username = username.trim();
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, pd1);

			this.setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}

}