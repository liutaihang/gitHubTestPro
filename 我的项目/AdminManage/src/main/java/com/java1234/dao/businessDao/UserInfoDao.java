package com.java1234.dao.businessDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java1234.entity.business.UserInfo;

/**
 * 用户信息查询Dao
 *
 */
public interface UserInfoDao {
	/**
	 * 
	 * @param loginName 注册名（电话号码）
	 * @param phoneNo 电话号码
	 * @param startTime 注册开始时间
	 * @param endTime 注册结束时间
	 * @param realName 真实姓名
	 * @param cardId 证件号码
	 * @param userId 用户编号
	 * @return List<UserInfo>
	 */
	public List<UserInfo> userSearch(@Param("loginName")String loginName, 
									 @Param("phoneNo")String phoneNo, 
							 		 @Param("startTime")String startTime, 
							 		@Param("endTime")String endTime, 
							 		@Param("realName")String realName, 
							 		@Param("cardId")String cardId, 
							 		@Param("userId")String userId,
							 		@Param("enIdNum")String enIdNum,
							 		@Param("startNo")Integer startNo,
							 		@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public Integer getUserInfoTotal(
			@Param("loginName")String loginName, 
			 @Param("phoneNo")String phoneNo, 
	 		 @Param("startTime")String startTime, 
	 		@Param("endTime")String endTime, 
	 		@Param("realName")String realName, 
	 		@Param("cardId")String cardId, 
	 		@Param("userId")String userId,
	 		@Param("enIdNum")String enIdNum);
	
	/**
	 * 导出
	 * @param loginName
	 * @param phoneNo
	 * @param startTime
	 * @param endTime
	 * @param realName
	 * @param cardId
	 * @param userId
	 * @param enIdNum
	 * @return
	 */
	public List<UserInfo> exportUserInfo(
			@Param("loginName")String loginName, 
			@Param("phoneNo")String phoneNo, 
	 		@Param("startTime")String startTime, 
	 		@Param("endTime")String endTime, 
	 		@Param("realName")String realName, 
	 		@Param("cardId")String cardId, 
	 		@Param("userId")String userId,
	 		@Param("enIdNum")String enIdNum);
}
