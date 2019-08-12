package com.tw.liu.constructpro.dao;

import com.tw.liu.constructpro.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysUserDao extends JpaRepository<SysUser, String> {

    List<SysUser> findSysUsersByMobile(String mobile);
}
