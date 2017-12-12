package com.java1234.Vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.java1234.entity.auth.UserRole;

public class UserRoleVo {
	private Integer userId;
	private Set<Integer> roleIds;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Set<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	public List<UserRole> convertVo(){
		List<UserRole> us = new ArrayList<UserRole>();
		UserRole userRole = null;
		if(roleIds.size() > 0 && userId != null){
			for (Integer roleId : roleIds) {
				userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				us.add(userRole);
			}
		}
		return us;
	}
	
}
