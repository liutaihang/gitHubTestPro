package cn.lth.threadDemo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DemoTest {
	public static void main(String[] args) {
		Thread obj1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("obj1 -- > 运行了！");
			}
		});

		Thread thread = new Thread(new DemoRun(obj1));
		obj1.run();
		thread.run();
		int second = 0;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		executorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("延迟0秒，一秒执行一次！");
			}
		}, 0, 1, TimeUnit.SECONDS);
		
		new DemoRun(new DemoInterface() {
			@Override
			public void demos(Object... objects) {
				
			}
		});
	}
}
