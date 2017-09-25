package com.HibernatePro.liu.po;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5241607584534827471L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "sex")
	private Integer sex;

	@Column(name = "age")
	private Integer age;

	@Column(name = "province")
	private String province;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Person(Integer sex, Integer age, String province) {
		super();
		this.sex = sex;
		this.age = age;
		this.province = province;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [Id=" + Id + ", sex=" + sex + ", age=" + age + ", province=" + province + "]";
	}

}
