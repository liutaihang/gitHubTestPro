package liu.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Other {

	public void say(String content){
		System.out.println("【" + content + "】");
	}
	
	public static void main(String[] args) {
		int num = 10;
		Thread thread = new Thread(new threadtest(num));
		Thread thread2 = new Thread(new threadtest(num));
		thread.start();
		thread2.start();
	}
	
	
}
