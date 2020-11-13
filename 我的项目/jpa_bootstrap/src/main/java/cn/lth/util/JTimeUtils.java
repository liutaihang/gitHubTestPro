package cn.lth.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JTimeUtils {

    public static final String format_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String format_2 = "yyyyMMddHHmmss";


    public static String getTimestamp(String format, Date date){
        if (StringUtils.isEmpty(format)) {
            format = format_2;
        }
        if(null == date){
            date = new Date();
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String getTimestamp(String format){
        return getTimestamp(format, null);
    }

    public static String getTimestamp(){
        return getTimestamp(null, null);
    }

    public static void main(String[] args) {
        System.out.println(getTimestamp());
    }
}
