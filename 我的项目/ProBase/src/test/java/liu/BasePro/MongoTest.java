package liu.BasePro;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import liu.dao.hibernate.HibernateDao;
import liu.dao.mongo.PeopleDao;
import liu.dao.mybatisDao.BookDao;
import liu.dao.mybatisDao.DaoBook;
import liu.po.Book;
import liu.po.Like;
import liu.po.People;
import liu.po.msq.UserInfo;


public class MongoTest extends BaseTest{

	@Resource
	PeopleDao dao;
	
//	@Resource
//	BookDao bookdao;
//	
//	@Resource
//	DaoBook daoBook;
	
//	@Resource
//	PeopleManager manager;
	
	@Resource
	HibernateDao h1;
	
	@Test
	public void mongotest() throws InterruptedException{
//		People [id=5979a4c1acf1b3f514764507, name=name, sex=sex, like=Like [type=type, name=name]]
		People people = new People("name", "sex", new Like("type", "name")); 
		people.setId("5979a4c1acf1b3f514764507");
//		System.out.println(dao.save(people));

		System.out.println(dao.findAll());

//		System.out.println(new TestAop().setString("something"));
	}
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hibernate-config.xml");
		HibernateDao hibernateDao = (HibernateDao) context.getBean("hibernateDao");
		UserInfo user = new UserInfo("name", "account", "pwd", "男", 21);
		hibernateDao.add(user);
		System.out.println(user);
	}
	
	@Test
	public void mysqltest(){
//		System.out.println(bookdao.findAll());
//		List<Book> list = daoBook.getAll();
//		System.out.println(list);
	}
	
//	@Test
//	public void managertest(){
//		Map<String, Object> maps = manager.getAll();
//		List<People> lists = (List<People>) maps.get("data");
//		System.out.println(maps.get("msg") + lists.get(6).toString());
//	}

	private static void sets(TestBean bean) throws InterruptedException{
		Map<String, Object> maps = new HashMap<>();
		bean.setRequestMap(maps);

		Thread.sleep(10000);
		maps.put("1", "1");maps.put("2", "2");
	}
	
	@Test
	public void hiber(){
		System.out.println(h1.getByid(1));
	}
}
