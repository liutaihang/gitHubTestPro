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
        System.out.println(demoService.findAll().size());
        new Thread(() -> System.out.println(demoService.findAll().size())).start();
        new Thread(() -> System.out.println(demoService.findAll().size())).start();
        new Thread(() -> System.out.println(demoService.findAll().size())).start();

	}

	public static void main(String[] args) {
        new Thread(() -> {

        }).start();
	}

}
