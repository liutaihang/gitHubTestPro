package cn.lth;

import cn.lth.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebJapApplicationTests {

    @Autowired
    private DemoService demoService;
    @Test
	public void contextLoads() {
        new Thread(new Ables(demoService)).start();

	}

	public static void main(String[] args) {
	}
 class Ables implements Runnable{
     private DemoService demoServices;
     Ables(DemoService demoService){
         this.demoServices = demoService;
     }
     @Override
     public void run() {
         System.err.println("ssss");
         System.err.println(demoServices.findAll().size());
     }
 }

}
