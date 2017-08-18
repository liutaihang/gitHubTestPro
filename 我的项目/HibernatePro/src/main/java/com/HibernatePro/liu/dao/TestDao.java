package com.HibernatePro.liu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.HibernatePro.liu.po.Person;

@Repository
public class TestDao extends DaoH<Person>{

	public static void main(String[] args) {
		
//		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		Person person = new Person(1, 21, "sc");
//		session.save(person);
//		person.setId(1);
//		session.delete(person);
//		
//		System.out.println(person);
//		t.commit();
//		session.close();
//		factory.close();
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		TestDao da = (TestDao) context.getBean("testDao");
		System.out.println(da.save(new Person(1, 21, "sc")));
//		SessionFactory f = (SessionFactory)context.getBean("sessionfactory");
//		Session s = f.openSession();
//		s.beginTransaction();
		System.err.println(context);
		
	}
	
	
	public Person save(Person person){
		return super.save(person);
	}
}
