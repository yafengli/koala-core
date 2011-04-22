package test.test;

import org.junit.Test;

/**
 * @author liyafeng 这是JPA的测试程序！
 */
public class CRCTest {

    public CRCTest() {
    }

    @Test
    public void test() {
        String name = "18992";
        Long l = Long.decode(name);
        String str = Long.toHexString(l ^ 0x55aa55aa);
        System.out.printf("the name is %s ,the l is %s,the str is %s%n", name, l, str);
    }

    @Test
    public void testhex() {
        String ip="218.94.84.92";
        System.out.printf("the tohex is %s%n", getIpDecode(ip));
    }

    private String getIpDecode(String ip) {
        byte[] b = getIpByteArrayFromString(ip);
        System.out.printf("[b]%d,%d,%d,%d",b[0],b[1],b[2],b[3]);
        String ipstr = toHexString(b);
        System.out.printf("[ipstr]%s",ipstr);
        ipstr = "0x" + ipstr;
        System.out.printf("[ipstr]%s",ipstr);
        ipstr = Long.toHexString(Long.decode(ipstr) ^ 0x55aa55aa);
        System.out.printf("[ipstr]%s",ipstr);
        ipstr = "0x" + ipstr;
        System.out.printf("[ipstr]%s",ipstr);
        String iplong = Long.decode(ipstr).toString();
        System.out.printf("[iplong]%s",iplong);
        return iplong;
    }

    private String toHexString(byte[] buf) {
        int i = 0;
        String s = "";
        for (i = 0; i < buf.length; i++) {
            s = toHex2(buf[i]) + s;
        }
        return s;
    }

    private String toHex2(byte buf) {
        int n = buf >= 0 ? buf : 256 + buf;
        String str = Integer.toHexString(n);
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    private byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
        try {
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
}
