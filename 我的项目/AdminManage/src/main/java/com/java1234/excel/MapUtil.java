package com.java1234.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author PJJ 2017/11/15
 *  用来存放属性和属性描述的对应关系
 */
public class MapUtil {
	
	static Map<String, String> map = new HashMap<String, String>();
	
	static {
		//用户交易相关
		map.put("UserTransInfo", "用户交易表");
		map.put("UserTransInfo.resId", "商户编号");
		map.put("UserTransInfo.id", "id");
		map.put("UserTransInfo.resName", "商户名称");
		map.put("UserTransInfo.serv", "业务类型");
		map.put("UserTransInfo.txnAmt", "交易金额");
		map.put("UserTransInfo.bankName", "银行名称");
		map.put("UserTransInfo.cardId", "银行卡号");
		map.put("UserTransInfo.payDate", "应缴日期");
		map.put("UserTransInfo.payMode", "代扣方式");
		map.put("UserTransInfo.rsrvStr1", "订单时间");
		map.put("UserTransInfo.orderStatus", "订单状态");
		map.put("UserTransInfo.orderId", "交易流水号");
		map.put("UserTransInfo.refId", "系统跟踪号");
		map.put("UserTransInfo.respCode", "多渠道返回编码");
		map.put("UserTransInfo.payType", "支付方式");
		map.put("UserTransInfo.acceptDate", "受理时间");
		
		
		//绑卡日志相关
		map.put("BindCardLogInfo", "绑卡日志表");
		map.put("BindCardLogInfo.userId", "用户编号");
		map.put("BindCardLogInfo.userRealName", "真实姓名");
		map.put("BindCardLogInfo.cardId", "身份证号");
		map.put("BindCardLogInfo.phoneNo", "用户手机号");
		map.put("BindCardLogInfo.bankNo", "银行卡号");
		map.put("BindCardLogInfo.status", "业务状态");
		map.put("BindCardLogInfo.respCode", "多渠道应答编码");
		map.put("BindCardLogInfo.respDesc", "多渠道应答描述");
		map.put("BindCardLogInfo.acceptDate", "受理时间");
		map.put("BindCardLogInfo.refId", "系统跟踪号");
		map.put("BindCardLogInfo.bankName", "银行名称");
		
		//业务相关
		map.put("BusinessInfo", "业务信息表");
		map.put("BusinessInfo.userId", "用户编号");
		map.put("BusinessInfo.createPho", "注册手机");
		map.put("BusinessInfo.businessType", "业务类型");
		map.put("BusinessInfo.businessStatus", "业务状态");
		map.put("BusinessInfo.respCode", "多渠道应答编码");
		map.put("BusinessInfo.respDesc", "多渠道应答描述");
		map.put("BusinessInfo.finishDate", "完成时间");
		map.put("BusinessInfo.acceptDate", "受理时间");
		map.put("BusinessInfo.refId", "系统跟踪号");
		
		
		//绑卡相关
		map.put("BindCardInfo", "绑卡信息表");
		map.put("BindCardInfo.userId", "用户编号");
		map.put("BindCardInfo.userRealName", "用户真实姓名");
		map.put("BindCardInfo.cardId", "身份证号");
		map.put("BindCardInfo.bankNo", "银行卡号");
		map.put("BindCardInfo.bankName", "银行名称");
		map.put("BindCardInfo.cardState", "卡状态");
		map.put("BindCardInfo.bindTime", "绑卡时间");
		
		//用户相关
		map.put("UserInfo", "用户信息表");
		map.put("UserInfo.loginName", "注册号");
		map.put("UserInfo.userId", "用户编号");
		map.put("UserInfo.idNum", "身份证号");
		map.put("UserInfo.userRealName", "真实姓名");
		map.put("UserInfo.createTime", "注册时间");
		map.put("UserInfo.lastLoginTime", "最后登录时间");
		map.put("UserInfo.status", "状态");
		map.put("UserInfo.phoneNo", "电话号码");
	}
	
	public static String getDesc(String code) {
		return map.get(code);
	}
}
