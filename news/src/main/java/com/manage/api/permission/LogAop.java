package com.manage.api.permission;

import com.manage.core.utils.FastJsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

@Aspect
@Component
public class LogAop {

	
	private static final  Logger logger=  LoggerFactory.getLogger(LogAop.class);
	
 
	
    public LogAop() {
    }
    
    @Pointcut("execution(* com.manage.api.*.controller.*Controller.*(..))")
	public void LogAopPointcut(){}  

    /**
     * 拦截器具体实现
     *
     * @param pjp
     */
	@Around("LogAopPointcut()")
    public Object LognInterceptor(ProceedingJoinPoint pjp) {
     
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String uri = request.getRequestURI();
        
        StringBuilder sb=new StringBuilder();
        sb.append("uri:"+uri);
        Object[] objs=pjp.getArgs();
        try {
        	if(objs!=null){
        		  sb.append(" \n params:");
        		for (int i = 0; i < objs.length; i++) {
        			  sb.append(FastJsonUtil.parseToJSON(objs[i])+"\n");
				}
        	}
          
		} catch (Exception e) {
			
		}
        Map<String, String[]>  parameterMap=request.getParameterMap();
        if(parameterMap!=null){
        	for (Entry<String, String[]> entry : parameterMap.entrySet()) {
        		sb.append(" getParameterMap="+entry.getKey()+" value:"+entry.getValue());
        		if(entry.getValue()!=null){
        			for (String  s : entry.getValue()) {
            			sb.append(s+" , ");
    				}
        		}
			}
        	
        }
 
        String uuid=UUID.randomUUID().toString().replaceAll("-", "");
        logger.info(uuid+"====LogAop:"+sb.toString());
        
        // result的值就是被拦截方法的返回值
        Object result=null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
		  logger.info(uuid+"====LogAop result:"+result);
		return result;
    }
    
 
 
}
