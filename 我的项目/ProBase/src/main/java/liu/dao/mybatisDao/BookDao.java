package liu.dao.mybatisDao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import liu.po.Book;

@Repository
public interface BookDao {

	
	@Select("select * from book")
	@Results({
		@Result(column = "bookId",property = "bookId"),
		@Result(column = "name", property = "name"),
		@Result(column = "number", property = "number")
	})
	public List<Book> findAll();
}
