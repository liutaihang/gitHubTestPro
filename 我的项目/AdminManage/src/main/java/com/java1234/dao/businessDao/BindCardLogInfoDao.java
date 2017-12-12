package com.java1234.dao.businessDao;

import java.util.List;

import com.java1234.Vo.searchVo.BindCardLogSearchVo;
import com.java1234.entity.business.BindCardLogInfo;

/**
 * 绑卡日志信息查询Dao
 *
 */
public interface BindCardLogInfoDao {
	
	/**
	 * 绑卡日志查询
	 * @param searchVo 查询条件vo
	 * @return
	 */
	public List<BindCardLogInfo> bindCardLogSearch(BindCardLogSearchVo searchVo);
	
	/**
	 * 获取总数量
	 * @return
	 */
	public Integer getBindCardLogInfoTotal(BindCardLogSearchVo searchVo);
	
	/**
	 * 导出
	 * @param searchVo
	 * @return
	 */
	public List<BindCardLogInfo> exportBindCardLog(BindCardLogSearchVo searchVo);
}
