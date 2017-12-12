package com.java1234.controller.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java1234.Vo.TreeVo;
import com.java1234.Vo.UserRoleVo;
import com.java1234.Vo.UserVo;
import com.java1234.controller.BaseController;
import com.java1234.entity.auth.PResource;
import com.java1234.entity.auth.Role;
import com.java1234.entity.auth.User;
import com.java1234.service.auth.ResourceService;
import com.java1234.service.auth.RoleService;
import com.java1234.service.auth.UserService;
import com.java1234.utils.EhcacheUtils;



/**
 * 用户Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	@Resource
	private UserService service;
	
	@Resource
	private ResourceService resourceService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private CacheManager cacheManager;
	
	@RequestMapping("/loginout")
	public String loginout(){
		EhcacheUtils.setManager(cacheManager);
		Subject subject = SecurityUtils.getSubject();
		System.out.println("登出：" +subject.getSession().getId());
    	System.out.println("\n " + "\n");
		subject.logout();
		EhcacheUtils.remove("myCache", "user");
		EhcacheUtils.remove("myCache", "userSession");
		return "user/login";
	}
	@RequestMapping("/login")
	public String doLogin(HttpServletRequest request, HttpServletResponse response, Model model) {  
	    String msg = "";  
	    String retUrl = "user/login";
	    EhcacheUtils.setManager(cacheManager);
	    response.setCharacterEncoding("utf-8");
	    String userName = request.getParameter("userName");  
	    String password = request.getParameter("password");  
	    Subject subject = SecurityUtils.getSubject();  
		Session session=subject.getSession(false);
		if(session == null){
			session = subject.getSession();
		}
	    if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
	    	out(response, "用户名或密码不能为空！！");
	    	return "user/login";
	    }
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);  
	    try {  
	    	token.setRememberMe(false); 
	        subject.login(token);  
	        retUrl = "redirect:/user/index.htm";
	        //登录验证成功将用户缓存到Ehcache中
	        EhcacheUtils.put("myCache", "user", new UserVo(userName, password, true));
	        EhcacheUtils.put("myCache", "userSession", session.getId());
	    } catch (IncorrectCredentialsException e) {  
	        msg = "账号" + token.getPrincipal() + "登录密码错误";  
	        model.addAttribute("message", msg);  
	        request.setAttribute("errorMsg", "账号" + token.getPrincipal() + "登录密码错误");
	        out(response,msg);
	    }catch (UnknownAccountException uae) {
	    	request.setAttribute("errorMsg","没有 " + token.getPrincipal() + "这个用户！");
        } catch (Exception e) {
	    	request.setAttribute("errorMsg","账号或密码错误！");
			out(response, "账号或密码错误！");
			return "user/login";
		} 
	    SavedRequest saved = WebUtils.getSavedRequest(request);
	    System.out.println("-------------------------(userController)---------------------------------");
	    System.out.println("========>当前登录用户：" + userName);
	    if(saved != null){
		    System.out.println("========>获取上次登录的地址：" + saved.getRequestUrl());
		    System.out.println("--------------------------------------------------------------------------");
	    	System.out.println("\n " + "\n");
	    }
	    return retUrl;  
	}  
	
	/**
	 * 初始化菜单栏
	 * @param response
	 */
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	public void getMenuList(HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		if(username != null){
			List<PResource> list = resourceService.findByUsername(username);
			JSONArray arr = converJson(list);
			out(response, arr);
		}else{
			out(response, "用户名为空,用户未登录！");
		}
	}
	
	@RequestMapping(value = "/wel", method = RequestMethod.GET)
	public void conSys(HttpServletRequest request, HttpServletResponse response){
		EhcacheUtils.setManager(cacheManager);
		UserVo uservo = (UserVo) EhcacheUtils.get("myCache", "user");
		if(uservo != null){
			User user=service.getByUserName(uservo.getUserName());
			out(response, user);
		}else{
			out(response, "用户未登录！");
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addUser(User user, HttpServletResponse response){
		long result = 0;
		if(user != null && StringUtils.isNotBlank(user.getUserName())
				&& StringUtils.isNotBlank(user.getNickName())){
			User old = service.getByUserName(user.getUserName());
			if(old == null){
				result = service.addUser(user);
			}else{
				result = 5000;
			}
		}
		retValue(response, result);
		
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public void delUser(@RequestParam("ids[]")Integer[]userIds, HttpServletResponse response){
		long result = 0;
		if(userIds != null && userIds.length > 0){
			List<Integer> list= CollectionUtils.arrayToList(userIds);
			result = service.delUser(list);
		}
		retValue(response, result);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateUser(HttpServletResponse response, HttpServletRequest request){
		Object obj1 = request.getParameter("uservo");
		EhcacheUtils.setManager(cacheManager);
		Object obj = request.getParameter("newPwd");
		User user = (User) EhcacheUtils.get("myCache", "userInfo");
		if(user != null && obj1 != null){
			 JSONObject jobj = JSON.parseObject(obj1.toString());
			if(StringUtils.isNotBlank(jobj.getString("password"))){
				user.setPassword(jobj.getString("password"));
//				user.setNickName(jobj.getString("nickName"));
//				&& StringUtils.isNotBlank(jobj.getString("nickName"))
				service.updateUser(user);
				out(response, "修改成功！");
				EhcacheUtils.loginOut();
			}else{
				out(response, "输入密码为空！");
			}
		}else{
			out(response, "修改失败！");
		}
	}
	
	/**
	 * 验证当前修改密码是否正确
	 * @param response
	 * @param request
	 * @param password
	 */
	@RequestMapping(value = "/verifyPwd", method = RequestMethod.POST)
	public void verifyPwd(HttpServletResponse response, HttpServletRequest request, String password){
		EhcacheUtils.setManager(cacheManager);
		User user = (User) EhcacheUtils.get("myCache", "userInfo");
		if(user == null){
			request.setAttribute("errorMsg","用户未登录！");
		}else if(StringUtils.isNotBlank(password)){
			if(password.equals(user.getPassword())){
				out(response, "success");
			}else{
				out(response, "error");
			}
		}
	}
	
	/**
	 * 返回参数
	 * @param response
	 * @param result
	 */
	private void retValue(HttpServletResponse response, long result) {
		if(result > 0){
			out(response, "执行成功！");
		}else if(result == 0){
			out(response, "未执行或执行失败！");
		}else if(result == 5000){
			out(response, "用户名已存在！");
		}
	}
	
	@RequestMapping(value = "/delUserRole")
	public void delUserRole(List<Integer> ids, HttpServletResponse response){
		long result = service.delUserRole(ids);
		retValue(response, result);
	}
	
	@RequestMapping(value = "/updateUserRole")
	public void updateUserRole(UserRoleVo vo, HttpServletResponse response){
		long result = service.updateUserRole(vo);
		retValue(response, result);
	}
	
	@RequestMapping(value = "/addUserRole")
	public void addUserRole(@RequestParam("roleIds[]")Integer[] roleIds, Integer userId, 
			HttpServletResponse response){
		long result = 0;
		if(roleIds != null && roleIds.length >0 && userId != null && roleIds[0] != -1){
			UserRoleVo vo = new UserRoleVo();
			//先清空
			List<Integer> userid = new ArrayList<Integer>();
			userid.add(userId);
			service.delUserRoleByUserId(userid);
			//再添加
			vo.setRoleIds(new HashSet<Integer>(Arrays.asList(roleIds)));
			vo.setUserId(userId);
			result = service.addUserRole(vo);
		}
		if(userId != null && roleIds[0] == -1){
			List<Integer> userid = new ArrayList<Integer>();
			userid.add(userId);
			result = service.delUserRoleByUserId(userid);
		}
		retValue(response, result);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public void findAll(HttpServletResponse response){
		List<User> list = service.findAll();
		List<UserVo> vos = new ArrayList<UserVo>();
		StringBuffer str = null;
		//数据转换，拼接角色名
		for (User user : list) {
			List<Role> s = roleService.getByUserId(user.getId());
			UserVo vo = new UserVo(user);
			if(s != null && s.size() > 0){
				str = new StringBuffer();
				for (int i=0; i< s.size(); i++) {
					if(i == s.size()-1){
						str.append(s.get(i).getRoleName());
						break;
					}
					str.append(s.get(i).getRoleName() + ",");
				}
				vo.setRole(str.toString());
			}else{
				vo.setRole("未添加角色");
			}
			vos.add(vo);
		}
		if(vos != null && vos.size() > 0){
			out(response, retParam(vos, vos.size()));
		}else{
			out(response, "系统异常！");
		}
	}
	
	@RequestMapping(value = "/Tree", method = RequestMethod.POST)
	public void findRoleByUser(HttpServletResponse response, HttpServletRequest request){
		String userId = request.getParameter("userId");
		List<TreeVo> vos = new ArrayList<TreeVo>();
		List<Integer> roleIds = new ArrayList<Integer>();
		List<Role> roles = roleService.findAll();
		if(roles != null && roles.size() >0){
			for (Role role : roles) {
				vos.add(new TreeVo(role));
			}
			if(StringUtils.isNotBlank(userId)){
				List<Role> rs = roleService.getByUserId(Integer.parseInt(userId));
				if(rs != null && rs.size() > 0){
					for (Role role : rs) {
						roleIds.add(role.getId());
					}
				}
			}
			JSONObject jobj = new JSONObject();
			jobj.put("vos", vos);
			jobj.put("roleIds", roleIds);
			out(response, jobj);
			return;
		}
		out(response, "尚未添加角色！");
	}
	
	@RequestMapping(value = "/setPwd", method = RequestMethod.POST)
	public void setPassword(Integer id, String pwd, HttpServletRequest request, HttpServletResponse response){
		EhcacheUtils.setManager(cacheManager);
		User cacheUser = (User) EhcacheUtils.get("myCache", "userInfo");
		
		if(id == null || StringUtils.isBlank(pwd)){
			out(response, "密码或id为空！");
			return;
		}
		if(cacheUser != null){
			if(id == cacheUser.getId()){
				out(response, "donot");
				return;
			}
		}
		User user = new User();
		user.setId(id);
		user.setPassword(pwd);
		long num = service.setPwd(user);
		if(num >= 1){
			out(response, "修改成功！");
		}
	}
}
