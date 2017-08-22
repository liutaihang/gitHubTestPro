package liu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import liu.manager.AnimalManager;
import liu.po.msq.Animal;


@RestController
@RequestMapping(value = "animal")
public class AnimalController {

	@Resource
	private AnimalManager animalManager;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(Animal animal,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Object obj = animalManager.add(animal);
		ToPage(request, obj, response);
	}

	private void ToPage(HttpServletRequest request, Object object, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		JSONObject obj = new JSONObject();
		obj.put("data", object);
		PrintWriter out = response.getWriter();
		out.print(obj);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Object obj = animalManager.findAll();
		ToPage(request, obj, response);
	}
}
