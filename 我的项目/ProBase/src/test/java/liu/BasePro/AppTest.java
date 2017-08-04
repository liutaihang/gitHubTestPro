package liu.BasePro;

import javax.swing.*;

/**
 * Unit test for simple App.
 */
public class AppTest implements Runnable{

	private String name;
	private Integer num;
	private Integer num1;
	public AppTest(String name, Integer num, Integer num1) {
		this.name = name;
		this.num = num;
		this.num1 = num1;
	}
	
	@Override
	public void run() {
			new Count().count(num, num1, name);
	}
   
	public static void main(String[] args) {
		new Thread(new AppTest("t1", 10, 0)).start();
		new Thread(new AppTest("t2", 20, 0)).start();
		
		new Thread(new Runnable() {
			public void run() {
					new Count().count( 30, 0,"t3");
			}
		}).run();
		Thread thread = new myrunable(new AppTest("my", 40, 0));
		thread.setName("name");
		thread.run();
	}
}

class Count{
	public synchronized void count(Integer num, Integer num1, String name){
			System.out.println(name + "[" + (num + num1 + Math.random()*10) + "]" + Thread.currentThread().getName());
	}
}

class myrunable extends Thread{
	public myrunable(Runnable runnable) {
		super(runnable);
	}
	
	@Override
	public void run() {
		System.out.println("my---|");
		super.run();
	}
}
