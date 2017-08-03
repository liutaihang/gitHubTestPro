package liu.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import liu.dao.BaseDao.mongoDataBase;
import liu.po.People;

@Repository
public class PeopleDao extends mongoDataBase<People>{

	public People save(People people){
		return super.save(people);
	}

	public long remove(People people){
		return super.remove(people, people.getId());
	}

	public List<People> findAll(){
		System.out.println("测试");
		return super.findAll();
	}
	
}
