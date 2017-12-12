package com.java1234.service;

import java.util.Map;

public interface DynamicPermissionService {
	    /**
	     * 资源权限的配置字符串模板
	     */
	    public static final String PREMISSION_STRING="perms[{0}]";
	    /**
	     * 角色权限的配置字符串模板
	     */
	    public static final String ROLE_STRING="roles[{0}]";

	    /**
	     * 初始化时获取当前已定义的filterchains
	     */
	    void init();

	    /**
	     * 更新框架资源权限配置，需要线程同步,此方法会动态添加definitions
	     * 如果有重复的url，新的map将覆盖以前的map
	     * 也就是说，以前链接的权限配置会被新的权限配置覆盖
	     */
	     void updatePermission(Map<String, String> newDefinitions);

	    /**
	     * 需要线程同步,此方法会加载静态配置，DynamicPermissionDao查询出来的配置
	     *
	     */
	    void reloadPermission();
}
