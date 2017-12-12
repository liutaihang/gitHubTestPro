package com.java1234.controller.Sys;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.java1234.controller.BaseController;
import com.java1234.entity.business.UserInfo;
import com.java1234.service.businessService.UserInfoService;
import com.java1234.utils.EhcacheUtils;
import com.java1234.utils.SystemTalk;

@RestController
@RequestMapping("sys")
public class SysController extends BaseController{
	
	@Resource
	private CacheManager cacheManager;
	
	@Resource
	private UserInfoService UserService;
	
	@RequestMapping(value = "/Talk", method = RequestMethod.POST)
	public void sysWeb(HttpServletRequest request,HttpServletResponse response){
		EhcacheUtils.setManager(cacheManager);
		Object obj = request.getParameter("select");
		Object obj1 = request.getParameter("param");
		String service = obj.toString();
		JSONObject jsonObj = null;
		JSONArray arr = new JSONArray();
		JSONArray addOther = new JSONArray();
		//删除,添加,更新的数据转换
		if(SystemTalk.DELETE.equals(service) || SystemTalk.DELGRAY.endsWith(service)){
			String ids = request.getParameter("param");
			arr = JSON.parseArray(ids);
		}else if(SystemTalk.INSERT.equals(service) || SystemTalk.UPDATE.equals(service)){
			JSONArray white = JSON.parseArray(obj1.toString());
			addOther = white;
//			for(int i =0; i < white.size(); i++){
//				JSONObject temp = JSON.parseObject(white.get(i).toString());
//				jsonObj = new JSONObject();
//				jsonObj.put("loginName", temp.getString("loginName"));
//				jsonObj.put("userId", temp.getString("userId"));
//				jsonObj.put("isHide", temp.getString("rsrvStr2"));
//				addOther.add(jsonObj);
//			}
		}else if(SystemTalk.INGRAY.endsWith(service)){
			if(obj1 != null){
				addOther = JSON.parseArray(obj1.toString());
			}
		}else{
			if(obj1 != null){
				jsonObj = JSON.parseObject(obj1.toString());
			}
		}
		
		if(StringUtils.isNotBlank(service) && 
				(addOther.size() > 0 || arr.size() > 0 || jsonObj != null)){
			//白名单
			if(SystemTalk.INSERT.equals(service)){
				sendMessage(obj, addOther, response);
			}
			if(SystemTalk.DELETE.equals(service)){
				sendMessage(obj, arr, response);
			}
			if(SystemTalk.UPDATE.equals(service)){
				sendMessage(service, addOther.get(0), response);
			}
			//灰度升级
			if(SystemTalk.INGRAY.equals(service)){
				getPhos(response, addOther);
				sendMessage(service, addOther, response);
			}
			if(SystemTalk.DELGRAY.equals(service)){
				sendMessage(service, arr, response);
			}
			if(SystemTalk.UPDATEVERSION.equals(service)){
				sendMessage(service, jsonObj, response);
			}
		}
		//查询
		if(StringUtils.isNotBlank(service)){
			if(SystemTalk.SELECT.equals(service)){
				selectSend(obj);
			}
			if(SystemTalk.SELECTTRADEPAY.equals(service)){
				sendMessage(service, jsonObj, response);
			}
			if(SystemTalk.SELGRAY.equals(service)){
				selectSend(obj);
			}
			if(SystemTalk.SELECTGRAYVERSION.equals(service)){
				selectSend(obj);
			}
			if(SystemTalk.SELECTUSER.equals(service)){
				selectSend(obj);
			}
		}
	}

	/**
	 * 取出未注册的用户
	 * @param response
	 * @param addOther
	 */
	private void getPhos(HttpServletResponse response, JSONArray addOther) {
		EhcacheUtils.setManager(cacheManager);
		//Object white_list = EhcacheUtils.get("myCache", "user_info");//获取白名单的用户
		List<UserInfo> userInfos = UserService.userInfoSearch(null);//逻辑改变，所有用户都可以为灰度用户
		List<String> userPho = new ArrayList<String>();//数据库查出来的数据
		List<String> tempPho = new ArrayList<String>();//需要添加的灰度用户数据
		List<String> tempPho1 = new ArrayList<String>();//数据交换用
		if(userInfos != null && addOther != null){
			//JSONArray array = JSON.parseArray(white_list.toString());
			tempPho = converJsonArray(addOther);
			tempPho1 = converJsonArray(addOther);
			userPho = getUserPhos(userInfos);
			tempPho.retainAll(userPho);//取交集,白名单数据库和添加数据中都存在的电话
			tempPho1.removeAll(tempPho);//取出数据库不存在的电话号码
			//将数据库中不存在的电话号码传到页面
			if(tempPho1.size() > 0){
				EhcacheUtils.put("myCache", "white_none", tempPho1);
//				out(response, tempPho1);
				return;
			}
		}
	}
	
