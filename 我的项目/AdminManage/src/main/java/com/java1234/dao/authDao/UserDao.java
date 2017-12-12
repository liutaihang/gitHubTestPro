package com.java1234.dao.authDao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.User;
import com.java1234.entity.auth.UserRole;

public interface UserDao {

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public User getByUserName(String userName);
	
	/**
	 * 通过用户名查询角色信息
	 * @param userName
	 * @return
	 */
	public List<Role> getRoles(String userName);
	
	/**
	 * 通过用户名查询权限信息
	 * @param userName
	 * @return
	 */
	public Set<String> getPermissions(String userName);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public long addUser(User user);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public long updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public long deleteUser(@Param("userIds")List<Integer> ids);
	
	/**
	 * 更新用户，角色关系表
	 * @param Urole
	 * @return
	 */
	public long updateUserRole(UserRole Urole);
	
	/**
	 * 添加用户，角色关系
	 * @param Urole
	 * @return
	 */
	public long addUserRole(UserRole Urole);
	
	/**
	 * 删除用户，角色关系
	 * @param id
	 * @return
	 */
	public long delUserRole(@Param("ids")List<Integer> ids);
	
	/**
	 * 删除用户，角色关系byUserId
	 * @param userId
	 * @return
	 */
	public long delUserRoleByUserId(@Param("userIds")List<Integer> userIds);
	
	/**
	 * 查询所有的
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 密码修改
	 * @return
	 */
	public long setPwd(User user);
}
