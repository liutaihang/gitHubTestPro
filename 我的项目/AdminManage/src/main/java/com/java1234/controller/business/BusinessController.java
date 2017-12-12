package com.java1234.controller.business;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.java1234.Vo.ExportPageVo;
import com.java1234.Vo.searchVo.AutoPaySearchVo;
import com.java1234.Vo.searchVo.BindCardLogSearchVo;
import com.java1234.Vo.searchVo.BindCardSearchVo;
import com.java1234.Vo.searchVo.BusinessSearchVo;
import com.java1234.Vo.searchVo.UserSearchVo;
import com.java1234.Vo.searchVo.UserTransSearchVo;
import com.java1234.controller.BaseController;
import com.java1234.entity.business.AutoPayUserInfo;
import com.java1234.entity.business.BindCardInfo;
import com.java1234.entity.business.BindCardLogInfo;
import com.java1234.entity.business.BusinessInfo;
import com.java1234.entity.business.UserInfo;
import com.java1234.entity.business.UserTransInfo;
import com.java1234.excel.ExcelUtils;
import com.java1234.service.businessService.AutoPayService;
import com.java1234.service.businessService.BindCardLogService;
import com.java1234.service.businessService.BindCardService;
import com.java1234.service.businessService.BusinessService;
import com.java1234.service.businessService.UserInfoService;
import com.java1234.service.businessService.UserTransService;
import com.java1234.utils.DesSensitiveData;
import com.java1234.utils.MathMoney;

@Controller
@RequestMapping("/bus")
public class BusinessController extends BaseController{
	
	@Resource
	private UserInfoService service;
	@Resource
	private UserTransService transService;
	@Resource
	private BindCardLogService bindCardLogService;
	@Resource
	private BindCardService bindCardService;
	@Resource
	private AutoPayService autoPayService;
	@Resource
	private BusinessService businessService;
	
	/**
	 * -U pjj 11/14 新增加密身份证后or联表查询
	 * 归集通用户信息查询
	 * @param searchVo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public void userSearch(UserSearchVo searchVo, HttpServletRequest request, 
			HttpServletResponse response,@RequestParam(value = "pageNum", required = false)Integer pageNum){
		System.out.println(request.getParameter("loginName"));
		searchVo.setPage(pageNum);
		if (searchVo != null && searchVo.getCardId() != null && searchVo.getCardId() != "") {
			try {
				searchVo.setEnIdNum(DesSensitiveData.enSensitiveData(searchVo.getCardId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<UserInfo> userInfos = service.userInfoSearch(searchVo);
		List<UserInfo> newUserInfos = new ArrayList<UserInfo>();
		for (UserInfo userInfo : userInfos) {
			try {
				if (userInfo != null && StringUtils.isNotBlank(userInfo.getIdNum())
						&& !userInfo.getIdNum().substring(0, 12).matches("[0-9]*")) {
					userInfo.setIdNum(DesSensitiveData.deSensitiveData(userInfo.getIdNum()));
				}
			} catch (Exception e) {
				continue;
			}
			newUserInfos.add(userInfo);
		}
		Integer total = service.getUserInfoTotal(searchVo);
		out(response, retParam(newUserInfos, total));
	}
	
	/**
	 * 用户交易信息查询
	 * @param searchVo
	 * @param response
	 */
	@RequestMapping(value = "/userTransSearch")
	public void userTransSearch(UserTransSearchVo searchVo, HttpServletResponse response, 
			@RequestParam(value = "pageNum", required = false)Integer pageNum){
		searchVo.setPage(pageNum);
		List<UserTransInfo> infos =  transService.userTransSearch(searchVo);
		Integer total = transService.getTotal(searchVo);
		out(response, retParam(infos, total)); 
	}
	
	/**
	 * 自动支付用户信息查询
	 * @param response
	 * @param searchVo
	 */
	@RequestMapping(value = "/AutoPaySearch", method = RequestMethod.POST)
	public void AutoPaySearch(HttpServletResponse response, AutoPaySearchVo searchVo){
		List<AutoPayUserInfo> userInfos = autoPayService.autoPaySearch(searchVo);
		out(response, retParam(userInfos, userInfos.size()));
	}

	/**
	 * 用户绑卡信息查询
	 * @param response
	 * @param searchVo
	 * @throws Exception 
	 */
	@RequestMapping(value = "/BindCardSearch", method = RequestMethod.POST)
	public void BindCardSearch(HttpServletResponse response, BindCardSearchVo searchVo,
			@RequestParam(value = "pageNum", required = false)Integer pageNum) throws Exception{
		searchVo.setPage(pageNum);
		List<BindCardInfo> bindCardInfos = bindCardService.bindCardSearch(searchVo);
		Integer total = bindCardService.getBindCardTotal(searchVo);
		out(response, retParam(bindCardInfos, total));
	}

