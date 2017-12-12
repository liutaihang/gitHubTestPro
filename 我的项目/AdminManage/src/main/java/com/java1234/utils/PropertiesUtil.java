package com.java1234.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件工具包
 * @author liuth
 */
public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
	private static final String FILENAME = "system.properties";
	public static final String CONNECTION_ = "connection_url";
	public static final String APKUPLOAD_ = "apkUpload_url";
	
	public Properties getProperty() throws IOException{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILENAME);
		Properties properties = new Properties();  
        try{  
            properties.load(inputStream);  
        }catch (IOException ioE){  
			System.err.println("PropertiesUtil Message:{加载文件异常，文件路径：" + FILENAME + "}");
            ioE.printStackTrace();  
        }finally{  
				inputStream.close();
        }  
        return properties;
	}
	
	public static String getValue(String key){
		 Properties properties = null;
			try {
				properties = new PropertiesUtil().getProperty();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 return properties.getProperty(key);
	}
}
