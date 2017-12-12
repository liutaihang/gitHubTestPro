package com.java1234.controller.auth;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java1234.controller.BaseController;
import com.java1234.entity.auth.PResource;
import com.java1234.service.auth.ResourceService;

@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController{
	
	@Resource
	private ResourceService resourceService;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public void findResources(HttpServletResponse response){
		List<PResource> list = resourceService.findAll();
		out(response, retParam(list, list.size()));
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addResource(PResource resource, HttpServletResponse response){
		try {
			out(response, resourceService.add(resource));
		} catch (Exception e) {
			System.err.println(e);
			out(response, e);
		}
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public void delResource(@RequestParam("ids[]")Integer [] ids, HttpServletResponse response, HttpServletRequest request){
		if(ids == null || ids.length < 1){
			out(response, "资源id为空");
			return;
		}
		List<Integer> list = CollectionUtils.arrayToList(ids);
		long result = resourceService.delResource(list);
		if(result > 0){
			out(response, "删除成功！");
		}
		if(result == 0){
			out(response, "未删除！");
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateResource(PResource pResource){
		
	}
}
