package liu.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import liu.dao.Other;
/**
 * demo 
 */
public class BeforeTestAdvice implements MethodBeforeAdvice,AfterReturningAdvice{

	
	
	public BeforeTestAdvice() {
		super();
	}


	@Override
	public void before(Method method, Object[] objects, Object object) throws Throwable {
		System.out.println(method.getClass().getName() + "." + method.getName() + "()  before");
	}


	@Override
	public void afterReturning(Object object, Method method, Object[] objects, Object object2) throws Throwable {
		System.out.println(object + method.getClass().getName() + "." + method.getName() + "()  after");
	}
	
	public static void main(String[] args) {
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(new Other());
//		factory.addAdvice(new BeforeTestAdvice());
		factory.addAdvice(new AroundTestAdvice());
		
		Other other = (Other) factory.getProxy();
		other.say(factory.getClass().getName());
	}
}

 class AroundTestAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		before(invocation);
		Object result = invocation.proceed();
		after(invocation);
		
		return result;
	}
	 private void before(MethodInvocation invocation){
		 System.out.println(invocation.getMethod().getName() + "()   before");
	 }
	 
	 private void after(MethodInvocation invocation){
		 System.out.println(invocation.getMethod().getName() + "()   after");
	 }
 }
