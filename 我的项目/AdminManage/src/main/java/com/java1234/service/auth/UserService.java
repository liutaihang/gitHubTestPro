package com.java1234.service.auth;

import java.util.List;
import java.util.Set;

import com.java1234.entity.auth.User;
import com.java1234.Vo.UserRoleVo;

public interface UserService {

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
	public Set<String> getRoles(String userName);
	
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
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public long updateUser(User user);
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	public long delUser(List<Integer> ids);
	
	/**
	 * 添加用户,角色关系
	 * @param userRole
	 * @return
	 */
	public long addUserRole(UserRoleVo vo);
	
	/**
	 * 修改
	 * @param userRole
	 * @return
	 */
	public long updateUserRole(UserRoleVo vo);
	
	/**
	 * 删除
	 * @param userRole
	 * @return
	 */
	public long delUserRole(List<Integer> ids);
	
	/**
	 * 删除UserRole ByUserId
	 * @param userId
	 * @return
	 */
	public long delUserRoleByUserId(List<Integer> userIds);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAll();
	
	
	/**
	 * 密码修改
	 * @param user
	 * @return
	 */
	public long setPwd(User user);
}
