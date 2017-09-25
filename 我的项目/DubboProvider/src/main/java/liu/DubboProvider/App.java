package liu.DubboProvider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import liu.common.TestInterface;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Provider_dubbo.xml");
        context.start();
        System.in.read();
    }
}
