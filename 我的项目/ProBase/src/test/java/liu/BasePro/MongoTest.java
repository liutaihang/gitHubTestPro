package liu.BasePro;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import liu.dao.mongo.PeopleDao;
import liu.dao.mysqldao.BookDao;
import liu.po.Like;
import liu.po.People;


public class MongoTest extends BaseTest{

	@Resource
	PeopleDao dao;
	
	@Resource
	BookDao bookdao;
	
	@Test
	public void mongotest(){
		People people = new People("name", "sex", new Like("type", "name")); 
		System.out.println(dao.save(people));
	}
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apps", "loanApplyDianRong.getApps()");
		map.put("contractNo", "loanApplyDianRong.getContractNo()");
		map.put("loanId", "loanApplyDianRong.getLoanId()");
		map.put("attachment", "loanApplyDianRong.getDianRongAttachment()");
		System.out.println(map.toString());
	}
	
	@Test
	public void mysqltest(){
		System.out.println(bookdao.findAll().get(0));
	}
	
}
