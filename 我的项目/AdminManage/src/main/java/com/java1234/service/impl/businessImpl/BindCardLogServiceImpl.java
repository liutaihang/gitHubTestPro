package com.java1234.service.impl.businessImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.java1234.Vo.searchVo.BindCardLogSearchVo;
import com.java1234.dao.businessDao.BindCardLogInfoDao;
import com.java1234.entity.business.BindCardLogInfo;
import com.java1234.service.businessService.BindCardLogService;
import com.java1234.utils.DesSensitiveData;

@Service
public class BindCardLogServiceImpl implements BindCardLogService{

	@Resource
	private BindCardLogInfoDao infoDao;
	
	@Override
	public List<BindCardLogInfo> bindCardLogSearch(BindCardLogSearchVo searchVo) throws Exception {
		if(StringUtils.isNotBlank(searchVo.getBankNo())){ //bankNo放的银行卡号
			searchVo.setBankNo(DesSensitiveData.enSensitiveData(searchVo.getBankNo()));
		}
		if(StringUtils.isNotBlank(searchVo.getPhoneNo())){
			searchVo.setPhoneNo(DesSensitiveData.enSensitiveData(searchVo.getPhoneNo()));
		}
		if (StringUtils.isNotBlank(searchVo.getRealName())) {
			searchVo.setRealName(DesSensitiveData.enSensitiveData(searchVo.getRealName()));
		}
		if (StringUtils.isNotBlank(searchVo.getCardId())) { //cardId放的身份证号
			searchVo.setCardId(DesSensitiveData.enSensitiveData(searchVo.getCardId()));
		}
		List<BindCardLogInfo> bindCardLogInfos  = infoDao.bindCardLogSearch(searchVo);
		List<BindCardLogInfo> newBindCardLogInfos = new ArrayList<BindCardLogInfo>();
		if(bindCardLogInfos != null){
			for (BindCardLogInfo bindCardInfo : bindCardLogInfos) {
				try {
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getBankNo())){
						bindCardInfo.setBankNo(DesSensitiveData.deSensitiveData(bindCardInfo.getBankNo()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getPhoneNo())){
						bindCardInfo.setPhoneNo(DesSensitiveData.deSensitiveData(bindCardInfo.getPhoneNo()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getUserRealName())){
						bindCardInfo.setUserRealName(DesSensitiveData.deSensitiveData(bindCardInfo.getUserRealName()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getCardId())){
						bindCardInfo.setCardId(DesSensitiveData.deSensitiveData(bindCardInfo.getCardId()));
					}
				} catch (Exception e) {
					continue;
				}
				newBindCardLogInfos.add(bindCardInfo);	
			}
		}
		return newBindCardLogInfos;
	}
	
	/**
	 * 绑卡日志数量
	 */
	public Integer getBindCardLogTotal(BindCardLogSearchVo searchVo){
		Integer total = infoDao.getBindCardLogInfoTotal(searchVo);
		return total;
	}

	@Override
	public List<BindCardLogInfo> exportBindCardLog(BindCardLogSearchVo searchVo) throws Exception {
		if(StringUtils.isNotBlank(searchVo.getBankNo())){ //bankNo放的银行卡号
			searchVo.setBankNo(DesSensitiveData.enSensitiveData(searchVo.getBankNo()));
		}
		if(StringUtils.isNotBlank(searchVo.getPhoneNo())){
			searchVo.setPhoneNo(DesSensitiveData.enSensitiveData(searchVo.getPhoneNo()));
		}
		if (StringUtils.isNotBlank(searchVo.getRealName())) {
			searchVo.setRealName(DesSensitiveData.enSensitiveData(searchVo.getRealName()));
		}
		if (StringUtils.isNotBlank(searchVo.getCardId())) { //cardId放的身份证号
			searchVo.setCardId(DesSensitiveData.enSensitiveData(searchVo.getCardId()));
		}
		List<BindCardLogInfo> bindCardLogInfos = infoDao.exportBindCardLog(searchVo);
		List<BindCardLogInfo> newBindCardLogInfos = new ArrayList<BindCardLogInfo>();
		if(bindCardLogInfos != null){
			for (BindCardLogInfo bindCardInfo : bindCardLogInfos) {
				try {
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getBankNo())){
						bindCardInfo.setBankNo(DesSensitiveData.deSensitiveData(bindCardInfo.getBankNo()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getPhoneNo())){
						bindCardInfo.setPhoneNo(DesSensitiveData.deSensitiveData(bindCardInfo.getPhoneNo()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getUserRealName())){
						bindCardInfo.setUserRealName(DesSensitiveData.deSensitiveData(bindCardInfo.getUserRealName()));
					}
					if(bindCardInfo != null && StringUtils.isNotBlank(bindCardInfo.getCardId())){
						bindCardInfo.setCardId(DesSensitiveData.deSensitiveData(bindCardInfo.getCardId()));
					}
				} catch (Exception e) {
					continue;
				}
				newBindCardLogInfos.add(bindCardInfo);	
			}
		}
		return newBindCardLogInfos;
	}
}
