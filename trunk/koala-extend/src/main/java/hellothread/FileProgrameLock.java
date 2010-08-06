package hellothread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProgrameLock {
    public static final Logger logger = LoggerFactory.getLogger(FileProgrameLock.class);

    public static void main(String[] args) throws Exception {
        ExecutorService execu = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        long start = System.currentTimeMillis();
        File files[] = new File("f:/tmp/pcap/").listFiles();
        execu.execute(new CountRun(files.length, 500));
        for (File gzipFile : files) {
            File pcapFile = new File("f:/tmp/testlock", gzipFile.getName().substring(0, gzipFile.getName().length() - 3));
            System.out.printf("#%s,%s\n", pcapFile.getAbsolutePath(), gzipFile.getAbsolutePath());
            Runnable run = new UnzipThread(pcapFile, gzipFile, 50L);
            execu.execute(run);
        }
        execu.shutdown();
        long end = System.currentTimeMillis();
        logger.info("#time use: {} ms.", end - start);
        System.out.println("OK.");
    }
}

class UnzipThread implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(UnzipThread.class);
    private File gzipFile;
    private File unzipFile;
    private long blockTime;

    public UnzipThread(File unzipFile, File gzipFile, long blockTime) {

        this.gzipFile = gzipFile;
        this.unzipFile = unzipFile;
        this.blockTime = blockTime;
    }

    public void run() {
        FileStateLock plock = new FileStateLock();
        String filename = this.unzipFile.getAbsolutePath();
        while (true) {
            try {
                if (plock.lock(filename, gzipFile)) {
                    plock.unlock();
                    break;
                }
                logger.error("#unzip [{}->{}] is waiting...\n", gzipFile.getAbsolutePath(), unzipFile.getAbsolutePath());
                Thread.sleep(blockTime);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class CountRun implements Runnable {

    private long count;
    private long blockTime;

    public CountRun(long count, long blockTime) {
        this.count = count;
        this.blockTime = blockTime;
    }

    public void run() {
        while (true) {
            try {
                if (FileStateLock.getLockCount() >= this.count) {
                    System.out.printf("#all file is ok,%d :%d\n", FileStateLock.getLockCount(), this.count);
                    break;
                } else {
                    System.out.printf("#index=%d, slide=%d\n", FileStateLock.getLockCount(), this.count);
                    Thread.sleep(this.blockTime);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