	@RequestMapping(value = "/web", method = RequestMethod.POST)
	public void sysTalk(HttpServletRequest request,HttpServletResponse response){
		EhcacheUtils.setManager(cacheManager);
		String str = request.getParameter("result");
		ServletContext context = request.getSession().getServletContext();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONArray array = null;
		if(StringUtils.isNotBlank(str)){
			array =  JSONArray.parseArray(str);
			if(array.size() < 1){
				context.removeAttribute("userInfo");
			}
		}
		JSONObject jo = null;
		try {
			if(array != null && null != array.getString(0) 
					&& StringUtils.isNotBlank(array.getString(0).toString()) 
					&& array.getString(0).toString().length() == 11){//白名单没有该电话号码的数据
				context.setAttribute("notPho", array);
			}
			jo = JSON.parseObject(array.getString(0));
		} catch (Exception e) {
			//返回可能是修改条数或删除条数所以会转换失败
			System.err.println("返回可能是修改条数或删除条数所以会数据错误 json转换失败！");
		}
 		if(jo != null){
			String version = jo.getString("lastVersion");//判断是否为版本管理信息
			String lastLoginTime = jo.getString("lastLoginTime");//判断是否为用户信息
			String payDate = jo.getString("payDate");//判断是否为用户交易信息
			String whiteList = jo.getString("rsrvStr2");//判断是否为白名单用户
			JSONArray newArr = new JSONArray();
			if( StringUtils.isNotBlank(version)){//存储灰度版本信息
				converVersion(array, newArr);
				context.setAttribute("grayVersion", newArr);
			}else if(StringUtils.isNotBlank(payDate)){//用户交易信息
				EhcacheUtils.put("myCache", "tradeInfo", array);
				context.setAttribute("tradeInfo", array);
			}else if(StringUtils.isNotBlank(lastLoginTime)){//用户信息
				context.setAttribute("userInfo", array);
				EhcacheUtils.put("myCache", "user_info", array);
			}else if(StringUtils.isNotBlank(lastLoginTime) 
					&& StringUtils.isNotBlank(whiteList) 
					&& "1".equals(whiteList)){//其他信息
				EhcacheUtils.put("myCache", "white_list", array);//将白名单信息存入缓存中
			}else{
				context.setAttribute("resultData", array);
			}
		}else{
			context.setAttribute("tradeInfo", "notData");
		}
		
	}

