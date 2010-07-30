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

    protected boolean running;

    protected ServerSocket ss;

    protected FileLock lock;

    public boolean lock(String fileName) throws FileNotFoundException {
        File tf = new File(fileName);
        FileChannel channel = new RandomAccessFile(tf, "rw").getChannel();
        try {
            lock = channel.tryLock();
            if (lock != null) {
                return true;
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
            if (!plock.lock("F:/tmp/lock.log")) {
                while (true) {
                    System.out.println("File is locked.");
                    if (!plock.lock("F:/tmp/lock.log")) {
                        System.out.println("File is unlocked.");
                        return;
                    }
                    Thread.sleep(3000);
                }
            } else {
                System.out.println("File is locking...");
                plock.unlock();
                System.out.println("File is unlocking...");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

