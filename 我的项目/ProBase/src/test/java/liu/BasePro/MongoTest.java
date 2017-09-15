package liu.BasePro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.tomcat.jni.OS;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import liu.BasePro.enter.EnterAndPersonExplain;
import liu.BasePro.enter.EnterAssets;
import liu.BasePro.enter.EnterBankDeposit;
import liu.BasePro.enter.EnterCars;
import liu.BasePro.enter.EnterCash;
import liu.BasePro.enter.EnterDataSurvey;
import liu.BasePro.enter.EnterFinaPosition;
import liu.BasePro.enter.EnterFinanAnal;
import liu.BasePro.enter.EnterIncome;
import liu.BasePro.enter.EnterInterest;
import liu.BasePro.enter.EnterLoan;
import liu.BasePro.enter.EnterPayableFinan;
import liu.BasePro.enter.EnterRiskAnalsis;
import liu.BasePro.enter.LoanDataCheck;
import liu.BasePro.enter.LoanEnterDataType;
import liu.BasePro.enter.LoanRiskAnalsis;
import liu.dao.hibernate.HibernateDao;
import liu.dao.mongo.PeopleDao;
import liu.po.Like;
import liu.po.People;


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
		//资产备用文件
		EnterAssets assets = new EnterAssets();
		List<Map<String, String>> lists = new ArrayList<>();
		Map<String, String> maps = new HashMap<>();
		maps.put("资产/设备名称（车辆名称）", "奥迪 A6 1.2L 两厢 乞丐到家版");
		maps.put("是否全款购买", "否");
		maps.put("非全款月还", "1200.00");
		maps.put("是否抵押", "否");
		lists.add(maps);
		assets.setAssetsMaps(lists);
		
		//企业及相关人员基本情况
		EnterAndPersonExplain andPersonExplain = new EnterAndPersonExplain();
		andPersonExplain.setDetails("详细情况");
		andPersonExplain.setLoanEnterDataType(LoanEnterDataType.BOOK_KEEPER);
		List<EnterAndPersonExplain> explains= new ArrayList<EnterAndPersonExplain>();
		explains.add(andPersonExplain);
		
		//银行存款
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("账户名称", "北京XXXXX有限公司");
		maps.put("开户银行", "工商银行");
		maps.put("账户用途", "理财");
		maps.put("期末余额", "180000.00");
		lists.add(maps);
		EnterBankDeposit bankDeposit = new EnterBankDeposit();
		bankDeposit.setDepositMaps(lists);
		bankDeposit.setRemark("备注");
		
		//目前履约车及相关收入与测算
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("车型", "奥迪 A6 1.2L 两厢 乞丐到家版");
		maps.put("数量", "5辆");
		maps.put("月租金", "1200.00");
		maps.put("月运营收入", "180000.00");
		lists.add(maps);
		EnterCars cars = new EnterCars();
		cars.setCarMaps(lists);
		cars.setRemark("备注");
		
		//现金
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("查询日期", "2017年10月5日");
		maps.put("金额", "180000.00");
		lists.add(maps);
		EnterCash cash = new EnterCash();
		cash.setCashMaps(lists);
		cash.setRemark("备注");
		
		//公司财务状况分析
		EnterFinanAnal anal = new EnterFinanAnal();
		anal.setInfo("信息");
		anal.setRemark("备注");
		anal.setEnterFinaPosition(EnterFinaPosition.DEBT);
		List<EnterFinanAnal> anals = new ArrayList<>();
		anals.add(anal);
		
		//收入计算电核记录
		EnterIncome enterIncome = new EnterIncome();
		enterIncome.setInfo("信息");
		enterIncome.setRemark("备注");
		
		//结息
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("查询日期", "2017年10月5日");
		maps.put("金额", "180000.00");
		lists.add(maps);
		EnterInterest enterInterest = new EnterInterest();
		enterInterest.setInterestMaps(lists);
		enterInterest.setRemark("备注");
		
		//短/长期借款类审核文件
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("企业名称", "北京XXXXX有限公司");
		maps.put("金额", "11112200.00");
		maps.put("还款日", "2017年10月5日");
		maps.put("月还", "18000.00");
		lists.add(maps);
		EnterLoan enterLoan = new EnterLoan();
		enterLoan.setLoanMaps(lists);
		enterLoan.setRemark("备注");
		
		//应付财务审核文件
		lists = new ArrayList<>();
		maps = new HashMap<>();
		maps.put("企业名称", "北京XXXXX有限公司");
		maps.put("金额", "11112200.00");
		maps.put("应付款日期", "2017年10月5日");
		maps.put("备注", "18000.00");
		lists.add(maps);
		EnterPayableFinan enterPayableFinan = new EnterPayableFinan();
		enterPayableFinan.setFinanMaps(lists);
		enterPayableFinan.setRemark("备注");
		
		//风险分析及意见
		EnterRiskAnalsis analsis = new EnterRiskAnalsis();
		analsis.setInfo("信息");
		analsis.setLoanRiskAnalsis(LoanRiskAnalsis.COMPANY_ADVANTAGE);
		analsis.setRemark("备注");
		List<EnterRiskAnalsis> analsis2 = new ArrayList<>();
		analsis2.add(analsis);
		
