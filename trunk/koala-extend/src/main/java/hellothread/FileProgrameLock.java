package hellothread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class FileProgrameLock {

    protected FileLock lock = null;
    private FileChannel channel = null;

    public boolean lock(String fileName) throws FileNotFoundException {
        File tf = new File(fileName);
        long ctime = System.currentTimeMillis();
        try {
            channel = new RandomAccessFile(tf, "rw").getChannel();
            lock = channel.tryLock();
            if (lock != null) {
                if (tf.lastModified() < ctime) {
                    System.out.println("File is exists.");
                    return true;
                } else {
                    System.out.println("File is no exists, and DO SOMETHING.");
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
        }
    }

    public static void main(String[] args) throws Exception {
        final FileProgrameLock plock = new FileProgrameLock();
        Thread t1 = new Thread(new TestRun(plock));
        final FileProgrameLock plock2 = new FileProgrameLock();
        Thread t2 = new Thread(new TestRun(plock2));
        t1.start();
        t2.start();
        Thread.sleep(5000);
    }
}

class TestRun implements Runnable {
    private FileProgrameLock plock;

    public TestRun(FileProgrameLock plock) {
        this.plock = plock;
    }

    public void run() {
        try {
            if (plock.lock("F:/tmp/lock.log")) {
                System.out.printf("[%s] file is locking...\n", Thread.currentThread().getName());
                //TODO
                Thread.sleep(2000);
                plock.unlock();
                System.out.printf("[%s] file is unlocking...\n", Thread.currentThread().getName());
            } else {
                while (true) {
                    System.out.printf("[%s] file is locked.\n", Thread.currentThread().getName());
                    if (plock.lock("F:/tmp/lock.log")) {
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