	private void converVersion(JSONArray array, JSONArray newArr) {
		for(int i=0;i<array.size();i++){
			JSONObject newJson = new JSONObject();
			JSONObject json = JSON.parseObject(array.get(i).toString());
//			String isOnline = json.getString("isOnline");//是否审核通过0-还未通过appStore审核；1-已经通过appStore审核，并上线
//			if("1".equals(isOnline)){
//				newJson.put("sh", "已经通过审核，并上线");
//			}else if("0".equals(isOnline)){
//				newJson.put("sh", "已审核市场");
//			}
			
			//审核描述
			String verifyDescribe = json.getString("rsrvStr1");
			newJson.put("sh", verifyDescribe);
			String sfqzgx = json.getString("isUpdate");//是否强制更新（1,强制更新.0,不强制更新.2,灰度更新）
			String dqbb = json.getString("lastVersion");//最新版本
			String khdlx = json.getString("teamBrand");//ios or android
			String bbms = json.getString("lastVersionDescribe");//版本描述
			String xzdz = json.getString("downlodUrl");//下载地址
			newJson.put("sfqzgx", sfqzgx);
			newJson.put("dqbb", dqbb);
			newJson.put("khdlx", khdlx);
			newJson.put("bbms", bbms);
			newJson.put("xzdz", xzdz);
			newArr.add(newJson);
		}
	}
	
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public void getResource(HttpServletRequest request,HttpServletResponse response){
		EhcacheUtils.setManager(cacheManager);
		ServletContext context = request.getSession().getServletContext();
		String get = request.getParameter("trade");
		String user = request.getParameter("user");
		String gray = request.getParameter("gray");
		String whiteList = request.getParameter("whiteList");
		String userSearsh = request.getParameter("userSearch");
		
		//取数据
		Object trade = context.getAttribute("tradeInfo");
		if("get".equals(get) && trade != null){//交易信息
			if("notData".equals(trade.toString())){
				out(response, "没有数据");
				return;
			}
			JSONArray arr = new JSONArray();
			arr = JSONArray.parseArray(trade.toString());
			arr = converMoney(arr);
			out(response, retParam(arr, arr.size()));
			context.removeAttribute("tradeInfo");
		}
		Object userInfo = context.getAttribute("userInfo");
		if("getAll".equals(user) && userInfo != null){//用户信息
			JSONArray arr = JSONArray.parseArray(userInfo.toString());
			out(response, retParam(arr, arr.size()));
//			context.removeAttribute("userInfo");
		}
		Object obj = context.getAttribute("resultData");
		Object white_none = EhcacheUtils.get("myCache", "white_none");
		JSONArray arr = new JSONArray();
		if(obj != null && "get".equals(gray)){//灰度信息
			arr = JSONArray.parseArray(obj.toString());
			JSONArray tempArr = new JSONArray();
			if(white_none != null && StringUtils.isNotBlank(white_none.toString())){
				JSONArray white_none_arr = JSONArray.parseArray(white_none.toString());
				for(int i = 0; i< white_none_arr.size(); i++){
					JSONObject tempObj = new JSONObject();
					tempObj.put("userName", white_none_arr.getString(i));
					tempObj.put("userId", "用户未注册！");
					tempArr.add(tempObj);
				}
			}
			arr.addAll(tempArr);
			out(response, retParam(arr, arr.size()));
			EhcacheUtils.remove("myCache", "white_none");
			context.removeAttribute("resultData");
		}
		Object whiteData = context.getAttribute("notPho");//获取导入白名单用户时不存在的用户
		Object white_list = EhcacheUtils.get("myCache", "user_info");
		if("whitePho".equals(whiteList) && white_list != null){
			JSONArray whiteAll = new JSONArray();//存入所有的用户数据（包括导入白名单用户时不存在的用户）
			if(whiteData != null){
				JSONArray notPhos = JSON.parseArray(whiteData.toString());//得到用户表没有的用户电话号码
				for(int i = 0; i < notPhos.size(); i++){
					JSONObject notPho = new JSONObject();
					String pho = notPhos.getString(i);
					notPho.put("loginName", pho);
					notPho.put("userRealName", "没有该用户");
					whiteAll.add(notPho);
				}
			}
			whiteAll. addAll(JSON.parseArray(white_list.toString()));
			out(response, retParam(whiteAll, whiteAll.size()));
			context.removeAttribute("notPho");
		}
		if("SEARCH".equals(userSearsh)){
			isLoginNameOrStatus(request, response, userInfo);
		}
	}

	private JSONArray converMoney(JSONArray arr) {
		JSONArray newMoney = new JSONArray();
		for(int index = 0; index < arr.size(); index ++){
			JSONObject obj = null;
			if(StringUtils.isNotBlank(arr.getString(index))){
				obj = new JSONObject();
				obj = JSON.parseObject(arr.getString(index));
				String money = obj.getString("txnAmt");
				if(StringUtils.isNotBlank(money) && StringUtils.isNumericSpace(money)){
					int yuan = Integer.parseInt(money)/100;
					obj.put("txnAmt", yuan + "");
				}
			}
				newMoney.add(obj);
		}
		return newMoney;
	}
	
	@RequestMapping(value = "/version", method = RequestMethod.POST)
	public void getGrayVersion(HttpServletRequest request,HttpServletResponse response){
		ServletContext context = request.getSession().getServletContext();
		Object obj = context.getAttribute("grayVersion");
		if(obj != null){
			JSONArray arr = JSONArray.parseArray(obj.toString());
			out(response, retParam(arr, arr.size()));
		}
	}
}
