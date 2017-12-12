package com.java1234.controller.whitelist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java1234.controller.BaseController;
import com.java1234.utils.SystemTalk;

@RestController
@RequestMapping("wihteList")
public class WhiteListController extends BaseController{

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public void findAll(HttpServletResponse response, HttpServletRequest request){
//		SystemTalk.postForm(SystemTalk.SELECT, SystemTalk.PARAM, "123");
		String str = request.getParameter("result");
		JSONArray array = null;
		JSONObject obj = new JSONObject();
		obj.put("pho", "123");
		obj.put("RealName", "123");
		obj.put("state", 1);
		array = new JSONArray();
		array.add(obj);
		if(StringUtils.isNotBlank(str)){
			array =  JSONArray.parseArray(str);
		}
		System.out.println(str);
		System.out.println(array.toJSONString());
		out(response, retParam(array, array.size()));
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public void delCustom(List<String> loginNames, HttpServletResponse response, 
			HttpServletRequest request){
		SystemTalk.postForm(SystemTalk.DELETE, SystemTalk.PARAM, loginNames.toString());
		String str = request.getParameter(SystemTalk.RESULT);
		JSONArray array = null;
		if(StringUtils.isNotBlank(str)){
			array =  JSONArray.parseArray(str);
		}
		System.out.println(array.toJSONString());
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateCustom(Object custom, HttpServletResponse response, 
			HttpServletRequest request){
		SystemTalk.postForm(SystemTalk.UPDATE, SystemTalk.PARAM, custom.toString());
		String str = request.getParameter(SystemTalk.RESULT);
		JSONArray array = null;
		if(StringUtils.isNotBlank(str)){
			array =  JSONArray.parseArray(str);
		}
		System.out.println(array.toJSONString());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addCustom(Object custom, HttpServletResponse response, 
			HttpServletRequest request){
		SystemTalk.postForm(SystemTalk.INSERT, SystemTalk.PARAM, custom.toString());
		String str = request.getParameter(SystemTalk.RESULT);
		JSONArray array = null;
		if(StringUtils.isNotBlank(str)){
			array =  JSONArray.parseArray(str);
		}
		System.out.println(array.toJSONString());
	}
}
