package liu.dao;
public class threadtest implements Runnable{

	public threadtest( int num) {
		this.num = num;
	}
		private int num = 0;
		@Override
		public void run() {
			for(int i = 0; i < num; i++){
				num = num -1;
				System.out.print(num + "   ");
			}
		}
		
	}