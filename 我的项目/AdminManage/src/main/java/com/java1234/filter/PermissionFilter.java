package com.java1234.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.cache.CacheManager;

import com.java1234.Vo.UserVo;
import com.java1234.controller.BaseController;
import com.java1234.utils.EhcacheUtils;

public class PermissionFilter extends AccessControlFilter{

	@Resource
	private CacheManager manager;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
    	EhcacheUtils.setManager(manager);
		Subject subject = getSubject(request, response);
		HttpServletRequest req = (HttpServletRequest) request;
    	String uri = req.getRequestURI();
    	String url = req.getRequestURL().toString();
    	UserVo uservo = (UserVo) EhcacheUtils.get("myCache", "user");
    	
	    System.out.println("-------------------------(permissionFilter)---------------------------------");
    	System.out.println(uri + "\n" + url);
    	uri = uri.substring(uri.indexOf("/", 2), uri.length());
    	System.out.println("截取后的" + uri);
	    System.out.println("----------------------------------------------------------------------------");
    	System.out.println("\n " + "\n");
    	if(uservo != null && subject.getPrincipal() != null && "/".equals(uri)){
    		return true;
    	}
    	if(subject.isPermitted(uri)){
    		return true;
    	}
    	if(BaseController.isAjax(request)){
    		if(uservo == null || subject.getPrincipal() == null){//判断是否登录过期或未登录
    			return false;
    		}
    		//过了地址权限判断说明是登录成功的ajax请求
    		return true;
    	}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if(subject.getPrincipal() == null){
			//进这里说明没有登录,重定向到登录页面
			saveRequest(request);  
            WebUtils.issueRedirect(request, response, "/user/login.jsp"); 
		}else{
			//进这里说明是登录但是没有权限，重定向到没权限的页面
			WebUtils.issueRedirect(request, response, "/unauthor.jsp"); 
		}
		return false;
	}

}
