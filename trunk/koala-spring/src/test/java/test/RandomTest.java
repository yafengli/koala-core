/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.koala.util.FormatUtil;

/**
 *
 * @author Administrator
 */
public class RandomTest {

    @Test
    public void testRandom() {
        try {
            RandomAccessFile raf = new RandomAccessFile("f:/test", "rw");
            raf.writeLong(12L);
            raf.writeLong(34L);

            raf.seek(0);
            System.out.printf("[%s,%s]\n", raf.readLong(), raf.readLong());


            long lg=Long.parseLong("4c2c2f50",16);
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(lg);
            cal.add(Calendar.YEAR,40);
            Date d=new Date(lg);
            System.out.println(FormatUtil.getFormatUtil().getTime(cal.getTime(),FormatUtil.yyyy_MM_dd_HH_mm_ss));
            System.out.println(FormatUtil.getFormatUtil().getTime(d,FormatUtil.yyyy_MM_dd_HH_mm_ss));
            Date dt=FormatUtil.getFormatUtil().getDate("2010-07-01 14:01:52",FormatUtil.yyyy_MM_dd_HH_mm_ss);
            System.out.printf("[%s,%s,%s]",dt.getTime(),lg,(dt.getTime()-lg)/(3600*1000*24)/365);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
