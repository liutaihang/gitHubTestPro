package com.java1234.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.cache.CacheManager;

import com.alibaba.fastjson.JSONObject;
import com.java1234.Vo.UserVo;
import com.java1234.dao.authDao.UserDao;
import com.java1234.entity.auth.User;
import com.java1234.utils.EhcacheUtils;

public class LoginFilter extends FormAuthenticationFilter {
	
	@Resource
	private UserDao dao;
	
	@Resource
	private CacheManager manager;
    /**
     * 所有被拦截的请求都会经过的方法 。
     * @return 
     */
    public boolean onaccessdenied(ServletRequest request,ServletResponse response) throws Exception {
        HttpServletRequest httpservletrequest = (HttpServletRequest) request;
        String resurl = httpservletrequest.getServletPath();
        System.out.println("filters:" +resurl);
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
    		ServletResponse response, Object mappedValue) {
    	EhcacheUtils.setManager(manager);
    	Subject subject = getSubject(request, response);
    	HttpServletRequest req = (HttpServletRequest) request;
    	Object obj = subject.getPrincipal();
    	String uri = req.getRequestURI();
    	String url = req.getRequestURL().toString();
    	String sbs = (String) subject.getSession().getId();
    	boolean isAccess = false;
    	Session session = subject.getSession(false);
    	//判断用户是否登录且是否在同一浏览器内
    	String sid = (String) EhcacheUtils.get("myCache", "userSession");
    	UserVo uservo = (UserVo) EhcacheUtils.get("myCache", "user");//前面存入了用户sessionid和用户信息
    	if(subject.isAuthenticated()){
    		if(session != null && sid != null && uservo != null){//判断是否过期，未过期且在使用，再添加一次持续使用,延长过期时间
        		EhcacheUtils.put("myCache", "user", uservo);
        		EhcacheUtils.put("myCache", "userSession", sid);
    		}
    	}
    	//判断用户是否存在
    	if(obj != null && subject.isAuthenticated()){
    		String username = obj.toString();
        	User user = dao.getByUserName(username);
        	//现在匹配当前用户sessionId和用户信息，如果当前用户信息和前面存储的用户信息不一致，说明是不同的用户登录，如果一直说明是相同的用户登录
        	if(sid != null && user != null && StringUtils.isNotBlank(uservo.getUserName())){
    			if(!uservo.getUserName().equals(obj.toString()) && !StringUtils.equals(sid, sbs)){
    				isAccess = true;//名字不相同，sessionid不相同   不同的用户不同地方登录访问
    			}
    			if(uservo.getUserName().equals(obj.toString()) && !StringUtils.equals(sid, sbs)){
    				isAccess = true;//名字相同，sessionid不同，相同的用户不同地方登录访问-------(第一次访问的时候需要把ajax信息传递出去，所以返回true)
    				HttpServletResponse resp = (HttpServletResponse) response;
    				
    				//返回账号登陆挤掉信息
    				try {
						PrintWriter out = resp.getWriter();
						out.print(new JSONObject().put("data", new JSONObject().put("msg", "当前账号在其他地方登陆")));
					} catch (IOException e) {
						e.printStackTrace();
					}
    				subject.logout();
    			}
    			if(uservo.getUserName().equals(obj.toString()) && StringUtils.equals(sid, sbs)){
    				isAccess = true;//名字相同，sessionid相同，当前浏览器用户访问
    			}
    		}
    	}
    	System.out.println();
    	System.out.println("---------------------------------------------------------------");
    	System.out.println("=========>loginfilter____{" + uri + "\n=========>" + url + "\n=========>当前登陆账号：" + obj + "}");
    	System.out.println("---------------------------------------------------------------");
    	System.out.println("\n " + "\n");
    	return isAccess;
    }
}