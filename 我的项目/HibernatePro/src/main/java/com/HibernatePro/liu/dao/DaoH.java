package com.HibernatePro.liu.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoH <T>{

	@Resource
	private SessionFactory factory;
	
	private Session getsession(){
		return factory.getCurrentSession();
	}
	
	public T save(T t){
		getsession().save(t);
		return t;
	}
}
