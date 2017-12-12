package cn.lth.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.lth.demo.bean.DemoBean;
import cn.lth.demo.mapper.DemoMapper;

@Service
public class DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	/**
	 * 
	 * @param bean
	 */
	@Transactional
	public int addDemo(DemoBean bean){
		return demoMapper.addDemo(bean);
	}
}
