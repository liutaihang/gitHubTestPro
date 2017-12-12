package com.java1234.service.impl.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.java1234.Vo.TreeVo;
import com.java1234.dao.authDao.PermissionDao;
import com.java1234.dao.authDao.RoleDao;
import com.java1234.entity.auth.PResource;
import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.RolePermission;
import com.java1234.service.auth.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private PermissionDao permDao;
	
	@Override
	public long addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public long delRoles(Integer[] ids) {
		long result = 0;
		List<Integer> hasPerm = new ArrayList<Integer>();
		List<Integer> notPerm = new ArrayList<Integer>();
		List<Integer> hasUser = new ArrayList<Integer>();
		List<Integer> permIds = null;
		List<Role> roles = null;
		if(ids != null){
			for (Integer id : ids) {
				roles = roleDao.getUserByRoleId(id);
				if(roles != null && roles.size() > 0){
					hasUser.add(id);
					return 555;
				}
				permIds = permDao.findPermIdByRoleId(id);
				if(permIds != null && permIds.size() > 0){
					hasPerm.add(id);
				}else{
					notPerm.add(id);
				}
			}
			//先删除角色和资源的关联关系
			if(hasPerm != null && hasPerm.size() > 0){
				//如果有关联资源 先删除关联关系
				delRolePerms(CollectionUtils.arrayToList(ids));
			}
			result = roleDao.delRoles(CollectionUtils.arrayToList(ids));
		}
		return result;
	}

	@Override
	public long updateRolePerm(RolePermission rolePerm) {
		return roleDao.updateRolePerm(rolePerm);
	}

	@Override
	public long addRolePerm(RolePermission rolePerm) {
		return roleDao.addRolePerm(rolePerm);
	}

	@Override
	public long delRolePerms(List<Integer> ids) {
		return roleDao.delRolePerms(ids);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public long delRolePermByRoleid(Integer roleId) {
		return roleDao.delRolePermByRoleid(roleId);
	}

	@Override
	public List<Role> getByUserId(Integer id) {
		if(id != null){
			return roleDao.getByUserId(id);
		}
		return null;
	}
	
	@Override
	public List<TreeVo> getMenuTree(){
		List<PResource> list = permDao.findAll();
		List<TreeVo> trs = new ArrayList<TreeVo>();
		for (PResource pResource : list) {
			if(pResource != null){
				trs.add(new TreeVo(pResource));
			}
		}
		return trs;
	}

	@Override
	public Set<Integer> getPermIdByRoleId(Integer roleId){
		Set<Integer> id = new HashSet<Integer>();
		id.add(roleId);
		Set<Integer> permIds = permDao.findByRoleIds(id);
		return permIds;
	}
}
