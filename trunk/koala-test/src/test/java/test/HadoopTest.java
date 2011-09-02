package test;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class HadoopTest {

//    @Test
    public void testString() {
        String str = "\u0041\u00d1\u6771\uD801\uDC00";
        System.out.println("string:" + str.length());
        System.out.println("string:" + str.charAt(2));
    }

//    @Test
    public void testText() {
        Text t = new Text("\u0041\u00d1\u6771\uD801\uDC00");
        System.out.println("text:" + t.getLength());
        System.out.println("text:" + t.charAt(1));
        System.out.println("text:" + t.charAt(3));
        System.out.println("text:" + t.charAt(5));
        System.out.println("text:" + t.charAt(7));
        System.out.println("text:" + t.charAt(9));

        ByteBuffer buffer = ByteBuffer.wrap(t.getBytes(), 0, t.getLength());
        int cp = -1;
        while (buffer.hasRemaining() && (cp = Text.bytesToCodePoint(buffer)) != -1) {
            System.out.println(Integer.toHexString(cp));
        }
        SimpleDateFormat ft = new SimpleDateFormat("d/MMM/yyyy:HH:mm:ss");
        try {
            System.out.println(ft.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testHadoopConf() {
        Configuration conf = new Configuration();
        conf.set("fuck", "you");
        conf.set("hello", "world");
        for (Entry<String, String> entry : conf) {
            System.out.printf("%s,%s\n", entry.getKey(), entry.getValue());
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        for (Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
        System.currentTimeMillis();
    }
}
