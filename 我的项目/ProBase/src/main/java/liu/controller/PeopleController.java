package liu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import liu.dao.mybatisDao.BookDao;
import liu.dao.mybatisDao.DaoBook;
import liu.manager.PeopleManager;
import liu.po.People;
import liu.po.msq.Animal;

@RestController
@RequestMapping(value = "people")
public class PeopleController{
	 private static Logger logger =LoggerFactory.logger(PeopleController.class);
	@Resource
	private PeopleManager peopleManager;
	
	@Resource
	private BookDao dao;
	
//	@Resource
//	private DaoBook book;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		pring(peopleManager.getAll());
		response.setContentType("text/json");
		
		
		JSONObject object = new JSONObject();
		object.put("data", peopleManager.getAll());
		
		response.getWriter().println(object);
		logger.info("lai----------------->");
	}

	private void pring(Map<String, Object> maps){
		List<People> lists = (List<People>) maps.get("data");
		if(lists != null){
			for (People people : lists) {
				System.out.println(people);
			}
		}
	}
	
	@RequestMapping(value = "/tests")
	public void tests(HttpServletRequest request, HttpServletResponse response, Animal animal) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
//		obj.put("data", dao.findAll().add(book.getAll().get(0)));
		obj.put("data", animal);
		logger.info(obj.toJSONString());
		out.print(obj);
		System.out.println(animal.getEnvironment());
	}
}
