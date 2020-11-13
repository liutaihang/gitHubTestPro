package cn.lth.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class PropertiesUtils {
    private static Properties properties;
    private static PropertiesUtils instance;

    public static PropertiesUtils getInstance(){
        if(instance == null){
            try {
                log.info("create propertiesUtils instance");
                instance = new PropertiesUtils();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public PropertiesUtils() throws IOException {
        InputStream stream = PropertiesUtils.class.getClassLoader().getResourceAsStream("message.properties");
        properties = new Properties();
        properties.load(stream);
    }

    public String get(String code){
        return  String.valueOf(properties.get(code));
    }
}
