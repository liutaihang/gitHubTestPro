package liu.BasePro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

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
	
	@Resource
	PeopleManager manager;
	@Test
	public void mongotest(){
//		People [id=5979a4c1acf1b3f514764507, name=name, sex=sex, like=Like [type=type, name=name]]
		People people = new People("name", "sex", new Like("type", "name")); 
		people.setId("5979a4c1acf1b3f514764507");
//		System.out.println(dao.save(people));
		System.out.println(dao.remove(people));
	}
	public static void main(String[] args) throws ParseException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("apps", "loanApplyDianRong.getApps()");
//		map.put("contractNo", "loanApplyDianRong.getContractNo()");
//		map.put("loanId", "loanApplyDianRong.getLoanId()");
//		map.put("attachment", "loanApplyDianRong.getDianRongAttachment()");
//		System.out.println(map.toString());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.parse("2017-7-26 18:10:20").getTime());
	}
	
	@Test
	public void mysqltest(){
		System.out.println(bookdao.findAll().get(0));
	}
	
	@Test
	public void managertest(){
		Map<String, Object> maps = manager.getAll();
		List<People> lists = (List<People>) maps.get("data");
		System.out.println(maps.get("msg") + lists.get(6).toString());
	}
	
}