//		资料审核
		LoanDataCheck check = new LoanDataCheck();
		maps = new HashMap<>();
		maps.put("文件名", "文件地址");
		check.setAttchMap(maps);
		check.setLabelName("标签名称");
		check.setRemark("备注");
		List<LoanDataCheck> checks = new ArrayList<>();
		checks.add(check);
		
		EnterDataSurvey dataSurvey = new EnterDataSurvey();
		dataSurvey.setAuditOpinions("审批意见");
		dataSurvey.setEnterAndPersonExplains(explains);
		dataSurvey.setEnterAssets(assets);
		dataSurvey.setEnterBankDeposit(bankDeposit);
		dataSurvey.setEnterCars(cars);
		dataSurvey.setEnterCash(cash);
		dataSurvey.setEnterFinanAnals(anals);
		dataSurvey.setEnterIncome(enterIncome);
		dataSurvey.setEnterInterest(enterInterest);
		dataSurvey.setEnterLoan(enterLoan);
		dataSurvey.setEnterPayableFinan(enterPayableFinan);
		dataSurvey.setEnterRiskAnalsis(analsis2);
		dataSurvey.setLoanDataChecks(checks);
		dataSurvey.setLoanId(1);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("json", dataSurvey);
		System.out.println(jsonObject);
		
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
		//资产备用文件
				EnterAssets assets = new EnterAssets();
				List<Map<String, String>> lists = new ArrayList<>();
				Map<String, String> maps = new HashMap<>();
				maps.put("企业名称", "北京XXXXX有限公司");
				lists.add(maps);
				assets.setAssetsMaps(lists);
				
				//企业及相关人员基本情况
				EnterAndPersonExplain andPersonExplain = new EnterAndPersonExplain();
				andPersonExplain.setDetails("详细情况");
				andPersonExplain.setLoanEnterDataType(LoanEnterDataType.BOOK_KEEPER);
				List<EnterAndPersonExplain> explains= new ArrayList<EnterAndPersonExplain>();
				explains.add(andPersonExplain);
				
				//银行存款
				EnterBankDeposit bankDeposit = new EnterBankDeposit();
				bankDeposit.setDepositMaps(lists);
				bankDeposit.setRemark("备注");
				
				//目前履约车及相关收入与测算
				EnterCars cars = new EnterCars();
				cars.setCarMaps(lists);
				cars.setRemark("备注");
				
				//现金
				EnterCash cash = new EnterCash();
				cash.setCashMaps(lists);
				cash.setRemark("备注");
				
				//公司财务状况分析
				EnterFinanAnal anal = new EnterFinanAnal();
				anal.setInfo("信息");
				anal.setRemark("备注");
				anal.setEnterFinaPosition(EnterFinaPosition.DEBT);
				List<EnterFinanAnal> anals = new ArrayList<>();
				anals.add(anal);
				
				//收入计算电核记录
				EnterIncome enterIncome = new EnterIncome();
				enterIncome.setInfo("信息");
				enterIncome.setRemark("备注");
				
				//结息
				EnterInterest enterInterest = new EnterInterest();
				enterInterest.setInterestMaps(lists);
				enterInterest.setRemark("备注");
				
				//短/长期借款类审核文件
				EnterLoan enterLoan = new EnterLoan();
				enterLoan.setLoanMaps(lists);
				enterLoan.setRemark("备注");
				
				//应付财务审核文件
				EnterPayableFinan enterPayableFinan = new EnterPayableFinan();
				enterPayableFinan.setFinanMaps(lists);
				enterPayableFinan.setRemark("备注");
				
				//风险分析及意见
				EnterRiskAnalsis analsis = new EnterRiskAnalsis();
				analsis.setInfo("信息");
				analsis.setLoanRiskAnalsis(LoanRiskAnalsis.COMPANY_ADVANTAGE);
				analsis.setRemark("备注");
				List<EnterRiskAnalsis> analsis2 = new ArrayList<>();
				analsis2.add(analsis);
				
//				资料审核
				LoanDataCheck check = new LoanDataCheck();
				check.setAttchMap(maps);
				check.setLabelName("标签名称");
				check.setRemark("备注");
				List<LoanDataCheck> checks = new ArrayList<>();
				checks.add(check);
				
				EnterDataSurvey dataSurvey = new EnterDataSurvey();
				dataSurvey.setAuditOpinions("审批意见");
				dataSurvey.setEnterAndPersonExplains(explains);
				dataSurvey.setEnterAssets(assets);
				dataSurvey.setEnterBankDeposit(bankDeposit);
				dataSurvey.setEnterCars(cars);
				dataSurvey.setEnterCash(cash);
				dataSurvey.setEnterFinanAnals(anals);
				dataSurvey.setEnterIncome(enterIncome);
				dataSurvey.setEnterInterest(enterInterest);
				dataSurvey.setEnterLoan(enterLoan);
				dataSurvey.setEnterPayableFinan(enterPayableFinan);
				dataSurvey.setEnterRiskAnalsis(analsis2);
				dataSurvey.setLoanDataChecks(checks);
				dataSurvey.setLoanId(1);
				
	}
}
