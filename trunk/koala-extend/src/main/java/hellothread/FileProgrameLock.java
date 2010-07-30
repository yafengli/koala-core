package hellothread;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.GZIPInputStream;

public class FileProgrameLock {
    private FileLock lock = null;
    private FileChannel channel = null;

    public boolean lock(String fileName, File gzipFile) throws FileNotFoundException {
        File tf = new File(fileName);
        long ctime = System.currentTimeMillis();
        GZIPInputStream in = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(tf, "rw");
            channel = raf.getChannel();
            lock = channel.tryLock();
            if (lock != null) {
                if (tf.lastModified() < ctime) {
                    System.out.println("File is exists.");
                    return true;
                } else {
                    System.out.println("File is no exists, and DO SOMETHING.");


                    in = new GZIPInputStream(new FileInputStream(gzipFile));
                    int count = -1;
                    byte[] data = new byte[1024];
                    while ((count = in.read(data)) != -1) {
                        raf.write(data);
                    }
                    return true;
                }
            } else {
                return false;
            }
        }
        catch (OverlappingFileLockException e) {
            return false;
        }
        catch (IOException e) {
            return false;
        }

    }

    public void unlock() {
        try {
            if (lock != null) {
                lock.release();
            }
            if (channel != null) {
                channel.close();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        final FileProgrameLock plock = new FileProgrameLock();
        Thread t1 = new Thread(new TestRun(plock));
        final FileProgrameLock plock2 = new FileProgrameLock();
        Thread t2 = new Thread(new TestRun(plock2));
        t1.start();
        t2.start();
        System.out.println("OK.");
        Thread.sleep(4000);
    }
}

class TestRun implements Runnable {
    private FileProgrameLock plock;

    public TestRun(FileProgrameLock plock) {
        this.plock = plock;
    }

    public void run() {
        try {
            if (plock.lock("F:/tmp/lock.pcap", new File("f:/tmp/1.pcap.gz"))) {
                System.out.printf("[%s] file is locking...\n", Thread.currentThread().getName());
                //TODO
                Thread.sleep(2000);
                plock.unlock();
                System.out.printf("[%s] file is unlocking...\n", Thread.currentThread().getName());
            } else {
                while (true) {
                    System.out.printf("[%s] file is locked.\n", Thread.currentThread().getName());
                    if (plock.lock("F:/tmp/lock.pcap", new File("f:/tmp/1.pcap.gz"))) {
                        System.out.printf("[%s]file is unlocked.\n", Thread.currentThread().getName());
                        plock.unlock();
                        return;
                    }
                    Thread.sleep(500);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

