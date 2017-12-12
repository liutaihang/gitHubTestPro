package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.searchVo.UserSearchVo;
import com.java1234.entity.business.UserInfo;

/**
 * 用户信息查询service
 *
 */
public interface UserInfoService {
	/**
	 * 用户信息查询
	 * @param searchVo
	 * @return
	 */
	public List<UserInfo> userInfoSearch(UserSearchVo searchVo);
	
	/**
	 * 获取所有数量
	 * @return
	 */
	public Integer getUserInfoTotal(UserSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<UserInfo> exportUserInfo(UserSearchVo searchVo)throws Exception;
}
