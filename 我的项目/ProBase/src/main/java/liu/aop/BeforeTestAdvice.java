package liu.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import liu.dao.Other;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
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
	
	public static void main(String[] args) throws IOException {
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(new redisT());
//		factory.addAdvice(new BeforeTestAdvice());
		factory.addAdvice(new AroundTestAdvice());
		
		redisT other = (redisT) factory.getProxy();
//		other.redizz();
		other.redizzz();
	}
}

 class AroundTestAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long curr = System.currentTimeMillis();
		before(invocation, curr);
		Object result = invocation.proceed();
		after(invocation, curr);
		
		return result;
	}
	 private void before(MethodInvocation invocation, long curr){
		 System.out.println(invocation.getMethod().getName() + "()   before >>time>" + curr);
	 }
	 
	 private void after(MethodInvocation invocation, long curr){
		 long last = System.currentTimeMillis();
		 System.out.println(invocation.getMethod().getName() + "()   after" + (last - curr));
	 }
 }

 class redisT{
	 public void redizz() throws IOException{
		 Jedis jedis = new Jedis("127.0.0.1", 6379);
		 Response<Map<String, String>> res = null;
		 Response<List<String>> res1 = null;
		 Response<String> res2 = null;
		 Pipeline pipeline = jedis.pipelined();
		 pipeline.select(1);
		 res2 = pipeline.get("TEL_ACTIVITY");
		 pipeline.close();
		 System.out.println(res2.get());
	 }
	 public void redizzz() throws IOException{
		 Jedis jedis = new Jedis("127.0.0.1", 6379);
		 jedis.select(1);
		 System.out.println(jedis.get("TEL_ACTIVITY"));
	 }
 }