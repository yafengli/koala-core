package hadoop.config;

import java.io.BufferedReader;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import java.io.InputStreamReader;
import java.net.URL;

public class URLCat {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String args[]) throws Exception {
        long count = 0;
        BufferedReader reader = null;
        try {
            long start = System.currentTimeMillis();
            reader = new BufferedReader(new InputStreamReader(new URL(args[0]).openStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            long end = System.currentTimeMillis();
            System.out.printf("count:%d, time use:%d\n", count, end - start);
        } finally {
            if (reader != null) {
                reader.close();
            }

        }
    }
}
