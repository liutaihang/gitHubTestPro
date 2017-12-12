package com.java1234.Vo.searchVo;

import org.apache.commons.lang3.StringUtils;

import com.java1234.Vo.PageBean;
import com.java1234.utils.DesSensitiveData;

public class BindCardSearchVo extends PageBean{
	
	private String userId;//用户编号
	private String userRealName;//真实姓名
	private String cardId;//身份证号
	private String bankNo;
	private String bankName;
	private String cardState;//状态
	private String phoneNo;
	private String bindTimeStart;//绑定时间
	private String bindTimeEnd;//绑定时间
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		try {
			if(StringUtils.isNotEmpty(phoneNo)){
				this.phoneNo = DesSensitiveData.enSensitiveData(phoneNo);
			}else{
				this.phoneNo = phoneNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		try {
			if(StringUtils.isNotEmpty(bankNo)){
				this.bankNo = DesSensitiveData.enSensitiveData(bankNo);
			}else{
				this.bankNo = bankNo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardState() {
		return cardState;
	}
	public void setCardState(String cardState) {
		this.cardState = cardState;
	}
	public String getBindTimeStart() {
		return bindTimeStart;
	}
	public void setBindTimeStart(String bindTimeStart) {
		this.bindTimeStart = bindTimeStart;
	}
	public String getBindTimeEnd() {
		return bindTimeEnd;
	}
	public void setBindTimeEnd(String bindTimeEnd) {
		this.bindTimeEnd = bindTimeEnd;
	}
	
	
}
