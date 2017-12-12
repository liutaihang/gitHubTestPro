package com.java1234.service.impl.businessImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.Vo.searchVo.AutoPaySearchVo;
import com.java1234.dao.businessDao.AutoPayUserInfoDao;
import com.java1234.entity.business.AutoPayUserInfo;
import com.java1234.service.businessService.AutoPayService;

@Service
public class AutoPayServiceImpl implements AutoPayService{

	@Resource
	private AutoPayUserInfoDao payUserInfoDao;
	
	@Override
	public List<AutoPayUserInfo> autoPaySearch(AutoPaySearchVo searchVo) {
		
		return payUserInfoDao.autoPaySearch(searchVo);
	}

}
