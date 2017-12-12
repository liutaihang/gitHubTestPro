package com.java1234.controller.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java1234.Vo.RolePermissionVo;
import com.java1234.controller.BaseController;
import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.RolePermission;
import com.java1234.service.auth.RoleService;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController{
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public void findAll(HttpServletResponse response){
		List<Role> roles = roleService.findAll();
		out(response, retParam(roles, roles.size()));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addRole(Role role, HttpServletResponse response){
		if(role != null && StringUtils.isNotBlank(role.getRoleName()) 
				&& StringUtils.isNotBlank(role.getNickName())){
			out(response, roleService.addRole(role));
		}
		out(response, 0);
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public void delRole(@RequestParam("ids[]")Integer[] ids, HttpServletResponse response){
		long result = 0;
		if(ids != null && ids.length > 0){
			result = roleService.delRoles(ids);
		}
		if(result == 555){
			out(response, "有用户拥有该角色，不能删除！");
			return;
		}
		if(result > 0){
			out(response, "删除成功！");
		}else{
			out(response, "删除失败");
		}
	}
	
	@RequestMapping(value = "/addRolePerm", method = RequestMethod.POST)
	public void addRolePerm(@RequestParam("ids[]")Integer[] ids,@RequestParam("roleId")Integer roleId, HttpServletResponse response){
		long result = 0;
		//先删除
		Integer [] roles = {roleId};
		roleService.delRolePerms(Arrays.asList(roles));
		
		if(ids != null && ids.length > 0 && roleId != null && ids[0] != -1){
			for (Integer id : ids) {
				result += roleService.addRolePerm(new RolePermission(roleId, id));
			}
		}else if(roleId != null && ids[0] == -1){
			result = roleService.delRolePermByRoleid(roleId);
		}
		out(response, result);
	}
	
	@RequestMapping(value = "updateRolePerm", method = RequestMethod.POST)
	public void updateRolePerm(RolePermissionVo vo, HttpServletResponse response){
		List<RolePermission> permissions = new ArrayList<RolePermission>();
		long result = 0;
		if(vo != null){
			vo.convertVo(permissions);
			for (RolePermission rolePerm : permissions) {
				result += roleService.updateRolePerm(rolePerm);
			}
		}
		out(response, result);
	}
	
	@RequestMapping(value = "delRolePerm", method = RequestMethod.GET)
	public void delRolePerm(List<Integer> ids, HttpServletResponse response){
		long result = 0;
		if(ids.size() > 0){
			result = roleService.delRolePerms(ids);
		}
		out(response, result);
	}
	
	@RequestMapping(value = "/Tree", method = RequestMethod.POST)
	public void getMenuTree(Integer roleId, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("perms", roleService.getPermIdByRoleId(roleId));
		map.put("tree", roleService.getMenuTree());
		out(response, map);
	}
}
