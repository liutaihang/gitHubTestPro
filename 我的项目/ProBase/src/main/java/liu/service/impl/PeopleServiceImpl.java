package liu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liu.dao.mongo.PeopleDao;
import liu.po.People;
import liu.service.PeopleService;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService{

	@Resource
	private PeopleDao pdao;
	
	@Override
	public List<People> getAll() {
		return pdao.findAll();
	}

}
