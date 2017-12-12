package com.java1234.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 系统交互
 * @Title: SystemTalk.java 
 * @Package com.java1234.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuth 
 * @date Oct 20, 2017 2:46:10 PM 
 * @version V1.0
 */
public class SystemTalk {
	public static final String SELECTUSER = "SELECT_USERS";
	/**
	 * 白名单删除
	 */
	public static final String DELETE = "DISABLE_WHITE";
	/**
	 * 白名单添加
	 */
	public static final String INSERT = "ENABLE_WHITE";
	/**
	 * 白名单查询
	 */
	public static final String SELECT = "SELECT_WHITE";
	/**
	 * 白名单修改
	 */
	public static final String UPDATE = "UPDATE_WHITE";
	/**
	 * 数据
	 */
	public static final String PARAM = "parameter";
	/**
	 * 返回数据
	 */
	public static final String RESULT = "result";
	/**
	 * 灰度升级添加
	 */
	public static final String INGRAY = "INSERT_GRAY";
	
	/**
	 * 删除灰度升级用户
	 */
	public static final String DELGRAY = "DELETE_GRAY";
	
	/**
	 * 灰度升级用户查询
	 */
	public static final String SELGRAY = "SELECT_GRAY";

	/**
	 * 用户账单查询
	 */
	public static final String SELEUSERBILL = "SELECT_NOTIFYTRADE";
	
	/**
	 * 修改版本号
	 */
	public static final String UPDATEVERSION = "UPDATE_VERSION";
	
	/**
	 * 灰度版本信息
	 */
	public static final String SELECTGRAYVERSION = "SELECT_GRAYVERSION";
	
	/**
	 * 用户账单查询（修改表后的）
	 */
	public static final String SELECTTRADEPAY = "SELECT_TRADEPAY";

   /**
    * 外部连接传参数
    * @param array
    */
	public static void postForm(String service,String key, String value) {  
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(PropertiesUtil.getValue(PropertiesUtil.CONNECTION_));  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("service", service));  
        
        //传输数据
//        Map<String, String> map = new HashMap<String, String>();
//        for(int i = 0; i < 10; i++){
//            map.put("userId", "1706120002188" + i);
//        	map.put("isHide", "0");
//            map.put("loginName", "1320213672" + i);
//        }
//        map.put("loginName", "13980856427");
//    	map.put("isHide", "1");
//    	List<String> list = new ArrayList<>();
//        list.add("13202136729");
        if(StringUtils.isNotEmpty(key)){
            formparams.add(new BasicNameValuePair(key, value));  
        }
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	System.out.println("\n " + "\n");
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
}
