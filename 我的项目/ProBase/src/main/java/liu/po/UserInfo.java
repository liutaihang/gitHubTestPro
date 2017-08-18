package liu.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo extends BasePo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5711221365567470136L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "account")
	private String acount;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "age")
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
