package com.java1234.dao.businessDao;

import java.util.List;

import com.java1234.Vo.searchVo.BusinessSearchVo;
import com.java1234.entity.business.BusinessInfo;

/**
 * 业务信息查询Dao
 *
 */
public interface BusinessInfoDao {
	
	/**
	 * 业务信息查询
	 * @param searchVo
	 * @return
	 */
	public List<BusinessInfo> businessSearch(BusinessSearchVo searchVo);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public Integer getBusinessTotal(BusinessSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BusinessInfo> exportBusiness(BusinessSearchVo searchVo);
}
