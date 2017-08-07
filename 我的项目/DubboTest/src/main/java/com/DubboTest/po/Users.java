package com.DubboTest.po;

public class Users {

	private String name;
	private Integer age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Users(String name, Integer age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public Users() {
		super();
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
	
}
