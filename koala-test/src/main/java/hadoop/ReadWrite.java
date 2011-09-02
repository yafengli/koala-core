package hadoop;

import java.nio.ByteBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

/**
 *
 * @author Administrator
 */
public class ReadWrite {

    public static void main(String[] args) throws Exception {
        DistributedFileSystem hdfs = (DistributedFileSystem)FileSystem.get(new Configuration());
        Path path = new Path("/user/root/input/file5");
        FSDataOutputStream dos = hdfs.create(path);
        dos.write("This is NEW S 13!".getBytes());
        dos.close();
        hdfs.listStatus(path);
        System.out.printf("@%s@\n", hdfs.exists(path));

        FSDataInputStream dis = hdfs.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readLength = -1;
        byte[] bts = new byte[6];
        while ((readLength = dis.read(bts)) > 0) {
            buffer.put(bts, 0, readLength);

        }
        buffer.flip();
        FileStatus fs=hdfs.getFileStatus(path);
        for(DatanodeInfo di: hdfs.getDataNodeStats()){
            di.getHostName();
           
            
        }
        System.out.println(new String(buffer.array()));
        dis.close();
        hdfs.close();
    }
}
