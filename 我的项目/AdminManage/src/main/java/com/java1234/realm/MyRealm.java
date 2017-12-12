package com.java1234.realm;


import javax.annotation.Resource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.cache.CacheManager;

import com.java1234.entity.auth.User;
import com.java1234.service.auth.UserService;
import com.java1234.utils.EhcacheUtils;

public class MyRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	
	@Resource
	private CacheManager cacheManager;
	
	/**
	 * 为当限前登录的用户授予角色和权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(userName));
		authorizationInfo.setStringPermissions(userService.getPermissions(userName));
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		EhcacheUtils.setManager(cacheManager);
			User user=userService.getByUserName(userName);
			if(user!=null){
				if(!user.getUserName().equals(userName)){
					throw new AccountException("帐号或密码不正确！");
				}
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
				EhcacheUtils.put("myCache", "userInfo", user);
				return authcInfo;
			}else{
				throw new AccountException("帐号或密码不正确！");			
			}
	}
	/**
	 * 断言提交的AuthenticationToken的凭证与存储的账户验证信息的凭证相匹配，如果没有，则抛出一个AuthenticationException。
	 */
	@Override
	protected void assertCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) throws AuthenticationException {
		// TODO Auto-generated method stub
		super.assertCredentialsMatch(token, info);
	}

}
