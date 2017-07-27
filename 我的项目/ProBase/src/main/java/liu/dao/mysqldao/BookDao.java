package liu.dao.mysqldao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import liu.po.Book;

@Repository
public interface BookDao {

	@Select("select * from book")
	public List<Book> findAll();
}