	/**
	 * 用户绑卡日志信息查询
	 * @param response
	 * @param searchVo
	 * @throws Exception 
	 */
	@RequestMapping(value = "/BindCardLogSearch", method = RequestMethod.POST)
	public void BindCardLogSearch(HttpServletResponse response, BindCardLogSearchVo searchVo,
			@RequestParam(value = "pageNum", required = false)Integer pageNum) throws Exception{
		searchVo.setPage(pageNum);
		List<BindCardLogInfo> bindCardLogInfos = bindCardLogService.bindCardLogSearch(searchVo);
		Integer total = bindCardLogService.getBindCardLogTotal(searchVo);
		out(response, retParam(bindCardLogInfos, total));
	}

	/**
	 * 用户业务信息查询
	 * @param response
	 * @param searchVo
	 */
	@RequestMapping(value = "/businessSearch", method = RequestMethod.POST)
	public void businessSearch(HttpServletResponse response, BusinessSearchVo searchVo,
			@RequestParam(value = "pageNum", required = false)Integer pageNum){
		searchVo.setPage(pageNum);
		List<BusinessInfo> businessInfos = businessService.businessSearch(searchVo);
		Integer total = businessService.getBusinessTotal(searchVo);
		out(response, retParam(businessInfos, total));
	}
	
