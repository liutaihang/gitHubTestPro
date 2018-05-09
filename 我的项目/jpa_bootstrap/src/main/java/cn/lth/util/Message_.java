package cn.lth.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Component
public class Message_ {

    private Properties properties;

    public Message_() throws IOException {
        File file = new File("message.properties");
        InputStream stream = Message_.class.getClassLoader().getResourceAsStream("message.properties");
        properties = new Properties();
        properties.load(stream);
    }

    public Object get(String code){
        return  properties.get(code);
    }
}
