package hadoop;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URL;

/**
 * User: YaFengLi
 * Date: 11-7-22
 * Time: 上午9:22
 */
public class MapReduceTest {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }


    public static void main(String args[]) throws Exception {
        InputStream in = null;
        try {
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
