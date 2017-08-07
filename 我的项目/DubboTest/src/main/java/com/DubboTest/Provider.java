package com.DubboTest;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Dubbo_service.xml");
		
		context.start();
		
		System.in.read();
	}
}
