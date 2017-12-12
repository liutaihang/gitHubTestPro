package com.java1234.controller.business;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java1234.Vo.searchVo.UserTransSearchVo;
import com.java1234.controller.BaseController;
import com.java1234.service.businessService.AutoPayService;
import com.java1234.service.businessService.BindCardLogService;
import com.java1234.service.businessService.BindCardService;
import com.java1234.service.businessService.BusinessService;
import com.java1234.service.businessService.UserInfoService;
import com.java1234.service.businessService.UserTransService;

@RequestMapping("total")
@Controller
public class TotalController extends BaseController{

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
	
	@RequestMapping(value = "/userTrans", method = RequestMethod.GET)
	public void getUserTransTotal(HttpServletResponse response,UserTransSearchVo searchVo){
		out(response, transService.getTotal(searchVo));
	}
}
