package liu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		RedisDataBase base = new RedisDataBase();
		Map str = LoginController.copayProperty(new UserInfo("xiao", "xixi", "123123", "1", 23));
		System.out.println(base.setTimeOut("user_info", str, 30));
		System.out.println(base.hmget("user_info", "data"));
	}
}
