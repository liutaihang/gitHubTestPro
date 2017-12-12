package com.java1234.dao.businessDao;

import java.util.List;

import com.java1234.Vo.searchVo.AutoPaySearchVo;
import com.java1234.entity.business.AutoPayUserInfo;
/**
 * 自动代扣用户信息查询Dao
 *
 */
public interface AutoPayUserInfoDao {
	
	/**
	 * 自动代扣用户信息查询
	 * @param searchVo 
	 * @return
	 */
	public List<AutoPayUserInfo> autoPaySearch(AutoPaySearchVo searchVo);
	
	/**
	 * 获取所有数量
	 * @return
	 */
	public Integer getAutoPayTotal();
}
