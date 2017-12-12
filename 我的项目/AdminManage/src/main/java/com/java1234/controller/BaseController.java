package com.java1234.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java1234.entity.auth.PResource;
import com.java1234.entity.business.UserInfo;
import com.java1234.utils.SystemTalk;

public class BaseController {
	public void sendMessage(Object service, Object data, HttpServletResponse response) {
    	System.out.println("\n " + "\n");
		System.out.println("basecontroller.sendMessage【" + data + "】");
		SystemTalk.postForm(service.toString(), SystemTalk.PARAM, data.toString());
		out(response, "执行成功！");
		return;
	}
	
	public void selectSend(Object obj) {
		try {
			SystemTalk.postForm(obj.toString(), SystemTalk.PARAM, "");
		} catch (Exception e) {
			System.out.println("连接失败！！");
		}
		return;
	}
	
	public void out(HttpServletResponse response, Object obj){
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", obj);
			out.print(jsonObject);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
	/**
	 * 资源转换
	 * @param resources
	 * @return
	 */
	public JSONArray converJson(List<PResource> resources) {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray smallarray = new JSONArray();
		JSONObject small = new JSONObject();
		for (PResource pResource : resources) {
			smallarray = new JSONArray();
			if(pResource.getPid() == 0){
				for (PResource pre : resources) {
					//取出一级菜单下的子菜单
					if(pre.getPid() != 0 && pre.getPid() == pResource.getId()){
						small = new JSONObject();
						String url = pre.getUrl().replace("/user/", "");
						small.put("url", url);
						small.put("text", pre.getDescription());
						smallarray.add(small);
					}
				}
			}
			//将子菜单加到一级菜单下
			if(pResource.getPid() == 0 && smallarray.size() > 0){
				obj = new JSONObject();
				obj.put("isexpand", false);
				obj.put("text", pResource.getDescription());
				obj.put("children", smallarray);
				array.add(obj);
			}else if(pResource.getPid() == 0){
				obj = new JSONObject();
				obj.put("url", pResource.getUrl());
				obj.put("text", pResource.getDescription());
				array.add(obj);
			}
		}
		return array;
	}
	
	/**
	 * 提取电话号码
	 * @param item
	 * @return
	 */
	public List<String> converJsonArray(JSONArray item){
		List<String> tempList = new ArrayList<String>();
		for(int index = 0; index < item.size(); index ++){
			String tempStr = item.getString(index);
			if(StringUtils.isNotBlank(tempStr) && !StringUtils.isNumericSpace(tempStr)){
				JSONObject tempObj = JSON.parseObject(tempStr);
				if(tempObj != null){
					String temp = tempObj.getString("loginName");
					if(StringUtils.isNotBlank(temp)){
						tempList.add(temp);
					}
				}
			}else if(StringUtils.isNotBlank(tempStr)){
				if(StringUtils.isNotBlank(tempStr)){
					tempList.add(tempStr);
				}
			}
		}
		return tempList;
	}
	
	public JSONObject retParam(Object param, int size){
		JSONObject map = new JSONObject();
		map.put("Rows", param);
		map.put("Total", size);
		return map;
	}
	
	public void isLoginNameOrStatus(HttpServletRequest request,
			HttpServletResponse response, Object userInfo) {
		String param = request.getParameter("param");
		if(StringUtils.isNotBlank(param) && userInfo != null){
			JSONObject json = JSON.parseObject(param);
			JSONObject temp = new JSONObject();
			JSONArray tempArr = new JSONArray();
			String loginName = json.getString("loginName");
			String status = json.getString("rsrvStr2");
			JSONArray arr = JSONArray.parseArray(userInfo.toString());//获取所有参数
			if(StringUtils.isNotBlank(loginName) && arr != null){
				if(StringUtils.isNotBlank(status)){//如果状态不为空两个条件一起判断
					for(int i =0; i < arr.size(); i++){
						temp = JSON.parseObject(arr.get(i).toString());
						if(loginName.equals(temp.getString("loginName")) 
								&& status.equals(temp.getString("rsrvStr2"))){
							tempArr.add(temp);
						}
					}
					out(response, retParam(tempArr, tempArr.size()));
				}else{
					for(int i =0; i < arr.size(); i++){
						temp = JSON.parseObject(arr.get(i).toString());
						if(loginName.equals(temp.getString("loginName"))){
							tempArr.add(temp);
						}
					}
					out(response, retParam(tempArr, tempArr.size()));
				}
			}else if(StringUtils.isNotBlank(status)){
				if(StringUtils.isNotBlank(loginName)){//如果状态不为空两个条件一起判断
					for(int i =0; i < arr.size(); i++){
						temp = JSON.parseObject(arr.get(i).toString());
						if(loginName.equals(temp.getString("loginName")) 
								&& status.equals(temp.getString("rsrvStr2"))){
							tempArr.add(temp);
						}
					}
					out(response, retParam(tempArr, tempArr.size()));
				}else{//如果用户名为空则只判断状态
					for(int i =0; i < arr.size(); i++){
						temp = JSON.parseObject(arr.get(i).toString());
						if(status.equals(temp.getString("rsrvStr2"))){
							tempArr.add(temp);
						}
					}
					out(response, retParam(tempArr, tempArr.size()));
				}
			}else{
				out(response, retParam(arr, arr.size()));
			}
		}
	}
	
	public List<String> getUserPhos(List<UserInfo> infos){
		List<String> phos = new ArrayList<String>();
		if(infos != null && infos.size() > 0){
			for (UserInfo userInfo : infos) {
				phos.add(userInfo.getLoginName());
			}
		}
		return phos;
	}
}
