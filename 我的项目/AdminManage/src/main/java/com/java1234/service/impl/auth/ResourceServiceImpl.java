package com.java1234.service.impl.auth;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.java1234.dao.authDao.PermissionDao;
import com.java1234.entity.auth.PResource;
import com.java1234.service.auth.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Resource
	private PermissionDao dao;
	
	@Override
	public List<PResource> findAll() {
		return dao.findAll();
	}

	@Override
	public long add(PResource pResource) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		String name = (String) subject.getPrincipal();
		if(pResource == null){
			throw new Exception("添加对象为空");
		}else if(StringUtils.isEmpty(pResource.getUrl())){
			throw new Exception("添加地址为空");
		}else if(StringUtils.isEmpty(name)){
			throw new Exception("未登录！！");
		}
		pResource.setPid(0);
		pResource.setCreateName(name);
		return dao.addResource(pResource);
	}

	@Override
	public long delResource(List<Integer> ids) {
		return dao.delResource(ids);
	}

	public List<PResource> findByUsername(String userName){
		return dao.findByUserName(userName);
	}
}
