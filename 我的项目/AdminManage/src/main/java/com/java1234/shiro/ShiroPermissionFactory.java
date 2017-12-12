//package com.java1234.shiro;
//
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.config.Ini;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.config.IniFilterChainResolverFactory;
//import org.springframework.util.CollectionUtils;
//
//import com.java1234.dao.authDao.PermissionDao;
//import com.java1234.entity.auth.PResource;
//
//public class ShiroPermissionFactory extends ShiroFilterFactoryBean{
//
//	public static final String PERMISSION = "{1},perms[\"{0}\"]";
//	
//	public static String filterChainDefinitions = "";
//	@Resource
//	private PermissionDao dao;
//	
//	@Override
//	public void setFilterChainDefinitions(String definitions) {
//		filterChainDefinitions = definitions;
//		System.out.println("定死的权限" + filterChainDefinitions);
//		Map<String, String> chains = new HashMap<>();
//		List<PResource> list = dao.findAll();
//		for (PResource resource : list) {
//			Object[] strs = new String[]{resource.getPermission(), "authc"};
//			if(StringUtils.isNotBlank(resource.getUrl()) 
//					&& StringUtils.isNotBlank(resource.getPermission())){
//				chains.put(resource.getUrl(), 
//						MessageFormat.format(PERMISSION, strs));
//			}
//		}
//		Ini ini = new Ini();
//		chains.put("/**", "authc");
//		//加载配置中的过滤链
//		ini.load(filterChainDefinitions);
//		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
//		if(CollectionUtils.isEmpty(section)){
//			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//		}
//		
//		//加载数据库的过滤链
//		section.putAll(chains);
//		setFilterChainDefinitionMap(section);
//	}
//}
