package hellothread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.zip.GZIPInputStream;

/**
 * User: phoenixup
 * Date: 2010-7-30
 * Time: 17:30:34
 */
public class FileStateLock {
    private static Long lockCount = 0L;
    public static final Logger logger = LoggerFactory.getLogger(FileStateLock.class);


    private FileLock lock = null;
    private FileChannel channel = null;

    public static Long getLockCount() {
        return lockCount;
    }

    public boolean lock(String fileName, File gzipFile) throws FileNotFoundException {
        File tf = new File(fileName);
        long fileCreateTime = tf.lastModified();
        long length = tf.length();
        GZIPInputStream in = null;
        RandomAccessFile raf = null;
        try {
            synchronized (lockCount) {
                lockCount++;
                if (!tf.getParentFile().exists()) {
                    try {
                        //FileUtils.forceMkdir(tf.getParentFile());
                        logger.error(String.format("@NO_EXISTS:%s\n",tf.getParentFile().getAbsolutePath()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            raf = new RandomAccessFile(tf, "rw");
            channel = raf.getChannel();
            lock = channel.tryLock();
            if (lock != null) {
                if (fileCreateTime == 0 || length == 0) {
                    if (gzipFile.exists()) {
                        in = new GZIPInputStream(new FileInputStream(gzipFile));
                        //Write
                        nioWrite(in);
                    } else {
                        logger.error("#GzipFile:{} noe exists.", gzipFile.getAbsolutePath());
                    }
                }
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
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (lock == null) {
                try {
                    if (raf != null) {
                        raf.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void ioWrite(RandomAccessFile raf, GZIPInputStream in) {
        try {
            int count = -1;
            byte[] data = new byte[1024];
            while ((count = in.read(data, 0, data.length)) != -1) {
                raf.write(data, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nioWrite(GZIPInputStream in) {
        try {
            ReadableByteChannel inchannel = Channels.newChannel(in);
            ByteBuffer buffer = ByteBuffer.allocate(65536);
            while (inchannel.read(buffer) != -1) {
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
            }
            inchannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        try {
            if (lock != null) {
                lock.release();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (channel != null) {
                channel.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
