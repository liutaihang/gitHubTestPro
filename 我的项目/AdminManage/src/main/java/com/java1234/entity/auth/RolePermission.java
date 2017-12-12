package com.java1234.entity.auth;

/**
 * 角色，权限资源关联类
 * @Title: RolePermission.java 
 * @Package com.java1234.entity.auth 
 * @author liuth 
 * @date Oct 17, 2017 6:09:36 PM 
 * @version V1.0
 */
public class RolePermission {
	private int id;
	private int RoleId;
	private int permissionId;//资源id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return RoleId;
	}
	public void setRoleId(int roleId) {
		RoleId = roleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public RolePermission(int roleId, int permissionId) {
		super();
		RoleId = roleId;
		this.permissionId = permissionId;
	}
	public RolePermission() {
		super();
	}
	
}
