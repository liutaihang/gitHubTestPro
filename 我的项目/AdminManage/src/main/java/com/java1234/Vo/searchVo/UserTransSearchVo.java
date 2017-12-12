package com.java1234.Vo.searchVo;

import com.java1234.Vo.PageBean;


/**
 * 用户交易查询信息
 */
public class UserTransSearchVo extends PageBean{
	 
	 /**交易状态*/
	 private String tradeStatus;
	 /**银行卡号*/
	 private String bankNo;
	 /**用户交易Id*/
	 private String orderId;
	 /**业务类型*/
	 private String serv;
	 /**交易金额*/
	 private Integer txnAmtStart;
	 /**交易金额*/
	 private Integer txnAmtEnd;
	 /**代办商编号*/
	 private String resId;
	 /**代扣方式*/
	 private String payMode;
	 /**系统跟踪号*/
	 private String refId;
	 /**受理时间*/
	 private String acceptDateStart;
	 /**受理时间*/
	 private String acceptDateEnd;
	 //该字段用来判断风控还是空充
	 private String bType;
	 
	public String getbType() {
		return bType;
	}
	public void setbType(String bType) {
		this.bType = bType;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getTxnAmtStart() {
		return txnAmtStart;
	}
	public void setTxnAmtStart(Integer txnAmtStart) {
		this.txnAmtStart = txnAmtStart;
	}
	public Integer getTxnAmtEnd() {
		return txnAmtEnd;
	}
	public void setTxnAmtEnd(Integer txnAmtEnd) {
		this.txnAmtEnd = txnAmtEnd;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
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
	public String getServ() {
		return serv;
	}
	public void setServ(String serv) {
		this.serv = serv;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	 
}
