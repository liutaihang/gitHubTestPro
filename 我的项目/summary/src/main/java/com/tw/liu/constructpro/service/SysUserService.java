package com.tw.liu.constructpro.service;

import com.tw.liu.constructpro.dao.SysUserDao;
import com.tw.liu.constructpro.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService{

    private SysUserDao sysUserDao;

    public SysUserService(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public SysUser save(SysUser sysUser){
        return sysUserDao.save(sysUser);
    }

    public List<SysUser> findAll(){
        return sysUserDao.findAll();
    }

}
