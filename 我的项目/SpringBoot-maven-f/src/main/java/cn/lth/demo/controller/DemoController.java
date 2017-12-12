package cn.lth.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lth.demo.bean.DemoBean;
import cn.lth.demo.service.DemoService;


@Controller
@RequestMapping("templates")
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/demo")
	public String addDemo(DemoBean bean, HttpServletRequest request){
//		int i = demoService.addDemo(bean);
//		bean.setId(i);
		request.setAttribute("bean", bean);
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("path", request.getContextPath());
//		new DemoBean().setName("demoName");
		return "helloFtl";
	}
	
	@RequestMapping("/hello")
	public String hello(Map<String, Object> map){
		map.put("user", "userName");
		return "hello";
	}
}
