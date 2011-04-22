/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.RandomAccessFile;
import org.junit.Test;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
