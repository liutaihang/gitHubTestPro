package com.java1234.Vo.searchVo;

import com.java1234.Vo.PageBean;


/**
 * 用户信息查询数据接收类
 *
 */
public class UserSearchVo extends PageBean{

	private String loginName;//注册名
	private String phoneNo;
	private String startTime;
	private String endTime;
	private String realName;
	private String cardId;//身份证号
	private String enIdNum;//加密后的身份证号
	private String userId;//用户编号
	
	
	public String getEnIdNum() {
		return enIdNum;
	}
	public void setEnIdNum(String enIdNum) {
		this.enIdNum = enIdNum;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
