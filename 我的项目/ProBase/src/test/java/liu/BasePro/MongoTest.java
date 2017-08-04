package liu.BasePro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import liu.aop.SpringAopTest;
import liu.aop.BeforeTestAdvice;
import liu.dao.Other;
import liu.dao.mongo.PeopleDao;
import liu.dao.mybatisDao.BookDao;
import liu.dao.mybatisDao.DaoBook;
import liu.manager.PeopleManager;
import liu.po.Like;
import liu.po.People;


public class MongoTest extends BaseTest{

	@Resource
	PeopleDao dao;
	
	@Resource
	BookDao bookdao;
	
//	@Resource
//	DaoBook daoBook;
	
	@Resource
	PeopleManager manager;
	
	@Test
	public void mongotest() throws InterruptedException{
//		People [id=5979a4c1acf1b3f514764507, name=name, sex=sex, like=Like [type=type, name=name]]
		People people = new People("name", "sex", new Like("type", "name")); 
		people.setId("5979a4c1acf1b3f514764507");
//		System.out.println(dao.save(people));

		System.out.println(dao.findAll());

//		System.out.println(new TestAop().setString("something"));
	}
	public static void main(String[] args) throws ParseException, InterruptedException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("apps", "loanApplyDianRong.getApps()");
//		map.put("contractNo", "loanApplyDianRong.getContractNo()");
//		map.put("loanId", "loanApplyDianRong.getLoanId()");
//		map.put("attachment", "loanApplyDianRong.getDianRongAttachment()");
//		System.out.println(map.toString());
		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(format.parse("2017-7-26 18:10:20").getTime());
//		TestBean bean = new TestBean();
//		MongoTest.sets(bean);
//		System.out.println(bean);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		Other dao = (Other) context.getBean("Other");
		dao.say(Thread.currentThread().getClass().getName() + "." + Thread.currentThread().getName());
		
		System.out.println();
	}
	
	@Test
	public void mysqltest(){
		System.out.println(bookdao.findAll().get(0));
//		System.out.println(daoBook.getAll().get(0));
	}
	
	@Test
	public void managertest(){
		Map<String, Object> maps = manager.getAll();
		List<People> lists = (List<People>) maps.get("data");
		System.out.println(maps.get("msg") + lists.get(6).toString());
	}

	private static void sets(TestBean bean) throws InterruptedException{
		Map<String, Object> maps = new HashMap<>();
		bean.setRequestMap(maps);

		Thread.sleep(10000);
		maps.put("1", "1");maps.put("2", "2");
	}
}
