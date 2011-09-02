package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class PcapIOTest {

    public static Long FILE_HEAD_LENGTH = 24L;
    private ByteBuffer data_head_buffer = ByteBuffer.allocate(16);

    @Test
    public void testRead() {
        FileInputStream fin = null;
        long position = FILE_HEAD_LENGTH;
        try {
            fin = new FileInputStream(new File("F:/Google/koala/koala-test/src/test/java/test/test.pcap"));
            FileChannel fc = fin.getChannel();
            for (int i = 0; i < 2; i++) {
                int len = read(fc, position);
                System.out.printf("#len=%d,position=%d\n", len, position);
                position += (16 + len);
            }
            ByteBuffer data_buffer = ByteBuffer.allocate(96+16);
            fc.read(data_buffer, 136);
            System.out.println("^" + bytesToHexString(data_buffer.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int read(FileChannel fc, long position) throws Exception {
        int i = fc.read(data_head_buffer, position);
        data_head_buffer.order(ByteOrder.LITTLE_ENDIAN);
        int len = data_head_buffer.getInt(8);
        ByteBuffer data_buffer = ByteBuffer.allocate(len+16);
        fc.read(data_buffer, position);
        System.out.print("$" + bytesToHexString(data_head_buffer.array()));
        System.out.print("@" + bytesToHexString(data_buffer.array()));
        data_head_buffer.clear();
        return len;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        int block = 0, line = 0;
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            if (++block >= 2) {
                stringBuilder.append(" ");
                block = 0;
            }
            if (++line >= 16) {
                line = 0;
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
