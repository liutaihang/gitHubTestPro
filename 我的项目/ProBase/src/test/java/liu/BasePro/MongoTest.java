package liu.BasePro;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import liu.dao.Other;
import liu.dao.mongo.PeopleDao;
import liu.dao.mybatisDao.BookDao;
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
	public static void main(String[] args) throws ParseException, InterruptedException, AddressException {
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
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "localhost");
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("liutaihang@laiyongche.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("1576756228@qq.com"));
			message.setSubject("this is header line!");
			
			message.setText("this is now message");
			
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
