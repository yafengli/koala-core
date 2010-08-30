package org.helloworld;

import hellothread.LocalBean;
import org.junit.Test;
import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileTest {

    public class LockRun implements Runnable {

        public void run() {
            tryLock();
        }

        public void lock() {            
            FileChannel fc = null;
            FileLock lock = null;
            File f = new File("f:/tmp/1111.pcap");
            while (true) {
                try {
                    fc = new FileOutputStream(f).getChannel();
                    lock = fc.lock();
                } catch (Exception e) {
                }
                try {
                    if (lock != null) {
                        System.out.printf("[%s] is lock. the value is [%s]\n", f.getAbsolutePath(), LocalBean.look());
                        Thread.sleep(2000);
                        lock.release();
                        return;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (fc != null) {
                        try {
                            fc.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public void tryLock() {
            FileChannel fc = null;
            FileLock lock = null;
            File f = new File("f:/tmp/1111.pcap");
            while (true) {
                try {
                    fc = new FileOutputStream(f).getChannel();
                    lock = fc.lock();
                } catch (Exception e) {
                }
                try {
                    if (lock != null) {
                        System.out.printf("[%s] is lock. the value is [%s]\n", f.getAbsolutePath(), LocalBean.look());
                        Thread.sleep(2000);
                        lock.release();
                        return;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (fc != null) {
                        try {
                            fc.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public FileChannel getChannel(FileInputStream fin) {
        FileChannel fc = null;
        synchronized (fin) {
            try {
                fc = FileChannelImpl.open(fin.getFD(), true, true, fin);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fc;
        }
    }

    @Test
    public void testLock() {
        ExecutorService service = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 5; i++) {
                service.execute(new LockRun());
                Thread.sleep(10);
            }
            Thread.sleep(10000);
            service.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Test
    public void testWriter() throws Exception {
        BufferedWriter writer = null;
        BufferedOutputStream output = null;
        try {
            File f = new File("F:/tmp/test1/test1/1.txt");
            if (!f.getParentFile().exists() && !f.getParentFile().mkdirs()) {
                System.err.printf("The [%s] directory can't be build.", f
                        .getParentFile().getAbsolutePath());
            }
            writer = new BufferedWriter(new FileWriter(f));

            writer.write("Hello World,the file is one!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        try {
            File f = new File("F:/tmp/test2/test2/2.txt");
            if (!f.getParentFile().exists() && !f.getParentFile().mkdirs()) {
                System.err.printf("The [%s] directory can't be build.", f
                        .getParentFile().getAbsolutePath());
            }
            output = new BufferedOutputStream(new FileOutputStream(f));
            output.write("Hello World,the file is one!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
