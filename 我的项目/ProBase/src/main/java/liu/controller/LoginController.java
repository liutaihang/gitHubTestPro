package liu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.list.TreeList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import liu.constant.Constant;
import liu.dao.BaseDao.RedisDataBase;
import liu.po.msq.UserInfo;

@Controller
@RequestMapping("login")
public class LoginController {

	@RequestMapping(value = "/lg", method = RequestMethod.GET)
	public String login(UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		String name = null;
		if(session == null){
			session = request.getSession();
			if(user.getPassword().equals("password") && user.getAcount().equals("account")){
				session.setAttribute("token", user.getPassword() + user.getAcount());
			}else{
				response.getWriter().print("密码或账户错误!");
				return "redirect:findAll.html";
			}
			session.setAttribute("name", user.getName());
		}else{
			name = session.getAttribute("name").toString();
		}
		new RedisDataBase().setTimeOut(Constant.USER_INFO, copayProperty(user), 60*30);
		response.getWriter().print(user);
		return "redirect:login.html";
	}
	
	private static Map<String, String> copayProperty(UserInfo user){
		Map<String, String> maps = new HashMap<>();
		
		JSONObject obj = new JSONObject();
		obj.put("user", user);
		maps.put(user.getName(), obj.toJSONString());
		return maps;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		RedisDataBase base = new RedisDataBase();
//		Map str = LoginController.copayProperty(new UserInfo("xiao", "xixi", "123123", "1", 23));
//		System.out.println(base.setTimeOut("user_info", str, 30));
//		System.out.println(base.hmget("user_info", "data"));
//		
//		Map<String, Object> maps = new HashMap<String, Object>();
//		System.out.println(maps.equals(str));
//		List<Integer> lists = new TreeList();
//		Set<Integer> sets = new TreeSet<>();
//		for(int i = 0; i < 10; i++){
//			int tmp = (int) (Math.random() * 10);
//			sets.add(tmp);
//			lists.add(tmp);
//		}
//		System.out.println(sets.size());
//		System.out.print("set:");
//		for (Integer integer : sets) {
//			System.out.print(integer);
//		}
//		System.out.println();
//		System.out.print("list:");
//		for (Integer integer : lists) {
//			System.out.print(integer);
//		}
		long l = System.currentTimeMillis();
		List<String> lists = new ArrayList<>(10000000);
		for(int i = 0; i < 10000000; i++){
			lists.add("" + (int)(Math.random() * 10));
		}
		System.out.println(System.currentTimeMillis() - l + "|" + lists.size());
		
		long s = System.currentTimeMillis();
		Map<String, Object> maps = new TreeMap<>();
		for(int i = 0; i < 10000000; i++){
			double du = Math.random();
			maps.put("" + (int) (du * 10), (int)(du * 100));
		}
		System.out.println(System.currentTimeMillis() - s + "|" + maps.size());
		
		long b = System.currentTimeMillis();
		Set<String> sets = new TreeSet<>();
		for(int i = 0; i < 10000000; i++){
			int tmp = (int)(Math.random() * 10);
			sets.add("" + tmp);
		}
		System.out.println(System.currentTimeMillis() - b + "|" + sets.size());
		
		
		String str = "123321l123";
		System.out.println(str.substring(0, str.lastIndexOf("l")));
	}
}
