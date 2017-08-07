package com.DubboTest;

import java.util.ArrayList;
import java.util.List;

import com.DubboTest.po.Users;

/**
 * Hello world!
 *
 */
public class DemoServiceImpl implements DemoService{

	public String say(String content) {
		
		return "say：" + content;
	}

	public List<Users> getUser() {
		List<Users> list = new ArrayList<Users>();  
        Users u1 = new Users();  
        u1.setName("jack");  
        u1.setAge(20);  
        u1.setSex("男");  
          
        Users u2 = new Users();  
        u2.setName("tom");  
        u2.setAge(21);  
        u2.setSex("女");  
          
        Users u3 = new Users();  
        u3.setName("rose");  
        u3.setAge(19);  
        u3.setSex("女");  
          
        list.add(u1);  
        list.add(u2);  
        list.add(u3);
		return list;
	}
    
}
