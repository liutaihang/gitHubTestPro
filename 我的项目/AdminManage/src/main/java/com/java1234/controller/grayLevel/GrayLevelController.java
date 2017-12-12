package com.java1234.controller.grayLevel;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java1234.controller.BaseController;
import com.java1234.utils.PropertiesUtil;

@Controller
@RequestMapping("file")
public class GrayLevelController extends BaseController{
	/**
	 * @param file
	 * @param request
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,HttpServletResponse response){
//		String serverPath = request.getSession().getServletContext().getRealPath("upload");
		String serverPath = request.getSession().getServletContext().
				getRealPath(PropertiesUtil.getValue(PropertiesUtil.APKUPLOAD_));
		System.out.println("路径：" + serverPath);
    	System.out.println("\n ");
        if(file.isEmpty()){   
            System.out.println("文件未上传");   
            request.setAttribute("errorMsg","文件为空，请选择文件！");
            return "index";
        }else{   
            System.out.println("文件长度: " + file.getSize());   
            System.out.println("文件类型: " + file.getContentType());   
            System.out.println("文件名称: " + file.getName());   
            System.out.println("文件原名: " + file.getOriginalFilename());  
            System.out.println("文件上传路径： " + serverPath);
            System.out.println("========================================"); 
        	System.out.println("\n " + "\n");
        }
        File file2 = new File(serverPath, file.getOriginalFilename());
        try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), file2);
		} catch (IOException e) {
			e.printStackTrace();
		}
        out(response, "返回参数");
        return "success";
	}
	
	public void addGray(){
		
	}
	
	public void delGray(){
		
	}
	
	public void updateVersion(){
		
	}
}
