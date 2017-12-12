package com.java1234.Vo;

import java.util.ArrayList;
import java.util.List;

import com.java1234.entity.auth.RolePermission;

public class RolePermissionVo {
	private Integer roleId;
	private Integer [] perms;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer[] getPerms() {
		return perms;
	}
	public void setPerms(Integer[] perms) {
		this.perms = perms;
	}
	public void convertVo(List<RolePermission> list){
		List<RolePermission> Rp = new ArrayList<RolePermission>();
		RolePermission rPerm = null;
		if(perms.length > 0 && roleId != null){
			for (Integer perm : perms) {
				rPerm = new RolePermission();
				rPerm.setPermissionId(perm);
				rPerm.setRoleId(roleId);
			}
		}
		list = Rp;
	}
}
