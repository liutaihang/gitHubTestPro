package com.tw.liu.constructpro.jdbcUtils;

import cn.kkou.common.util.LanguageUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class JdbcConnection {
    private static Statement statement = null;
    private static Connection connection;
    public static void initStatement(String url, String userName, String pwd){
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, pwd);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Blob get(String sql){
        Blob blob = null;
        if(null != statement){
            try {
                ResultSet resultSet = statement.executeQuery(sql);
                if(connection == null){
                    return null;
                }
                if(resultSet.next()){
                    blob = resultSet.getBlob("BookImage");
                }
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return blob;
    }

    public static ResultSet getBySql(String url, String pwd, String userName, String sql) throws SQLException {
        initStatement(url, userName, pwd);
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static void main(String[] args) throws SQLException {
//        initStatement("jdbc:mysql://192.168.1.130:3306/literature?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8", "root", "root");
//        Blob blob = get("SELECT BookImage FROM `book_stock` WHERE id = 30295");
//        try {
//            InputStream inputStream = blob.getBinaryStream();
//            File file = new File("C:\\tmp\\template\\tem.png");
//            file.createNewFile();
//
//            byte []buffer = new byte[1024];
//
//            FileOutputStream stream = new FileOutputStream(file);
////            stream.write(blob.getBytes(1, (int)blob.length()));
//            if(-1 != inputStream.read(buffer)){
//               stream.write(buffer);
//            }
//
//            String encode = new BASE64Encoder().encode(blob.getBytes(1, (int)blob.length()));
//            System.out.println(encode);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        int n = 0;
//        for (int i = 0; i < 10; i++) {
//            System.out.println("before break");
//            boolean is = true;
//            for (int j = 0; j < 5; j++) {
//                if(i % 2 == 1){
//                    is = false;
//                }
//            }
//            if(is){
//                System.out.println("break after(" + (i) + ")(" + i%2 + ")");
//            }
//        }

        long startTime = System.currentTimeMillis();

        ResultSet bySql = getBySql("jdbc:mysql://192.168.1.130:3306/literature?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8"
                , "root", "root", "SELECT id,book_name FROM book_stock");

        System.out.println("select -- end [" + (System.currentTimeMillis() - startTime) + "]");
        List<Map> bookNames = Lists.newArrayList();
        List<String> tems = Lists.newArrayList();

        long forBeg = System.currentTimeMillis();
        while (bySql.next()){
            HashMap map = Maps.newHashMap();
            map.put("bookName", bySql.getString("book_name"));
            map.put("id", bySql.getString("id"));
            bookNames.add(map);
        }
        System.out.println("forBeg -- end [" + (System.currentTimeMillis() - forBeg) + "]");
        statement.close();

        StringBuffer str = new StringBuffer();


        long updateforBeg = System.currentTimeMillis();
        bookNames.stream().forEach(tem -> {
            String bookName = converterToFirstSpell(tem.get("bookName").toString());
            str.append(bookName).append(",");
            Statement execute = null;

            long updateoneb = System.currentTimeMillis();
            try {
                connection.setAutoCommit(false);
                execute = connection.createStatement();
                execute.executeUpdate("update book_stock set search_key = '"
                        + bookName + "' WHERE id = '" + tem.get("id").toString() + "'");
                connection.commit();
                execute.close();
                System.out.println("updateoneb -- end [" + (System.currentTimeMillis() - updateoneb) + "]");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        System.out.println("forBeg -- end [" + (System.currentTimeMillis() - updateforBeg) + "]");
        System.out.println(str.toString());



    }

    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < nameChar.length; ++i) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName = pinyinName + PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            } else {
                pinyinName = pinyinName + nameChar[i];
            }
        }

        return pinyinName;
    }
}
