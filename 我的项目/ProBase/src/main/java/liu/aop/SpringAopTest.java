package liu.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SpringAopTest {

	
	public SpringAopTest() {
	}
	
	@Before("justtest()")
	public void beforeTest(){
		System.out.println("前置增强");
	}
	
	@AfterReturning("justtest()")
	public void afterTest(){
		System.out.println("后置增强");
	}
	
//	@Around("justtest()")
	public void aroundTest(){
		System.out.println("环绕增强");
	}
	
	@Pointcut("execution(* liu.dao.mongo.PeopleDao.findAll())")
	public void justtest(){
	}
}
