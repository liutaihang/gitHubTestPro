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
        System.err.println(testInterface.saySomething("dubboCustomer"));
        System.err.println(testInterface.getList());
    }
}
