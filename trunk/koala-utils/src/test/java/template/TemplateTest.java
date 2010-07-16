package template;

import org.junit.Test;
import freemarker.template.Configuration;
import org.koala.template.ITemplateUtils;
import org.koala.template.TemplateUtilsFactory;
import org.koala.template.freemarker.FreemarkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

/**
 * User: phoenixup
 * Date: 2010-7-16
 * Time: 11:22:01
 */
public class TemplateTest {

    private static Logger logger = LoggerFactory.getLogger(TemplateTest.class);

    @Test
    public void freemarker() {
        try {

            TemplateUtilsFactory factory = new TemplateUtilsFactory();
            ITemplateUtils utils = factory.factory(FreemarkerUtils.class.getName());
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File("F:/Tmp"));
            utils.init(conf);
            Map map = new Hashtable();
            map.put("msg", "##Hello World!##");

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                String content = utils.template("test.ftl", map);
            }
            long endTime = System.currentTimeMillis();
            logger.info("The time is [{}] millisecond", String.valueOf(endTime - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
