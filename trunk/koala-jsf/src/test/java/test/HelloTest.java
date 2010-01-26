package test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.lang.reflect.Method;
import javax.mail.internet.MimeUtility;
import java.util.zip.CRC32;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-12-21
 * Time: 16:43:28
 * To change this template use File | Settings | File Templates.
 */
@TestClass(id = "#Fuck#")
public class HelloTest {
    @TestMethod(id = "#you#", names = {"one", "two", "three"}, name = "#Hello#")
    public void sayHello() {
        System.out.println("say Hello!");
    }

    public static void main(String[] args) {
        try {
            HelloTest ht = new HelloTest();
            boolean isFlag = ht.getClass().isAnnotationPresent(TestClass.class);
            if (isFlag) {
                TestClass t1 = ht.getClass().getAnnotation(TestClass.class);
                System.out.printf("[%s]\n", t1.id());
            }
            Method md = ht.getClass().getMethod("sayHello");
            boolean isMd = md.isAnnotationPresent(TestMethod.class);
            if (isMd) {
                TestMethod tm1 = md.getAnnotation(TestMethod.class);
                System.out.printf("[%s]\n", tm1.id());
                System.out.printf("[%s]\n", tm1.name());
                for (String name : tm1.names()) {
                    System.out.printf("%s ", name);
                }
                System.out.println();
            }
            String envalue="1 1217560296 wyb 0 0 0 9999 192.168.0.180.2238 1.2.3.4.80 - 1.2.3.4/";
            BASE64Encoder encoder=new BASE64Encoder();
            CRC32 crc32=new CRC32();


            crc32.update(encoder.encode(envalue.getBytes()).getBytes());
            
            System.out.printf("[en:%s][crc:%s]\n",encoder.encode(envalue.getBytes()),crc32.getValue());
            String decvalue="e9bef79d00022165YakawIYxldm0Th;ZZivYp:Vof:pl7:ONfLpH7oVDIx7tTOOd1OCrVlTsUnr2R00WERct;LisHOfrLY0dYLfrjYVNJHr";
            BASE64Decoder decoder=new BASE64Decoder();
            System.out.println("[de:]"+new String(decoder.decodeBuffer(decvalue)));
            System.out.println("[mi]"+MimeUtility.encodeText(envalue));
            System.out.println("[mi]"+MimeUtility.encodeText(envalue,"UTF-8","Base64"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
