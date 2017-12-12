package com.java1234.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

public class EhcacheUtils {
	private static CacheManager manager; 
	  
    public static CacheManager getManager() {
		return manager;
	}

	public static void setManager(CacheManager manager) {
		EhcacheUtils.manager = manager;
	}

	public static Object get(String cacheName, Object key) {  
        Cache cache = manager.getCache(cacheName);  
        if (cache != null) {  
        	ValueWrapper element = cache.get(key);  
            if (element != null) {  
                return element.get();  
            }  
        }  
        return null;  
    }  
  
    public static void put(String cacheName, Object key, Object value) {  
        Cache cache = manager.getCache(cacheName);  
        if (cache != null) {  
            cache.put(key, value);  
        }  
    }  
  
    public static boolean remove(String cacheName, Object key) {  
        Cache cache = manager.getCache(cacheName);  
        if (cache != null) { 
        	cache.evict(key);
            return true;
        }  
        return false;  
    }  
    
    public static void loginOut(){
    	Subject subject = SecurityUtils.getSubject();
    	subject.logout();
    	remove("myCache", "user");
    	remove("myCache", "userInfo");
    }
}
