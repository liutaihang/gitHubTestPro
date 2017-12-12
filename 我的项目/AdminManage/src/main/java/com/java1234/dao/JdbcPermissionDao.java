package com.java1234.dao;

import java.util.LinkedHashMap;

public interface JdbcPermissionDao extends DynamicPermissionDao {
    /**
     * 获取url到权限的对应字符串
     *
     * @return
     */
    LinkedHashMap<String, String> generateDefinitions();
}