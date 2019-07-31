package com.manage.api.permission;

import com.manage.core.utils.AESUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckToken {

	private static final Logger logger = LoggerFactory.getLogger(CheckToken.class);


	@Autowired
	AESUtil aesUtil;

	@Pointcut("execution(* com.manage.api.*.controller.*Controller.*(..))")
	public void CheckTokenPointcut() {
	}

	/**
	 * 拦截器具体实现
	 *
	 * @param
	 */
	@Before("CheckTokenPointcut()")
	public void CheckTokenInterceptor(JoinPoint joinPoint) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		String uri = request.getRequestURI();

		logger.info("=======CheckToken uri:" + uri);

		if ("/api/binddevice".equals(uri) || "/api/login".equals(uri)) {
			return;
		}
	}

}
