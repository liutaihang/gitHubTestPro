package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.searchVo.BindCardLogSearchVo;
import com.java1234.entity.business.BindCardLogInfo;

/**
 * 绑卡日志信息Service
 *
 */
public interface BindCardLogService {

	/**
	 * 绑卡日志信息
	 * @param searchVo
	 * @return List<BindCardLogInfo>
	 */
	public List<BindCardLogInfo> bindCardLogSearch(BindCardLogSearchVo searchVo) throws Exception ;
	
	/**
	 * 绑卡日志数量
	 * @param searchVo
	 * @return
	 */
	public Integer getBindCardLogTotal(BindCardLogSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BindCardLogInfo> exportBindCardLog(BindCardLogSearchVo searchVo) throws Exception;
}
