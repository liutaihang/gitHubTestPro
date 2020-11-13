package cn.lth;

import cn.lth.dto.SysUser;
import cn.lth.service.DemoService;
import cn.lth.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebJapApplication.class})
public class WebJapApplicationTests {

    @Autowired
    private DemoService demoService;

    @Autowired
    private SysUserService sysUserService;
    @Test
	public void contextLoads() {
        SysUser user = new SysUser("liusir", "123", "123");
        System.out.println(sysUserService.existsUserName("liusir"));
    }

	public static void main(String[] args) {
        SysUser s = new SysUser("lius", "123", "ss");
        System.out.println(s.toString());
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
