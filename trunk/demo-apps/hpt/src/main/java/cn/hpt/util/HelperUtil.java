package cn.hpt.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class HelperUtil {

    public static synchronized String createRandomString(int size) {// 随机字符串
        char[] c = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        Random random = new Random(); // 初始化随机数产生器
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c[Math.abs(random.nextInt(10)) % c.length]);
        }
        return sb.toString();
    }

    public static synchronized boolean isMatch(String str, String regEx) {
        Pattern ep = Pattern.compile(regEx);
        return ep.matcher(str).matches();
    }

    public static synchronized String format(Object result, String regEx) {
        NumberFormat format = new DecimalFormat(regEx);
        return format.format(result);
    }
}
