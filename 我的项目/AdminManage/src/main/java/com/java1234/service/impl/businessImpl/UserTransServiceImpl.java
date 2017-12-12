package com.java1234.service.impl.businessImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.Vo.ExportPageVo;
import com.java1234.Vo.searchVo.UserTransSearchVo;
import com.java1234.dao.businessDao.UserTransDao;
import com.java1234.entity.business.UserTransInfo;
import com.java1234.service.businessService.UserTransService;

@Service
public class UserTransServiceImpl implements UserTransService{
	@Resource
	private UserTransDao userTransDao;
	
	@Override
	public List<UserTransInfo> userTransSearch(UserTransSearchVo searchVo) {
		if(searchVo.getPage() == null || searchVo.getPage() < 1){
			searchVo.setPage(1);
		}
		 List<UserTransInfo> userTransSearch = userTransDao.userTransSearch(searchVo.getTradeStatus(), searchVo.getBankNo(), searchVo.getOrderId(), 
					searchVo.getServ(), searchVo.getTxnAmtStart(), searchVo.getTxnAmtEnd(), searchVo.getResId(), 
					searchVo.getPayMode(), searchVo.getRefId(), searchVo.getAcceptDateStart(),
					searchVo.getAcceptDateEnd(), searchVo.getbType(), searchVo.getStartNo(), searchVo.getPageSize());
		return userTransSearch;
	}

	@Override
	public List<UserTransInfo> ExportTransInfo(ExportPageVo pageVo, UserTransSearchVo searchVo) {
		if(pageVo != null && pageVo.getPageNo() != null && pageVo.getPageNo().length > 0){
			pageVo.setStartNum((pageVo.getPageNo()[0] - 1) * pageVo.getPageSize());//开始数
			pageVo.setPageSize((pageVo.getPageNo()[pageVo.getPageNo().length - 1] - 
					pageVo.getPageNo()[0] + 1) * pageVo.getPageSize());//所需查询数据量
		}
		return userTransDao.exportExcel(searchVo.getTradeStatus(), searchVo.getBankNo(), searchVo.getOrderId(), 
				searchVo.getServ(), searchVo.getTxnAmtStart(), searchVo.getTxnAmtEnd(), searchVo.getResId(),
				searchVo.getPayMode(), searchVo.getRefId(), searchVo.getAcceptDateStart(), 
				searchVo.getAcceptDateEnd(), searchVo.getbType(),null, null);
	}

	@Override
	public Integer getTotal(UserTransSearchVo searchVo) {
		Integer total = userTransDao.getTotal(searchVo.getTradeStatus(), searchVo.getBankNo(), searchVo.getOrderId(), 
				searchVo.getServ(), searchVo.getTxnAmtStart(), searchVo.getTxnAmtEnd(), searchVo.getResId(),
				searchVo.getPayMode(), searchVo.getRefId(), searchVo.getAcceptDateStart(), 
				searchVo.getAcceptDateEnd(), searchVo.getbType());
		return total;
	}
}
