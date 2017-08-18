package liu.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
	
	@Before("justtest()")
	public void beforeTest(){
		System.out.println("前置增强");
	}
	
	@AfterReturning(pointcut = "execution(* *.getAll(..))")
	public void afterTest(JoinPoint point) throws InstantiationException, IllegalAccessException{
		System.out.println(point.getTarget().getClass().getName() + "." + point.getSignature().getName());
		SourceLocation location = point.getSourceLocation();
		System.out.println("后置增强" + "【" + point.getKind() + "】" + location.getWithinType().newInstance());
	}
	
	@Around("justtest()")
	public Object aroundTest(ProceedingJoinPoint point) throws Throwable{
		Class c = point.getTarget().getClass();
		Object obj = null;
		Method []methods = c.getMethods();
		Method mt = getMethod(point, methods);
		c.getName();
		System.out.println(c.getName() + "." + mt.getName());
		System.out.println(obj = point.proceed());
		System.out.println("环绕增强_调用方法 - - >" + point.getSignature().getName() + "{" + point.getTarget().getClass().getName() + "}");
		return obj;
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
	
	@Pointcut("execution(* *.getAll(..))")
	public void justtest(){
	}
}
