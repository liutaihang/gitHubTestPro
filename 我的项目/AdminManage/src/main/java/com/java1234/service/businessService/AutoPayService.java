package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.searchVo.AutoPaySearchVo;
import com.java1234.entity.business.AutoPayUserInfo;

/**
 * 自动支付用户信息查询 Service
 *
 */
public interface AutoPayService {

	/**
	 * 自动支付用户信息查询
	 * @param searchVo
	 * @return List<AutoPayUserInfo>
	 */
	public List<AutoPayUserInfo> autoPaySearch(AutoPaySearchVo searchVo);
}
