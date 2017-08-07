package com.DubboTest.customer;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.DubboTest.DemoService;

public class Customer {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Customer_serviceImpl.xml");
		DemoService demoService = (DemoService) context.getBean("demoService");
		System.out.println(demoService.say("消费者"));
		
		System.in.read();
	}
}
