package com.java1234.dao.authDao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.auth.PResource;

/**
 * @Title: PermissionDao.java 
 * @Package com.java1234.dao 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuth 
 * @date Oct 12, 2017 2:56:26 PM 
 * @version V1.0
 */
public interface PermissionDao {
	
	/**
	 * 根据id查询权限
	 * @param ids
	 * @return
	 */
	public Set<String> findByIds(@Param("Pids")Set<Integer> ids);
	
	/**
	 * 通过角色id查询权限
	 * @param roleId
	 * @return
	 */
	public Set<Integer> findByRoleIds(@Param("roleIds")Set<Integer> roleId);
	
	/**
	 * 查询与该角色关联的资源id
	 * @param roleId
	 * @return
	 */
	public List<Integer> findPermIdByRoleId(Integer roleId);
	
	/**
	 * 查询所有的资源
	 * @return
	 */
	public List<PResource> findAll();
	
	/**
	 * 查询该用户名称下的权限资源
	 * @param userName
	 * @return
	 */
	public List<PResource> findByUserName(String userName);
	
	/**
	 * 添加权限资源
	 * @param pResource
	 * @return
	 */
	public long addResource(PResource pResource);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public long delResource(@Param("ids")List<Integer> ids);
	
	/**
	 * 更新
	 * @param pResource
	 */
	public void updateResource(PResource pResource);
}
