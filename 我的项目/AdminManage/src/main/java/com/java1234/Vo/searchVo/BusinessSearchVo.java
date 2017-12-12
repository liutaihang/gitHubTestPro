package com.java1234.Vo.searchVo;

import com.java1234.Vo.PageBean;

public class BusinessSearchVo extends PageBean{
	
	private String businessStatus;
	private String creatPho;
	private String businessType;
	private String refId;
	private String acceptDateStart;
	private String acceptDateEnd;
	//-U pjj 11/16 增加orderId查询
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	public String getCreatPho() {
		return creatPho;
	}
	public void setCreatPho(String creatPho) {
		this.creatPho = creatPho;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getAcceptDateStart() {
		return acceptDateStart;
	}
	public void setAcceptDateStart(String acceptDateStart) {
		this.acceptDateStart = acceptDateStart;
	}
	public String getAcceptDateEnd() {
		return acceptDateEnd;
	}
	public void setAcceptDateEnd(String acceptDateEnd) {
		this.acceptDateEnd = acceptDateEnd;
	}
	
	
}
