package com.java1234.dao.authDao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.RolePermission;

public interface RoleDao {

	/**
	 * 查询该用户的角色id
	 * @param userId
	 * @return
	 */
	public Set<Integer> findByUserId(Integer userId);
	
	/**
	 * 通过ids查询角色信息
	 * @param ids
	 * @return Set<String>
	 */
	public Set<String> findByIds(@Param("sIds")Set<Integer> ids);
	
	/**
	 * 添加角色
	 * @param role
	 * @return long
	 */
	public long addRole(Role role);
	
	/**
	 * 根据id删除角色
	 * @param id
	 * @return long
	 */
	public long delRoles(@Param("ids")List<Integer> ids);
	
	/**
	 * 查询所有角色
	 * @return List<Role>
	 */
	public List<Role> findAll();
	
	/**
	 * 更新RolePermission的关联关系
	 * @param rolePerm
	 * @return long
	 */
	public long updateRolePerm(RolePermission rolePerm);
	
	/**
	 * 添加RolePermission的关联关系
	 * @param rolePerm
	 * @return long
	 */
	public long addRolePerm(RolePermission rolePerm);
	
	/**
	 * 删除RolePermission的关联关系
	 * @param id
	 * @return long
	 */
	public long delRolePerms(@Param("ids")List<Integer> ids);
	
	/**
	 * 删除RolePermission的关联关系ByRoleId
	 * @param roleId
	 * @return
	 */
	public long delRolePermByRoleid(Integer roleId);
	
	/**
	 * 查询该id用户下的角色信息
	 * @param userId
	 * @return
	 */
	public List<Role> getByUserId(Integer userId);
	
	/**
	 * 查询用户的角色id
	 * @param userId
	 * @return
	 */
	public List<Integer> getUserIds(Integer userId);
	
	/**
	 * 查询该角色是否有某个用户拥有
	 * @param roleId
	 * @return
	 */
	public List<Role> getUserByRoleId(Integer roleId);
}
