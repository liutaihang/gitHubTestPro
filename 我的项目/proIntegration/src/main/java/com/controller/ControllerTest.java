package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import po.Tree;

@Controller
public class ControllerTest {
	
	@RequestMapping(value = "/controllerT", method = RequestMethod.GET)
	public String controllert(Tree tree, HttpServletResponse response){
		if(tree.getName() != null){
			tree.setName("修改过后的");
		}else{
			tree = new Tree("controller",new BigDecimal(1),"类型");
		}
		System.out.println(tree);
		return "index";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public void sendMessage(Tree tree, HttpServletResponse response) throws IOException{

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(JSONObject.toJSON(tree).toString());
		out.print(JSONObject.toJSON(tree));
	}
}
