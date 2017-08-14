package liu.BasePro;

/**
 * Unit test for simple App.
 */
public class AppTest implements Runnable{

	private Integer num;
	private Integer num1;
	private Thread t;
	private String tName;
	
	public AppTest(Integer num, Integer num1, String tName) {
		this.num = num;
		this.num1 = num1;
		this.tName = tName;
	}
	
	@Override
	public void run() {
			new Count().count(num, num1);
	}
	public void start(){
		System.out.println("creat " + tName + " Thread!");
		if(t == null){
			t = new Thread(this, tName);
			t.start();
		}
	}
	
   
	public static void main(String[] args) {
		Thread thread = new Thread(new AppTest(10, 10, "newstart"));
		thread.start();
	}
}

class Count{
	public synchronized void count(Integer num, Integer num1){
			System.out.println("[" + (num + num1 + Math.random()*10) + "]" + Thread.currentThread().getName());
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
