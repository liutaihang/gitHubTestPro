package com.java1234.service.impl.businessImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.java1234.Vo.searchVo.UserSearchVo;
import com.java1234.dao.businessDao.UserInfoDao;
import com.java1234.entity.business.UserInfo;
import com.java1234.service.businessService.UserInfoService;
import com.java1234.utils.DesSensitiveData;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Resource
	private UserInfoDao userInfoDao;

	@Override
	public List<UserInfo> userInfoSearch(UserSearchVo searchVo) {
		if(searchVo == null){
			searchVo = new UserSearchVo();
			searchVo.setPage(1);
			return userInfoDao.userSearch(null, null, null, null, null, null, null, null, searchVo.getPage(), searchVo.getPageSize());
		}
		if(searchVo.getPage() == null){
			searchVo.setPage(1);
		}
		return userInfoDao.userSearch(searchVo.getLoginName(), searchVo.getPhoneNo(), 
				searchVo.getStartTime(), searchVo.getEndTime(), searchVo.getRealName(), 
				searchVo.getCardId(), searchVo.getUserId(), searchVo.getEnIdNum(), searchVo.getStartNo(), searchVo.getPageSize());
	}

	@Override
	public Integer getUserInfoTotal(UserSearchVo searchVo) {
		if(searchVo == null){
			return userInfoDao.getUserInfoTotal(null, null, null, null, null, null, null, null);
		}
		return userInfoDao.getUserInfoTotal(searchVo.getLoginName(), searchVo.getPhoneNo(), 
				searchVo.getStartTime(), searchVo.getEndTime(), searchVo.getRealName(), 
				searchVo.getCardId(), searchVo.getUserId(), searchVo.getEnIdNum());
	}

	@Override
	public List<UserInfo> exportUserInfo(UserSearchVo searchVo) throws Exception {
		List<UserInfo> infos = new ArrayList<UserInfo>();
		List<UserInfo> newInfos = new ArrayList<UserInfo>();
		if(searchVo == null){
			infos = userInfoDao.exportUserInfo(null, null, null, null, null, null, null, null);
		}
		infos = userInfoDao.exportUserInfo(searchVo.getLoginName(), searchVo.getPhoneNo(), 
				searchVo.getStartTime(), searchVo.getEndTime(), searchVo.getRealName(), 
				searchVo.getCardId(), searchVo.getUserId(), searchVo.getEnIdNum());
		for (UserInfo userInfo : infos) {
			if(StringUtils.isNotBlank(userInfo.getStatus())){
				if(userInfo.getStatus().equals("1")){
					userInfo.setStatus("白名单");
				}else if(userInfo.getStatus().equals("0")){
					userInfo.setStatus("");
				}
			}
			try {
				if (userInfo != null && StringUtils.isNotBlank(userInfo.getIdNum())
						&& !userInfo.getIdNum().substring(0, 12).matches("[0-9]*")) {
					userInfo.setIdNum(DesSensitiveData.deSensitiveData(userInfo.getIdNum()));
				}
			} catch (Exception e) {
				continue;
			}
			newInfos.add(userInfo);
		}
		return newInfos;
	}

}
