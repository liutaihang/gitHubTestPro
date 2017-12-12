package com.java1234.service.impl.businessImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.Vo.searchVo.BusinessSearchVo;
import com.java1234.dao.businessDao.BusinessInfoDao;
import com.java1234.entity.business.BusinessInfo;
import com.java1234.service.businessService.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService{

	@Resource
	private BusinessInfoDao businessInfoDao;
	
	@Override
	public List<BusinessInfo> businessSearch(BusinessSearchVo searchVo) {
		return businessInfoDao.businessSearch(searchVo);
	}

	@Override
	public Integer getBusinessTotal(BusinessSearchVo searchVo) {
		return businessInfoDao.getBusinessTotal(searchVo);
	}

	@Override
	public List<BusinessInfo> exportBusiness(BusinessSearchVo searchVo) {
		return businessInfoDao.exportBusiness(searchVo);
	}
	
	

}
