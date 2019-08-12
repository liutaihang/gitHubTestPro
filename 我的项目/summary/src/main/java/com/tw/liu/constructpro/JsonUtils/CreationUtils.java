package com.tw.liu.constructpro.JsonUtils;

import com.tw.liu.constructpro.entity.Book;
import com.tw.liu.constructpro.entity.SysUser;
import com.tw.liu.constructpro.security.SecurityUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;

public class CreationUtils {
    public static String getJsonData(String filepath) {
        File file = new File(filepath);
        String returns = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                returns += (temp == null ? "" : temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returns;
    }

    public static String getWordTitleContent(String path) {
        File word = new File(path);
        OutputStreamWriter outputStreamWriter = null;
        try {
            InputStream inputStream = new FileInputStream(word);
            HWPFDocument document = new HWPFDocument(inputStream);
            Range range = document.getRange();
            String imgpath = "c:/temp/img";
            File img = new File(imgpath);
            if (img.exists()) {
                img.mkdirs();
            }

            InputStream inputStream1 = new FileInputStream(word);
            XWPFDocument xwpfDocument = new XWPFDocument(inputStream1);
            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(img));

            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream("c:temp/demohtml.html"), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(xwpfDocument, outputStreamWriter, options);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static Object invokeGetter(String fieldName, Object target, Class clazz){
        Object obj = null;
        try {
            obj = ReflectionUtils.invokeMethod(clazz.getMethod("get" + StringUtils.capitalize(fieldName)), target);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String jsonData = CreationUtils.getJsonData("C:/Users/￥/Desktop/json.json");
//        Object parse = JSON.parse(jsonData);

//        getWordTitleContent("C:\\Users\\￥\\Desktop\\肉山羊舍饲养殖场建设（2017.12.04）.doc");
//        System.out.println(jsonData);
//        Book book = new Book();
//        book.setBookNo("asdfasd");
//        book.setId("123");
//        book.setValue("qewe");

        SysUser sysUser = new SysUser("ss", "name");
        Class<?> aClass = Class.forName(SecurityUser.class.getName());

        try {
            Object o = SummaryUtils.converTo(sysUser, SecurityUser.class);
            System.out.println(o.getClass().getName());
            System.out.println(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
//        cn.kkou.common.util.ReflectionUtils.invokeGetter()
//        Class clazz = book.getClass();
//        Field[] declaredFields = clazz.getDeclaredFields();
//        for (Field f: declaredFields) {
//            f.setAccessible(true);
//            System.out.println(StringUtils.capitalize(f.getName()));
//
//            try {
//                Object value = ReflectionUtils.invokeMethod(clazz.getMethod("get" + StringUtils.capitalize(f.getName())), book);
//                System.out.println(value);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(SysUser.class.getMethods()[0].getParameterTypes()[1].getName());

//        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//        System.out.println(runtimeMXBean.getVmVendor());

        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", SysUser.class);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            System.out.println(writeMethod.getName());

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }
}
