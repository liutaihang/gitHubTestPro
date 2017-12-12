package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.ExportPageVo;
import com.java1234.Vo.searchVo.UserTransSearchVo;
import com.java1234.entity.business.UserTransInfo;

/**
 * 用户交易信息查询Service
 *
 */
public interface UserTransService {

	/**
	 * 用户交易信息查询
	 * @param searchVo
	 * @return
	 */
	public List<UserTransInfo> userTransSearch(UserTransSearchVo searchVo);
	
	/**
	 * 导出Excel文件
	 * @param pageVo
	 * @return
	 */
	public List<UserTransInfo> ExportTransInfo(ExportPageVo pageVo, UserTransSearchVo searchVo);
	
	/**
	 * 获取所有数量
	 * @return
	 */
	public Integer getTotal(UserTransSearchVo searchVo);
}
