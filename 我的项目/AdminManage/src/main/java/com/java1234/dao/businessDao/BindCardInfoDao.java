package com.java1234.dao.businessDao;

import java.util.List;

import com.java1234.Vo.searchVo.BindCardSearchVo;
import com.java1234.entity.business.BindCardInfo;

/**
 * 绑卡信息查询Dao
 *
 */
public interface BindCardInfoDao {
	
	/**
	 * 绑卡信息查询
	 * @param searchVo查询添加vo
	 * @return
	 */
	public List<BindCardInfo> BindCardSearch(BindCardSearchVo searchVo);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public Integer getBindCardInfoTotal(BindCardSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BindCardInfo> exportBindCardInfo(BindCardSearchVo searchVo);
}
