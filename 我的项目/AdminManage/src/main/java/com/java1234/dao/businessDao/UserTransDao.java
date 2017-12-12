package com.java1234.dao.businessDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.business.UserTransInfo;

/**
 * 用户交易信息查询Dao
 *
 */
public interface UserTransDao {
	
	/**
	 * 用户交易信息查询
	 * @param orderStatus 交易状态
	 * @param bankNo 银行卡号
	 * @param phoneNo 注册手机号
	 * @param payType 支付方式
	 * @param txnAmtStart 交易金额（查询这个区间的金额）
	 * @param txnAmtEnd 交易金额
	 * @param orderId 订单编号
	 * @param payMode 代扣方式
	 * @param refId 系统跟踪号
	 * @param acceptDateStart 受理时间（查询这个时间段的受理月份）
	 * @param acceptDateEnd 受理时间
	 * @param bType = bType字符串则为空充
	 * @return List<UserTransInfo>
	 */
	public List<UserTransInfo> userTransSearch(@Param("orderStatus")String orderStatus, 
												@Param("bankNo")String bankNo, 
												@Param("orderId")String orderId, 
												@Param("serv")String serv, 
												@Param("txnAmtStart")Integer txnAmtStart, 
												@Param("txnAmtEnd")Integer txnAmtEnd, 
												@Param("resId")String resId, 
												@Param("payMode")String payMode, 
												@Param("refId")String refId, 
												@Param("acceptDateStart")String acceptDateStart,
												@Param("acceptDateEnd")String acceptDateEnd,
												@Param("bType")String bType,
												@Param("startNo")Integer startNo,
												@Param("pageSize")Integer pageSize);
	/**
	 * 导出Excel
	 */
	public List<UserTransInfo> exportExcel(
			@Param("orderStatus")String orderStatus, 
			@Param("bankNo")String bankNo, 
			@Param("orderId")String orderId, 
			@Param("serv")String serv, 
			@Param("txnAmtStart")Integer txnAmtStart, 
			@Param("txnAmtEnd")Integer txnAmtEnd, 
			@Param("resId")String resId, 
			@Param("payMode")String payMode, 
			@Param("refId")String refId, 
			@Param("acceptDateStart")String acceptDateStart,
			@Param("acceptDateEnd")String acceptDateEnd,
			@Param("bType")String bType,
			@Param("startNum")Integer startNum,
			@Param("pageSize")Integer pageSize
			);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public Integer getTotal(
			@Param("orderStatus")String orderStatus, 
			@Param("bankNo")String bankNo, 
			@Param("orderId")String orderId, 
			@Param("serv")String serv, 
			@Param("txnAmtStart")Integer txnAmtStart, 
			@Param("txnAmtEnd")Integer txnAmtEnd, 
			@Param("resId")String resId, 
			@Param("payMode")String payMode, 
			@Param("refId")String refId, 
			@Param("acceptDateStart")String acceptDateStart,
			@Param("acceptDateEnd")String acceptDateEnd,
			@Param("bType")String bType);
}
