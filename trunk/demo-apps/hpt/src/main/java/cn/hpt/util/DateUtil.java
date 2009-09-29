package cn.hpt.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2009-9-29
 * Time: 10:38:23
 *
 * @version 1.0
 * @authtor YaFengLi
 */
public class DateUtil {
    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";


    public static String format(Date date, String pattern) {
        DateFormat factory = new SimpleDateFormat(pattern);
        return factory.format(date);
    }

    public static Date parse(String val, String pattern) throws Exception {
        DateFormat factory = new SimpleDateFormat(pattern);
        return factory.parse(val);
    }
}
