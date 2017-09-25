package mevenPro.Sington;

public class SingtonHunger {

	private static SingtonHunger instance = null;
	
	private SingtonHunger(){}
	
	public static SingtonHunger getInstance(){
		if(instance == null){
			instance = new SingtonHunger();
		}
		return instance;
	}
}
