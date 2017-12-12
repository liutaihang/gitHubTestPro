package com.java1234.service.impl.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.java1234.Vo.UserRoleVo;
import com.java1234.dao.authDao.PermissionDao;
import com.java1234.dao.authDao.RoleDao;
import com.java1234.dao.authDao.UserDao;
import com.java1234.entity.auth.User;
import com.java1234.entity.auth.UserRole;
import com.java1234.service.auth.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	public Set<String> getRoles(String userName) {
		User user = getByUserName(userName);
		Set<String> roleName = null;
		if(user != null){
			Set<Integer> ids = roleDao.findByUserId(user.getId());
			if(ids != null && ids.size() > 0){
				roleName = roleDao.findByIds(ids);
			}
		}
		try {
			System.out.println("================账号：" + userName + "拥有的角色=================");
			System.out.println(roleName.toString());
	    	System.out.println("\n " + "\n");
		} catch (Exception e) {
			System.out.println("没有角色");
		}
		return roleName;
	}

	public Set<String> getPermissions(String userName) {
		User user = getByUserName(userName);
		Set<Integer> ids = null;
		Set<Integer> pids = null;
		Set<String> permission = null;
		Set<String> perms = null;
		if(user != null){
			ids = roleDao.findByUserId(user.getId());
		}
		if(ids != null && ids.size() > 0){
			pids = permissionDao.findByRoleIds(ids);
		}
		if(pids != null && pids.size() > 0){
			permission = permissionDao.findByIds(pids);
		}
		if(permission != null && permission.size() > 0){
			perms = new HashSet<String>();
			for (String perm : permission) {
				if(StringUtils.isNotEmpty(perm)){
					perms.add(perm);
				}
			}
		}
		return perms;
	}

	@Override
	public long addUser(User user) {
		User sqlUser = userDao.getByUserName(user.getUserName());
		if(sqlUser == null){
			user.setPassword("123456");
			return userDao.addUser(user);
		}else{
			return 5000;
		}
	}

	@Override
	public long updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public long delUser(List<Integer> ids) {
		long result = 0;
		
		List<Integer> hasRole = new ArrayList<Integer>();
		List<Integer> notRole = new ArrayList<Integer>();
		if(ids != null && ids.size() > 0){
			for (Integer id : ids) {
				Set<Integer> roleIds = roleDao.findByUserId(id);
				if(roleIds != null && roleIds.size() > 0){
					hasRole.add(id);
				}else{
					notRole.add(id);
				}
			}
				delUserRoleByUserId(hasRole);
			result = userDao.deleteUser(ids);
		}
		return result;
	}

	@Override
	public long addUserRole(UserRoleVo vo) {
		long l = 0;
		if(vo != null && vo.getRoleIds() != null && vo.getUserId() != null){
			List<UserRole> Uroles = new ArrayList<UserRole>();
			Uroles = vo.convertVo();
			for (UserRole Urole : Uroles) {
				l += userDao.addUserRole(Urole);
			}
		}
		return l;
	}

	@Override
	public long updateUserRole(UserRoleVo vo) {
		long l = 0;
		if(vo != null && vo.getRoleIds() != null && vo.getUserId() != null){
			List<UserRole> Uroles = new ArrayList<UserRole>();
			Uroles = vo.convertVo();
			for (UserRole Urole : Uroles) {
				l += userDao.updateUserRole(Urole);
			}
		}
		return l;
	}

	@Override
	public long delUserRole(List<Integer> ids) {
		long l = 0;
		if(ids != null){
			l = userDao.delUserRole(ids);
		}
		return l;
	}

	@Override
	public long delUserRoleByUserId(List<Integer> userIds) {
		long result = 0;
		if(userIds != null && userIds.size() > 0){
			result = userDao.delUserRoleByUserId(userIds);
		}
		return result;
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public long setPwd(User user) {
		return userDao.setPwd(user);
	}

}
