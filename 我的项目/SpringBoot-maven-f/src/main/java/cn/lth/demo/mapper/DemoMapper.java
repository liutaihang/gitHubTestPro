package cn.lth.demo.mapper;

import org.apache.ibatis.annotations.Insert;

import cn.lth.demo.bean.DemoBean;


public interface DemoMapper {
	@Insert("insert into sys_role(name) values(#{name})")
	public int addDemo(DemoBean bean);
}
