package mevenPro.Sington;

public class SingtonLazy {
	private static SingtonLazy instance  = new SingtonLazy();
	
	private SingtonLazy(){}
	
	public static SingtonLazy getInstance(){
		return instance;
	}
}
