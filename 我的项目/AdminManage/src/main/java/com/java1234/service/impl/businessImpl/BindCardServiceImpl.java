package com.java1234.service.impl.businessImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.java1234.Vo.searchVo.BindCardSearchVo;
import com.java1234.dao.businessDao.BindCardInfoDao;
import com.java1234.entity.business.BindCardInfo;
import com.java1234.service.businessService.BindCardService;
import com.java1234.utils.DesSensitiveData;

@Service
public class BindCardServiceImpl implements BindCardService{
	
	@Resource
	private BindCardInfoDao cardInfoDao;
	
	@Override
	public List<BindCardInfo> bindCardSearch(BindCardSearchVo searchVo) throws Exception {
		if(searchVo != null && StringUtils.isNotBlank(searchVo.getBankNo()) && searchVo.getBankNo() != ""){
//			 searchVo.setBankNo(DesSensitiveData.enSensitiveData(searchVo.getBankNo()));
		}
		if (searchVo != null && StringUtils.isNotBlank(searchVo.getCardId()) && searchVo.getCardId() != "") {
			searchVo.setCardId(DesSensitiveData.enSensitiveData(searchVo.getCardId()));
		}
		List<BindCardInfo> bindCardInfos  = cardInfoDao.BindCardSearch(searchVo);
		List<BindCardInfo> newBindCardInfos = new ArrayList<BindCardInfo>();
		if(bindCardInfos != null){
			for (BindCardInfo bindCardInfo : bindCardInfos) {
				if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getBankNo())){
					 bindCardInfo.setBankNo(DesSensitiveData.deSensitiveData(bindCardInfo.getBankNo()));
				}
				if (bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getCardId())) {
					bindCardInfo.setCardId(DesSensitiveData.deSensitiveData(bindCardInfo.getCardId()));
				}
				newBindCardInfos.add(bindCardInfo);	
			}
		}
		return newBindCardInfos;
	}

	@Override
	public Integer getBindCardTotal(BindCardSearchVo searchVo) {
		Integer total = cardInfoDao.getBindCardInfoTotal(searchVo);
		return total;
	}

	@Override
	public List<BindCardInfo> exportBindCardInfo(BindCardSearchVo searchVo) throws Exception {
		if(searchVo != null && StringUtils.isNotBlank(searchVo.getBankNo()) && searchVo.getBankNo() != ""){
//			 searchVo.setBankNo(DesSensitiveData.enSensitiveData(searchVo.getBankNo()));
		}
		if (searchVo != null && StringUtils.isNotBlank(searchVo.getCardId()) && searchVo.getCardId() != "") {
			searchVo.setCardId(DesSensitiveData.enSensitiveData(searchVo.getCardId()));
		}
		List<BindCardInfo> bindCardInfos  = cardInfoDao.exportBindCardInfo(searchVo);
		List<BindCardInfo> newBindCardInfos = new ArrayList<BindCardInfo>();
		if(bindCardInfos != null){
			for (BindCardInfo bindCardInfo : bindCardInfos) {
				if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getBankNo())){
					 bindCardInfo.setBankNo(DesSensitiveData.deSensitiveData(bindCardInfo.getBankNo()));
				}
				if (bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getCardId())) {
					bindCardInfo.setCardId(DesSensitiveData.deSensitiveData(bindCardInfo.getCardId()));
				}
				newBindCardInfos.add(bindCardInfo);	
			}
		}
		return newBindCardInfos;
	}
}
