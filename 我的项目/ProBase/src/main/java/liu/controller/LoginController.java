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

import liu.constant.Constant;
import liu.dao.BaseDao.RedisDataBase;
import liu.po.UserInfo;

@Controller
public class LoginController {

	@RequestMapping(value = "/lg", method = RequestMethod.GET)
	public String login(UserInfo user, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(false);
		String name = null;
		if(session == null){
			session = request.getSession();
			session.setAttribute("name", user.getName());
		}else{
			name = session.getAttribute("name").toString();
		}
		new RedisDataBase().setTimeOut(Constant.USER_INFO, copayProperty(user), 60*30);
		response.getWriter().print(user);
		return "redirect:login.html";
	}
	
	private Map<String, Object> copayProperty(UserInfo user){
		Map<String, Object> maps = new HashMap<>();
		maps.put(Constant.USER_NAME, user.getName());
		maps.put("acount", user.getAcount());
		maps.put("createTime", user.getCreateTime().getTime());
		maps.put(Constant.PASS_WORD, user.getPassword());
		maps.put("sex", user.getSex());
		maps.put("updateTime", user.getUpdateTime());
		return maps;
	}
	
	public static void main(String[] args) {
		
	}
}
