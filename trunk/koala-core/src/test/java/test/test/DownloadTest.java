package test.test;

import org.junit.Test;
import org.koala.spring.support.DownloadUtil;

import java.net.URL;

/**
 * @author annegu
 * @since 2009-07-16
 */
public class DownloadTest {

    @Test
    public void testDown() {
        String urlStr = "http://apache.freelamp.com/velocity/tools/1.4/velocity-tools-1.4.zip";
        try {
            DownloadUtil downloadManager = new DownloadUtil();

            downloadManager.setUrl(new URL(urlStr));
            downloadManager.setFileDir("f:/");
            downloadManager.setSleepSeconds(5);

            String downladFileName = downloadManager.download();
            System.out.println("Download file is " + downladFileName + ".");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
