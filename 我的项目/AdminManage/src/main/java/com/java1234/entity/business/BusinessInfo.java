package com.java1234.entity.business;

/**
 * 业务信息
 *
 */
public class BusinessInfo {
	private String userId;
	private String createPho;//注册手机号
	private String businessType;//业务类型
	private String businessStatus;//业务状态 0 3 5
	private String respCode;//应答编号
	private String respDesc;//应答描述
	private String finishDate;//完成时间
	private String acceptDate;//受理时间
	private String refId;//系统跟踪号
	//-U pjj 11/16 增加订单号查询
	private String orderId;
	private int id;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreatePho() {
		return createPho;
	}
	public void setCreatePho(String createPho) {
		this.createPho = createPho;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	
}
