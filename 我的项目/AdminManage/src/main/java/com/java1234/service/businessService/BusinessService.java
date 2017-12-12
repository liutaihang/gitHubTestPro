package com.java1234.service.businessService;

import java.util.List;

import com.java1234.Vo.searchVo.BusinessSearchVo;
import com.java1234.entity.business.BusinessInfo;

/**
 * 业务信息查询Service
 *
 */
public interface BusinessService {
	
	/**
	 * 业务信息查询
	 * @param searchVo
	 * @return List<BusinessInfo>
	 */
	public List<BusinessInfo> businessSearch(BusinessSearchVo searchVo);
	
	public Integer getBusinessTotal(BusinessSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BusinessInfo> exportBusiness(BusinessSearchVo searchVo);
}
