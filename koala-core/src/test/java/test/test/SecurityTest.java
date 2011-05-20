package test.test;

import org.junit.Test;
import org.koala.util.MD5Tool;
import org.koala.util.SecurityTool;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Set;

import org.koala.util.BASE64;

public class SecurityTest {

    public static final String plainText = "中环人民Hello World,arekidding me!!~~";

    @Test
    public void testFind() {
        Set<String> set = SecurityTool.findServiceTypes();
        for (String serviceType : set) {
            System.out.println("@" + serviceType);
            Set<String> csets = SecurityTool.findCryptoImpls(serviceType);
            for (String crypto : csets) {
                System.out.println("#" + crypto);
            }
        }
        set = SecurityTool.findCryptoImpls("Cipher");
        for (String name : set) {
            System.out.println("$" + name);
        }
        set = SecurityTool.findCryptoImpls("KeyPairGenerator");
        for (String name : set) {
            System.out.println("#" + name);
        }
    }

    @Test
    public void testMac() {

        try {
            SecretKey key = getKey();
            if (key != null) {
                System.out.println("密钥：" + MD5Tool.getInstance().byteArrayToHexString(key.getEncoded()) + ":" + key.getAlgorithm());
                Mac mac = Mac.getInstance("HmacMD5");
                mac.init(key);
                mac.update(plainText.getBytes());
                System.out.println("MAC:" + MD5Tool.getInstance().byteArrayToHexString(mac.doFinal()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFc() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(512);
            KeyPair kp = kpg.genKeyPair();
            PrivateKey privateKey = kp.getPrivate();
            PublicKey publicKey = kp.getPublic();

            Cipher cp = Cipher.getInstance("RSA");
            cp.init(Cipher.ENCRYPT_MODE, publicKey);

            String str = new String(BASE64.encode(cp.doFinal(plainText.getBytes())));
            System.out.println(str);

            cp.init(Cipher.DECRYPT_MODE, privateKey);
            System.out.println(new String(cp.doFinal(BASE64.decode(str))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKey() {
        try {
//			KeyGenerator kg = KeyGenerator.getInstance("DES");
//			SecretKey key = kg.generateKey();
//			String keyStr = be.encode(key.getEncode());
            String keyStr = "LIMaBAEE99w=";
            SecretKey key = new SecretKeySpec(BASE64.decode(keyStr), "DES");

            Cipher cp = Cipher.getInstance("DES");
            cp.init(Cipher.ENCRYPT_MODE, key);
            int blockSize = cp.getBlockSize();

            ByteBuffer inbuffer = ByteBuffer.wrap(plainText.getBytes());
            inbuffer.flip();
            int outSize = cp.getOutputSize(inbuffer.capacity());
            System.out.println("##" + outSize);
            ByteBuffer outbuffer = ByteBuffer.allocate(outSize);
            cp.update(inbuffer, outbuffer);

            String str1 = new String(BASE64.encode(cp.doFinal()));
            System.out.println("@1=@" + str1);

            String str = new String(BASE64.encode(cp.doFinal(plainText.getBytes())));
            System.out.println(str);

            cp.init(Cipher.DECRYPT_MODE, key);
            byte[] dbs = cp.doFinal(BASE64.decode(str));
            System.out.println(new String(dbs));

            System.out.println("@1=@" + new String(cp.doFinal(BASE64.decode(str1))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SecretKey getKey() {
        SecretKey key = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            key = kg.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}
