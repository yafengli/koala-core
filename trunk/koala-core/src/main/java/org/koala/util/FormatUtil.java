package org.koala.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    public static final String _yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd-HH-mm-ss";
    public static final String yyyyMMddHH = "yyyyMMddHH";
    public static final String yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMM = "yyyyMM";
    public static final String yyyy_MM = "yyyy_MM";
    public static final String format_three = "#.##";

    private FormatUtil() {
    }
    private static FormatUtil du = null;

    public static FormatUtil getFormatUtil() {
        if (du == null) {
            du = new FormatUtil();
        }
        return du;
    }

    public String getTime(Date date, String datePattern) {
        DateFormat format = new SimpleDateFormat(datePattern);
        return format.format(date);
    }

    public String getStrNumber(double number, String numberPattern) {
        NumberFormat format = new DecimalFormat(numberPattern);
        return format.format(number);
    }

    public String getStrNumber(long number, String numberPattern) {
        NumberFormat format = new DecimalFormat(numberPattern);
        return format.format(number);
    }

    public String getStrNumber(Object number, String numberPattern) {
        NumberFormat format = new DecimalFormat(numberPattern);
        return format.format(number);
    }

    public Date getDate(String dateStr, String datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Date d = null;
        try {
            d = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
}
