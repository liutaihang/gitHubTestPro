package com.java1234.Vo;

import com.java1234.entity.auth.User;


public class UserVo {
	private Integer id;
	private String userName;//账号
	private String password;
	private String nickName;
	private String role;
	private boolean islogin;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	public UserVo(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.nickName = user.getNickName();
	}
	
	public UserVo(String userName, String password,
			boolean islogin) {
		super();
		this.userName = userName;
		this.password = password;
		this.islogin = islogin;
	}
	public UserVo() {
		super();
	}
	
	
}
