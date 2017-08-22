package liu.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import liu.dao.BaseDao.MysqlDataBase;
import liu.po.msq.UserInfo;

@Repository
@Transactional
public class HibernateDao extends MysqlDataBase<UserInfo, Integer>{

	public UserInfo getByid(int id){
		return super.findById(id);
	}
	
	public UserInfo add(UserInfo user){
		return super.save(user);
	}
}
