package test.test;

import org.junit.Test;
import org.koala.util.DESPlus;

import javax.crypto.Cipher;

public class DESTest {
    public static final String test = "Hello World,are kidding me!!~~";
    public static final String keyStr = "12345678";
    private DESPlus des = null;

    public DESTest() {
        try {
            des = new DESPlus(keyStr);//默认密钥
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecAndEnc() {
        try {
            //  DESPlus des = new DESPlus("FUCK");// 自定义密钥
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encrypt(test));
            System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWork() {
        try {
            byte[] bs = des.getEncryptCipher().doFinal(test.getBytes());
            System.out.println(bs.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWork2() {
        try {
            Cipher cp = Cipher.getInstance("DES");
            cp.init(Cipher.ENCRYPT_MODE, des.creatKey(keyStr));
            byte[] bs = cp.doFinal(test.getBytes());
            System.out.println(bs.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
