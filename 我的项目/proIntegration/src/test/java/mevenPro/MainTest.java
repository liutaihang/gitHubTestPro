package mevenPro;

import mevenPro.Sington.SingtonHunger;
import mevenPro.Sington.SingtonLazy;

public class MainTest {

	public static void main(String[] args) {
		SingtonLazy lazy = SingtonLazy.getInstance();
		SingtonLazy lazy2 = SingtonLazy.getInstance();
		if(lazy.hashCode() == lazy2.hashCode()){
			System.out.println("false");
		}else{
			System.out.println("true");
		}
		
		
		SingtonHunger hunger = SingtonHunger.getInstance();
		SingtonHunger hunger2 = SingtonHunger.getInstance();
		if(hunger.hashCode() == hunger2.hashCode()){
			System.out.println("false");
		}else{
			System.out.println("true");
		}
		
	}
}
