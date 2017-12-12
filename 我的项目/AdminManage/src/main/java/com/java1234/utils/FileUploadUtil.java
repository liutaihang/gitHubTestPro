package com.java1234.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUploadUtil {
	private HttpServletRequest request = null;
	
	public FileUploadUtil(HttpServletRequest request) {
		this.request = request;
	}
	
	public String uploadFile(String uplodFolderName) throws IOException{
		String name = null;
		//创建�?��多部分解析器
		CommonsMultipartResolver multipartResolver = 
				new CommonsMultipartResolver(request.getSession().getServletContext());
	
		//判断是否有文件上�?即多部分请求)
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//取得上传的所有文件名
			Iterator<String> iterable = multiRequest.getFileNames();
			while(iterable.hasNext()){
				// 设置文件保存路径
				String path = request.getSession().getServletContext().getRealPath("/")
						+ uplodFolderName+"/";
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iterable.next());
				//取得上传的文件名
				String fileName = file.getOriginalFilename(); 
				if (fileName != null && !("".equals(fileName))) {
					//动�?生成文件名字
					fileName = System.currentTimeMillis()+fileName;
					//写入文件到指定路�?
					File onloadFile = new File(path + fileName);
					file.transferTo(onloadFile);
					//返回文件�?
					name = uplodFolderName+"/"+fileName;
				} 
			}
		}
		return name;
	}
}
