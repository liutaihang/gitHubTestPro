package liu.DubboCustomer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import liu.common.TestInterface;

/**
 * Hello world!
 *
 */
public class DubboTest {
    public static void main( String[] args ){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Customer_serviceImpl.xml");
        TestInterface testInterface = (TestInterface) context.getBean("testInterface");
        testInterface.saySomething("dubboCustomer");
        System.out.println(testInterface.getList());
    }
}
