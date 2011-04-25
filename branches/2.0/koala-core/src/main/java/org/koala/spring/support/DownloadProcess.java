package org.koala.spring.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-10-19
 * Time: 13:51:33
 * To change this template use File | Settings | File Templates.
 */
public class DownloadProcess implements Runnable {
    private DownloadUtil task;
    private int id;
    private long startPosition;
    private long endPosition;
    private final CountDownLatch latch;
    private RandomAccessFile file = null;
    private RandomAccessFile tempFile = null;

    public DownloadProcess(DownloadUtil task, CountDownLatch latch, int id,
                           long startPos, long endPos) {
        super();
        this.task = task;
        this.id = id;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.latch = latch;
        try {
            file = new RandomAccessFile(task.getFileDir()
                    + task.getFileName(), "rw");
            tempFile = new RandomAccessFile(task.fileDir
                    + task.getFileName() + DownloadUtil.FILE_SUFFIX, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Thread " + id + " run ...");
        HttpURLConnection con = null;
        InputStream inputStream = null;
        long count = 0;
        try {
            System.out.println(id + "===1 ====" + tempFile.readLong());
            tempFile.seek(4 + 24 * id);
            System.out.println(id + "===2 ====" + tempFile.readLong());
            System.out.println(id + "===3 ====" + tempFile.readLong());
            System.out.println(id + "===4 ====" + tempFile.readLong());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        while (true) {
            try {
                // 打开URLConnection
                con = (HttpURLConnection) task.getUrl().openConnection();
                task.setHeader(con);
                // 设置连接超时时间为10000ms
                con.setConnectTimeout(10000);
                // 设置读取数据超时时间为10000ms
                con.setReadTimeout(10000);

                if (startPosition < endPosition) {
                    // 设置下载数据的起止区间
                    con.setRequestProperty("Range", "bytes="
                            + startPosition + "-" + endPosition);
                    System.out.println("Thread " + id
                            + " startPosition is " + startPosition
                            + ", and endPosition is " + endPosition);

                    file.seek(startPosition);

                    // 判断http status是否为HTTP/1.1 206 Partial Content或者200 OK
                    // 如果不是以上两种状态，把status改为STATUS_HTTPSTATUS_ERROR
                    if (con.getResponseCode() != HttpURLConnection.HTTP_OK
                            && con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {
                        System.out.println("Thread " + id + ": code = "
                                + con.getResponseCode() + ", status = "
                                + con.getResponseMessage());
                        task.setStatusError(true);
                        file.close();
                        con.disconnect();
                        System.out.println("Thread " + id + " finished.");
                        latch.countDown();
                        break;
                    }

                    inputStream = con.getInputStream();
                    int len = 0;
                    byte[] b = new byte[1024];
                    while (!task.isStatusError()
                            && (len = inputStream.read(b)) != -1) {
                        file.write(b, 0, len);

                        count += len;
                        startPosition += len;

                        // set tempFile now position
                        tempFile.seek(4 + 24 * id + 16);
                        tempFile.writeLong(startPosition);
                    }

                    file.close();
                    tempFile.close();
                    inputStream.close();
                    con.disconnect();
                }

                System.out.println("Thread " + id + " finished.");
                latch.countDown();
                break;
            } catch (IOException e) {
                try {
                    // outputStream.flush();
                    TimeUnit.SECONDS.sleep(task.getSleepSeconds());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                continue;
            }
        }
    }

}

