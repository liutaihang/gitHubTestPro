package liu.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
/**
 * demo Aop
 * @author Administrator
 *
 */
@Aspect
public class SpringAopTest {

	
	public SpringAopTest() {
	}
	
//	@Before("justtest()")
	public void beforeTest(){
		System.out.println("前置增强");
	}
	
	@AfterReturning(pointcut = "execution(* *.say(..))")
	public void afterTest(JoinPoint point) throws InstantiationException, IllegalAccessException{
		System.out.println(point.getTarget().getClass().getName() + "." + point.getSignature().getName());
		SourceLocation location = point.getSourceLocation();
		System.out.println("后置增强" + "【" + point.getKind() + "】" + location.getWithinType().newInstance());
	}
	
	@Around("justtest()")
	public void aroundTest(JoinPoint point) throws Throwable{
		Class c = point.getTarget().getClass();
		Method []methods = c.getMethods();
		Method mt = getMethod(point, methods);
		c.getName();
		System.out.println(c.getName() + "." + mt.getName());
//		System.out.println(point.getArgs()[0].getClass().getName() + point.proceed());
		System.out.println("环绕增强_调用方法 - - >" + point.getSignature().getName() + "{" + point.getTarget().getClass().getName() + "}");
		
	}

	private Method getMethod(JoinPoint point, Method[] methods) {
		Method mt = null;
		for (Method method : methods) {
			if(method.getName().equals(point.getSignature().getName())){
				mt = method;
				return mt;
			}
		}
		return mt;
	}
	
	@Pointcut("execution(* *.getAll(..)")
	public void justtest(){
	}
}
