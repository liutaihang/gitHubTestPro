package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.searchVo.BindCardSearchVo;
import com.java1234.entity.business.BindCardInfo;

/**
 * 绑卡信息查询Service
 *
 */
public interface BindCardService {

	/**
	 * 绑卡信息查询
	 * @param searchVo
	 * @return List<BindCardInfo>
	 */
	public List<BindCardInfo> bindCardSearch(BindCardSearchVo searchVo) throws Exception;
	
	/**
	 * 获取数量
	 * @param searchVo
	 * @return
	 */
	public Integer getBindCardTotal(BindCardSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BindCardInfo> exportBindCardInfo(BindCardSearchVo searchVo) throws Exception;
}