	/**
	 * 导出Excel  用户交易信息
	 * @param pageVo
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exprotExcel", method = RequestMethod.POST)
	public void ExprotUserInfo(UserTransSearchVo Vo,
			HttpServletResponse response, HttpServletRequest request) throws IOException{
		ExportPageVo pageVo = new ExportPageVo();
		List<UserTransInfo> infos = transService.ExportTransInfo(pageVo, Vo);
		List<UserTransInfo> newInfos = new ArrayList<UserTransInfo>();
		for (UserTransInfo userTransInfo : infos) {
			if (userTransInfo.getTxnAmt()!=null && !"".equals(userTransInfo.getTxnAmt())) {
				if (userTransInfo.getTxnAmt().length() <= 2) {
					userTransInfo.setTxnAmt(MathMoney.div(userTransInfo.getTxnAmt(), "100", 2));
				} else userTransInfo.setTxnAmt(MathMoney.div(userTransInfo.getTxnAmt(), "100", 0));
			}
			if (userTransInfo.getServ()!=null && "gzMobileRiskControl".equals(userTransInfo.getServ())) {
				if (userTransInfo.getResId()!=null && userTransInfo.getResId().length() == 8) {
					userTransInfo.setServ("风控厅");
				} else userTransInfo.setServ("空充厅");
			} else userTransInfo.setServ("营收金");
			if (userTransInfo.getPayMode()!=null && "0".equals(userTransInfo.getPayMode())) {
				userTransInfo.setPayMode("主动代扣");
			} else userTransInfo.setPayMode("自动代扣");
			if (userTransInfo.getOrderStatus()!=null) {
				if ("Paid".equals(userTransInfo.getOrderStatus())) {
					userTransInfo.setOrderStatus("已支付");
				} else if ("Paying".equals(userTransInfo.getOrderStatus())){
					userTransInfo.setOrderStatus("支付中");
				} else if ("Closed".equals(userTransInfo.getOrderStatus())){
					userTransInfo.setOrderStatus("已关闭");
				} else if ("Fail".equals(userTransInfo.getOrderStatus())){
					userTransInfo.setOrderStatus("交易失败");
				} else if ("Created".equals(userTransInfo.getOrderStatus())){
					userTransInfo.setOrderStatus("已创建");
				}
			}
			newInfos.add(userTransInfo);
		}
		String fileName = new SimpleDateFormat("yyyy").format(new Date()) + "-UserTransInfo.xls";
		String filePath = request.getRealPath("/") + "excel/" + fileName;
		//String filePath = "http://localhost:8080/AdminManage/excel/" + fileName;
		System.out.println("下载地址" + filePath);
		ExcelUtils.export(newInfos, filePath);
		JSONObject obj = new JSONObject();
		obj.put("url", fileName);
		obj.put("msg", "导出成功！");
		out(response, obj);
	}
	
	/**
	 * 导出用户信息
	 * @param searchVo
	 * @param response
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportUser", method = RequestMethod.POST)
	public void ExportUserInfo(UserSearchVo searchVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<UserInfo> infos = new ArrayList<UserInfo>();
		infos = service.exportUserInfo(searchVo);
		String fileName = new SimpleDateFormat("yyyy").format(new Date()) + "-UserInfo.xls";
		String filePath = request.getRealPath("/") + "excel/" + fileName;
		ExcelUtils.export(infos, filePath);
		JSONObject obj = new JSONObject();
		obj.put("url", fileName);
		obj.put("msg", "导出成功！");
		out(response, obj);
	}
	
	/**
	 * 绑卡信息导出
	 * @param searchVo
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportBindCard", method = RequestMethod.POST)
	public void ExportBindCardInfo(BindCardSearchVo searchVo, HttpServletResponse response, 
			HttpServletRequest request) throws Exception{
		List<BindCardInfo> cardInfos = new ArrayList<BindCardInfo>();
		List<BindCardInfo> newCardInfos = new ArrayList<BindCardInfo>();
		cardInfos = bindCardService.exportBindCardInfo(searchVo);
		String fileName = new SimpleDateFormat("yyyy").format(new Date()) + "-BindCardInfo.xls";
		String filePath = request.getRealPath("/") + "excel/" + fileName;
		for (BindCardInfo bindCardInfo : cardInfos) {
			if(bindCardInfo.getCardState().equals("0")){
				bindCardInfo.setCardState("解绑");
			}else if(bindCardInfo.getCardState().equals("1")){
				bindCardInfo.setCardState("待生效");
			}else if(bindCardInfo.getCardState().equals("2")){
				bindCardInfo.setCardState("正常");
			}else if(bindCardInfo.getCardState().equals("3")){
				bindCardInfo.setCardState("冻结");
			}
			newCardInfos.add(bindCardInfo);
		}
		ExcelUtils.export(newCardInfos, filePath);
		JSONObject obj = new JSONObject();
		obj.put("url", fileName);
		obj.put("msg", "导出成功！");
		out(response, obj);
	}
	
	/**
	 * 绑卡日志信息导出
	 * @param searchVo
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportBindCardLog", method = RequestMethod.POST)
	public void ExportBindCardLog(BindCardLogSearchVo searchVo, HttpServletResponse response, 
			HttpServletRequest request) throws Exception{
		List<BindCardLogInfo> infos = new ArrayList<BindCardLogInfo>();
		List<BindCardLogInfo> newInfos = new ArrayList<BindCardLogInfo>();
		infos = bindCardLogService.exportBindCardLog(searchVo);
		String fileName = new SimpleDateFormat("yyyy").format(new Date()) + "-BindCardLogInfo.xls";
		String filePath = request.getRealPath("/") + "excel/" + fileName;
		for (BindCardLogInfo bindCardLogInfo : infos) {
			if(bindCardLogInfo.getStatus().equals("0")){
				bindCardLogInfo.setStatus("创建成功");
			}else if(bindCardLogInfo.getStatus().equals("3")){
				bindCardLogInfo.setStatus("交易成功");
			}else if(bindCardLogInfo.getStatus().equals("5")){
				bindCardLogInfo.setStatus("交易失败");
			}
			newInfos.add(bindCardLogInfo);
		}
		ExcelUtils.export(newInfos, filePath);
		JSONObject obj = new JSONObject();
		obj.put("url", fileName);
		obj.put("msg", "导出成功！");
		out(response, obj);
	}
	
	/**
	 * 业务信息导出
	 * @param searchVo
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/exportBusiness", method = RequestMethod.POST)
	public void ExportBusiness(BusinessSearchVo searchVo, HttpServletResponse response, 
			HttpServletRequest request){
		List<BusinessInfo> infos = new ArrayList<BusinessInfo>();
		List<BusinessInfo> newInfos = new ArrayList<BusinessInfo>();
		infos = businessService.exportBusiness(searchVo);
		String fileName = new SimpleDateFormat("yyyy").format(new Date()) + "-BusinessInfo.xls";
		String filePath = request.getRealPath("/") + "excel/" + fileName;
		for (BusinessInfo businessInfo : infos) {
			if(StringUtils.isNotBlank(businessInfo.getBusinessStatus())){
				if(businessInfo.getBusinessStatus().equals("0")){
					businessInfo.setBusinessStatus("创建状态");
				}else if(businessInfo.getBusinessStatus().equals("2")){
					businessInfo.setBusinessStatus("交易处理中");
				}else if(businessInfo.getBusinessStatus().equals("3")){
					businessInfo.setBusinessStatus("交易成功");
				}else if(businessInfo.getBusinessStatus().equals("4")){
					businessInfo.setBusinessStatus("交易失败");
				}
			}
			
			if(StringUtils.isNotBlank(businessInfo.getBusinessType())){
				if(businessInfo.getBusinessType().equals("payOrderA")){
					businessInfo.setBusinessType("充值服务");
				}else if(businessInfo.getBusinessType().equals("authCard")){
					businessInfo.setBusinessType("绑定银行卡");
				}else if(businessInfo.getBusinessType().equals("realNameAuth")){
					businessInfo.setBusinessType("实名认证");
				}else if(businessInfo.getBusinessType().equals("saveAcc")){
					businessInfo.setBusinessType("保存银行卡");
				}else if(businessInfo.getBusinessType().equals("delCard")){
					businessInfo.setBusinessType("解绑银行卡");
				}else if(businessInfo.getBusinessType().equals("shebiePay")){
					businessInfo.setBusinessType("赊呗支付");
				}
			}
			newInfos.add(businessInfo);
		}
		ExcelUtils.export(newInfos, filePath);
		JSONObject obj = new JSONObject();
		obj.put("url", fileName);
		obj.put("msg", "导出成功！");
		out(response, obj);
	}
}
