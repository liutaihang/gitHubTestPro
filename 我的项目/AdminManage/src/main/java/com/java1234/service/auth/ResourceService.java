package com.java1234.service.auth;

import java.util.List;

import com.java1234.entity.auth.PResource;

/**
 * 权限资源
 * @Title: ResourceService.java 
 * @Package com.java1234.service.auth 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuth 
 * @date Oct 19, 2017 4:30:31 PM 
 * @version V1.0
 */
public interface ResourceService {
	/**
	 * 查询所有的资源
	 * @return List<PResource>
	 */
	public List<PResource> findAll();
	
	/**
	 * 添加权限资源
	 * @param pResource
	 * @return
	 * @throws Exception
	 */
	public long add(PResource pResource) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public long delResource(List<Integer> ids);
	
	/**
	 * 查询该用户名称下的权限资源
	 * @param userName
	 * @return
	 */
	public List<PResource> findByUsername(String userName);
}
