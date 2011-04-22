package org.koala.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: liyafeng
 * Date: 2007-12-21
 * Time: 16:32:29
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTool {
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";

    public static final String _yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd-HH-mm-ss";

    public static final String yyyyMMddHH = "yyyyMMddHH";

    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

    public static final String yyyyMMdd = "yyyyMMdd";

    public CalendarTool() {
    }

    public String getStrTime(Date date, String formatstr) {
        DateFormat format=new SimpleDateFormat(formatstr);
        return format.format(date);
    }

    public String getStrNumber(Double d,String formatstr)
    {
        NumberFormat format=new DecimalFormat(formatstr);
        return format.format(d);
    }
}
