package liu.dao.mongo;

import org.springframework.stereotype.Repository;

import liu.dao.BaseDao.mongoDataBase;
import liu.po.People;

@Repository
public class PeopleDao extends mongoDataBase<People>{

	public People save(People people){
		return super.save(people);
	}


}
