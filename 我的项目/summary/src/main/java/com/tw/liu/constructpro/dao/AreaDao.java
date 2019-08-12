package com.tw.liu.constructpro.dao;

import com.tw.liu.constructpro.entity.SysArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaDao extends JpaRepository<SysArea, String> {
}
