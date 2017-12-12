package com.java1234.controller.error;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("error")
public class ErrorlogController {
	@RequestMapping(value="/errorlog")
   public void errorlogs(@RequestParam("resId") String resId,@RequestParam("orderId") String orderId,@RequestParam("tempDate") String time,HttpServletResponse resbone) throws Exception{
       StringBuffer string = new StringBuffer();
       resbone.setCharacterEncoding("utf-8");
//       String patc = "/AdminManage/WEB-INF/classes/com/java1234/controller/error/TEST.sh";
       String patc = "/datadisk/software/tomcat6_jenkins/webapps/AppFS/download/shelllog.sh";
       File file = new File(patc);
       if(!file.exists()) {
     	  resbone.getWriter().write("文件不存在");
     	  return;
       }
       resId = Unitlt(resId);
       orderId = Unitlt(orderId);
       time = Unitlt(time);
       /**
        * 运行脚本
        */
       String[] command = new String[]{"/bin/sh", patc, resId, orderId, time + " shell.sh"};
       Process pr = Runtime.getRuntime().exec(command);
       InputStreamReader reader = new InputStreamReader(pr.getInputStream());
       BufferedReader readder = new BufferedReader(reader);
       if(readder.readLine()==null){
	       resbone.getWriter().write("Not error Log");
	       return;
       }
       String line = null;
       while (null!=(line=readder.readLine())) {
           string.append(line);
       }
       resbone.getWriter().write(string.toString());
   }

	private String Unitlt(String s) {
		s = s.trim();
		if (s == null | s.length() == 0) {
			s = "";
		} else {
			s = " " + s;
		}

		return s;
	}
}