package com.java1234.service.auth;

import java.util.List;
import java.util.Set;

import com.java1234.Vo.TreeVo;
import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.RolePermission;

public interface RoleService {
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public long addRole(Role role);
	
	/**
	 * 角色删除
	 * @param id
	 * @return
	 */
	public long delRoles(Integer[] ids);
	
	/**
	 * 角色,权限关系
	 * @param rolePerm
	 * @return
	 */
	public long updateRolePerm(RolePermission rolePerm);
	
	/**
	 * 添加角色，权限关系
	 * 如果角色id或资源id在数据库中不存在会报错
	 * @param rolePerm
	 * @return
	 */
	public long addRolePerm(RolePermission rolePerm);
	
	/**
	 * 删除角色，权限关系
	 * @param id
	 * @return
	 */
	public long delRolePerms(List<Integer> ids);
	
	public List<Role> findAll();
	
	/**
	 * 删除集合Roleids的资源关联关系
	 * @param Roleids
	 * @return long
	 */
	public long delRolePermByRoleid(Integer Roleids);
	
	/**
	 * 查询该id用户的角色信息
	 * @param id
	 * @return List<Role>
	 */
	public List<Role> getByUserId(Integer id);
	
	/**
	 * 获取初始化树
	 * @return
	 */
	public List<TreeVo> getMenuTree();
	
	/**
	 * 获取当前角色下的权限
	 * @param roleId
	 * @return
	 */
	public Set<Integer> getPermIdByRoleId(Integer roleId);
}
