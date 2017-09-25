package com.HibernatePro.liu.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MSQDao<T extends Serializable, S extends Serializable> {

	@Resource
	private SessionFactory factory;

	private Class<T> classType;

	@SuppressWarnings("unchecked")
	protected MSQDao() {
		Type superClass = this.getClass().getGenericSuperclass();
		// 获取参数泛型的类
		if (superClass instanceof ParameterizedType) {
			this.classType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
		}
	}

	public Session getsession() {
		return factory.getCurrentSession();
	}

	public T save(T t) {
		getsession().save(t);
		return t;
	}

	public T dele(T t) {
		getsession().delete(t);
		return t;
	}

	public T update(T t) {
		getsession().update(t);
		return t;
	}

	public T findByid(S s) {
		return (T) getsession().get(classType, s);
	}
}
