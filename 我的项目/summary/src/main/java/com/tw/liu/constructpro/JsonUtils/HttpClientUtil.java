package com.tw.liu.constructpro.JsonUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public  class HttpClientUtil {
    private static CloseableHttpClient httpClient= HttpClients.createDefault();

    public static String doGet(String url, Map<String, Object> map) throws Exception {

        // 声明URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);

        // 判断参数map是否为非空
        if (map != null) {
            // 遍历参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 设置参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 2 创建httpGet对象，相当于设置url请求地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        // 3 使用HttpClient执行httpGet，相当于按回车，发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);


        HttpEntity entity = response.getEntity();
        String content=null;
        if (entity != null) {
           content = EntityUtils.toString(entity);
           System.out.println(content);
        }

        return content;
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
  /*  public HttpResult doGet(String url) throws Exception {
        HttpResult httpResult = this.doGet(url, null);
        return httpResult;
    }*/

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = httpClient.execute(httpPost);


        // 返回结果
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }


    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> map, MultipartFile filePath, String fileKey) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null || !filePath.isEmpty()) {
            // 声明存放参数的List集合
            MultipartEntityBuilder params = MultipartEntityBuilder.create()
                    .setCharset(Charset.forName("UTF-8"));
                params.addBinaryBody(fileKey, filePath.getInputStream(), ContentType.MULTIPART_FORM_DATA, filePath.getOriginalFilename());

            // 遍历map，设置参数到list中
            if(map != null){
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    params.addTextBody(entry.getKey(), entry.getValue().toString(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
                }
            }

            // 创建form表单对象
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpEntity formEntity = params.build();
            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 返回结果
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    public static String doPost(String url, Map<String, Object> map, File filePath, String fileKey) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null || !filePath.exists()) {
            // 声明存放参数的List集合
            MultipartEntityBuilder params = MultipartEntityBuilder.create()
                    .setCharset(Charset.forName("UTF-8"));
            params.addBinaryBody(fileKey, new FileInputStream(filePath), ContentType.MULTIPART_FORM_DATA, filePath.getName());

            // 遍历map，设置参数到list中
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                if(null == entry.getKey() || null == entry.getValue()){
                    break;
                }
                params.addTextBody(entry.getKey(), entry.getValue().toString(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }

            // 创建form表单对象
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpEntity formEntity = params.build();
            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 返回结果
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    public static InputStream doPostGetPDF(String url, Map<String, String> map) throws IOException {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return response.getEntity().getContent();
    }


}
