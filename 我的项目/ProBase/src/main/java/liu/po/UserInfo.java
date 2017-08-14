package liu.po;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserInfo extends BasePo{

	private String name;
	private String acount;
	private String password;
	private String sex;
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public UserInfo(String name, String acount, String password, String sex, Integer age) {
		super();
		this.name = name;
		this.acount = acount;
		this.password = password;
		this.sex = sex;
		this.age = age;
	}
	public UserInfo() {
		super();
	}
	
}
