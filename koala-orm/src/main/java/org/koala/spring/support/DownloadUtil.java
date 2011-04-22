package org.koala.spring.support;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author annegu
 * @since 2009-07-16
 */
public class DownloadUtil {
    public static final String FILE_SUFFIX = ".part";

    // private static transient
    // 分段下载的线程个数
    private int threadNum = 5;
    private long threadLength = 0;
    private long sleepSeconds = 5;
    private boolean statusError = false;
    // 目标文件路径与名字
    public String fileDir;
    public String fileName;
    private String charset;
    //下载URL
    private URL url;

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public long getThreadLength() {
        return threadLength;
    }

    public void setThreadLength(long threadLength) {
        this.threadLength = threadLength;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isStatusError() {
        return statusError;
    }

    public void setStatusError(boolean statusError) {
        this.statusError = statusError;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public long getSleepSeconds() {
        return sleepSeconds;
    }

    public void setSleepSeconds(long sleepSeconds) {
        this.sleepSeconds = sleepSeconds;
    }

    public String download() {
        long contentLength = 0;
        CountDownLatch latch = new CountDownLatch(threadNum);
        DownloadProcess[] childThreads = new DownloadProcess[threadNum];
        long[] startPos = new long[threadNum];
        long[] endPos = new long[threadNum];

        try {
            // 从url中获得下载的文件格式与名字
            setFileName(getUrl().getFile().substring(getUrl().getFile().lastIndexOf("/") + 1,
                    getUrl().getFile().lastIndexOf("?") > 0 ? getUrl().getFile().lastIndexOf("?")
                            : getUrl().getFile().length()));
            if ("".equalsIgnoreCase(getFileName())) {
                setFileName(UUID.randomUUID().toString());
            }

            File file = new File(fileDir, fileName);
            File tempFile = new File(fileDir, fileName + FILE_SUFFIX);

            HttpURLConnection con = (HttpURLConnection) getUrl().openConnection();
            setHeader(con);
            // 得到content的长度
            contentLength = con.getContentLength();

            if (file.exists() && file.length() == contentLength) {
                System.out.println("The file is ok.");
                tempFile.deleteOnExit();
            } else {
                // 把context分为threadNum段的话，每段的长度。
                this.threadLength = contentLength / threadNum;

                // 第一步，分析已下载的临时文件，设置断点，如果是新的下载任务，则建立目标文件。
                setThreadBreakpoint(file, tempFile, contentLength, startPos, endPos);

                // 第二步，分多个线程下载文件
                ExecutorService exec = Executors.newCachedThreadPool();
                for (int i = 0; i < threadNum; i++) {
                    // 开启子线程，并执行。
                    childThreads[i] = new DownloadProcess(this, latch, i,
                            startPos[i], endPos[i]);
                    exec.execute(childThreads[i]);
                }

                try {
                    // 等待CountdownLatch信号为0，表示所有子线程都结束。
                    latch.await();
                    exec.shutdown();

                    // 删除临时文件
                    long downloadFileSize = file.length();
                    if (downloadFileSize == contentLength) {
                        tempFile.deleteOnExit();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDir + fileName;
    }

    private void setThreadBreakpoint(File file, File tempFile,
                                     long contentLength, long[] startPos, long[] endPos) {
        RandomAccessFile tempFileFos = null;
        try {
            if (file.exists()) {
                System.out.println("file " + fileName + " has exists!");

                long localFileSize = file.length();
                // 下载的目标文件已存在，判断目标文件是否完整
                if (localFileSize < contentLength) {
                    System.out.println("Now download continue ... ");

                    tempFileFos = new RandomAccessFile(tempFile, "rw");
                    // 遍历目标文件的所有临时文件，设置断点的位置，即每个临时文件的长度
                    for (int i = 0; i < threadNum; i++) {
                        tempFileFos.seek(4 + 24 * i + 8);
                        startPos[i] = tempFileFos.readLong();

                        tempFileFos.seek(4 + 24 * i + 16);
                        endPos[i] = tempFileFos.readLong();
                    }
                } else {
                    System.out.println("This file has download complete!");
                }

            } else {
                // 如果下载的目标文件不存在，则创建新文件
                file.createNewFile();
                tempFile.createNewFile();
                tempFileFos = new RandomAccessFile(tempFile, "rw");
                tempFileFos.writeInt(threadNum);

                for (int i = 0; i < threadNum; i++) {

                    // 创建子线程来负责下载数据，每段数据的起始位置为(threadLength * i)
                    startPos[i] = threadLength * i;
                    tempFileFos.writeLong(startPos[i]);

                    /**
                     * 设置子线程的终止位置，非最后一个线程即为(threadLength * (i + 1) - 1)
                     * 最后一个线程的终止位置即为下载内容的长度
                     */
                    if (i == threadNum - 1) {
                        endPos[i] = contentLength;
                    } else {
                        endPos[i] = threadLength * (i + 1) - 1;
                    }
                    // current position
                    tempFileFos.writeLong(startPos[i]);
                    // end position
                    tempFileFos.writeLong(endPos[i]);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                tempFileFos.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setHeader(URLConnection con) {
        con.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Accept-Encoding", "aa");
        con.setRequestProperty("Accept-Charset",
                "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
        con.setRequestProperty("Keep-Alive", "300");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("If-Modified-Since",
                "Fri, 02 Jan 2009 17:00:05 GMT");
        con.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
        con.setRequestProperty("Cache-Control", "max-age=0");
    }
}
