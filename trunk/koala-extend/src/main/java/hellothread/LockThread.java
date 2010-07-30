package hellothread;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * User: phoenixup
 * Date: 2010-7-30
 * Time: 16:21:04
 */
public class LockThread implements Runnable {
    public void run() {
        // Get a file channel for the file
        File file = new File("f:/tmp/lock.log");
        FileOutputStream out = null;
        FileChannel channel = null;
        try {
            out = new FileOutputStream(file);
            channel = out.getChannel();

            // Use the file channel to create a lock on the file.
            // This method blocks until it can retrieve the lock.
            FileLock lock = channel.lock();

            // Try acquiring the lock without blocking. This method returns
            // null or throws an exception if the file is already locked.
            try {
                lock = channel.tryLock();
            } catch (OverlappingFileLockException e) {
                e.printStackTrace();
            }
            if (lock != null) {
                System.out.println("Write");
                Thread.sleep(2000);
                // Release the lock
                lock.release();
            }
            // Close the file
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arsg[]) {
        try {
            Thread t1 = new Thread(new LockThread());
            t1.setName("Write--T");
            t1.start();
//            Thread t2 = new Thread(new ReadThread());
//            t2.setName("Read--T");
//            t2.start();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ReadThread implements Runnable {
    public void run() {
        File file = new File("f:/tmp/test.cpp");
        BufferedReader reader = null;
        try {
            while (true) {
                if (file.canRead()) {
                    reader = new BufferedReader(new FileReader(file));
                    try {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.printf("#msg:%s\n", line);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                } else {
                    System.out.printf("#%s waiting.", Thread.currentThread().getName());
                    Thread.sleep(500);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
