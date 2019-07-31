package com.manage.core.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cglib.beans.BeanCopier;

public class BeanUtil {

	/**
     * the beanCopierMap
     */
    private static final ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();
 
    /**
     * @description 两个类对象之间属性内容copy
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object  target) {
    	 String key = genKey(source.getClass(), target.getClass());  
         BeanCopier copier = null;  
         if (!beanCopierMap.containsKey(key)) {  
             copier = BeanCopier.create(source.getClass(), target.getClass(), false);  
             beanCopierMap.put(key, copier);  
         } else {  
             copier = beanCopierMap.get(key);  
         }  
         copier.copy(source, target, null);  
    }
    
    private static String genKey(Class<?> sourceClazz, Class<?> targetClazz) {  
        return sourceClazz.getName() +"_"+ targetClazz.getName();  
    } 
    
 
}
