package com.java1234.entity.auth;

/**
 * 用户角色关系类
 * @Title: UserRole.java 
 * @Package com.java1234.entity.auth 
 * @author liuth 
 * @date Oct 17, 2017 6:39:27 PM 
 * @version V1.0
 */
public class UserRole {
	private int id;
	private Integer roleId;//角色id
	private Integer userId;//用户id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
