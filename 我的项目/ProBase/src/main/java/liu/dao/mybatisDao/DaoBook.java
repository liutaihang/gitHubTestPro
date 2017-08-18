package liu.dao.mybatisDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import liu.dao.BaseDao.MysqlDataBase;
import liu.po.Book;

@Repository("daoBook")
public class DaoBook extends MysqlDataBase<Book, Integer>{

	
	public List<Book> getAll(){
		return super.findAll();
	}
	
}
