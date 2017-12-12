package com.java1234.Vo.searchVo;

import com.java1234.Vo.PageBean;

/**
 * 
 *
 */
public class AutoPaySearchVo extends PageBean{
	
	private String resName;//代办商名称
	private String resId;	//	代办商ID
	private String createTimeStart;//设置日期:起始
	private String createTimeEnd;
	private String phoneNo;//注册手机号
	private String bankNo;//银行卡号
	
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	
}
