//package com.java1234.service;
//
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
//import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
//import org.apache.shiro.web.servlet.AbstractShiroFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import com.java1234.shiro.ShiroPermissionFactory;
//
//@Component
//public class FilterChainDefinitionService {
//	
//	@Resource
//	private ShiroPermissionFactory factory;
//
//	
//	public void reloadFilter(){
//		synchronized(factory){
//			AbstractShiroFilter filter = null;
//			
//			 try {
//				filter = (AbstractShiroFilter) factory.getObject();
//				PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) filter  
//	                    .getFilterChainResolver();
//				
//				//过滤管理器
//				DefaultFilterChainManager chainManager = (DefaultFilterChainManager) resolver.getFilterChainManager();
//				//清除权限配置
//				chainManager.getFilterChains().clear();
//				factory.getFilterChainDefinitionMap().clear();
//				
//				//重新设置权限
//				factory.setFilterChainDefinitions(ShiroPermissionFactory.filterChainDefinitions);
//				Map<String, String> chains = factory.getFilterChainDefinitionMap();
//				System.out.println(chains);
//				if(!CollectionUtils.isEmpty(chains)){
//					 for (Map.Entry<String, String> chain : chains.entrySet()) {
//	                     chainManager.createChain(chain.getKey(), chain.getValue().replace(" ", ""));
//	                 }
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
