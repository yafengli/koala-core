package template;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import freemarker.template.Configuration;
import org.koala.utils.template.ITemplateUtils;
import org.koala.utils.template.TemplateUtilsFactory;
import org.koala.utils.template.freemarker.FreemarkerUtils;
import org.koala.utils.template.velocity.VelocityUtils;
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

    private TemplateUtilsFactory factory;
    private ITemplateUtils freemarkerUtils;
    private ITemplateUtils velocityUtils;

    @Before
    public void init() {
        try {
            factory = new TemplateUtilsFactory();
            freemarkerUtils = factory.factory(FreemarkerUtils.class.getName());
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File("F:/Tmp"));
            freemarkerUtils.init(conf);


            velocityUtils = factory.factory(VelocityUtils.class.getName());
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "f:/tmp");
            ve.init();
            velocityUtils.init(ve);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void freemarker() {
        try {
            Map map = new Hashtable();
            map.put("msg", "##Hello World!##");

            long startTime = System.currentTimeMillis();
            String content = null;
            for (int i = 0; i < 50; i++) {
                content = freemarkerUtils.template("test.ftl", map);
            }
            long endTime = System.currentTimeMillis();
            logger.info("{},{}", String.valueOf(endTime - startTime), content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void velocity() {
        try {
            Map map = new Hashtable();
            map.put("msg", "##Hello World!##");

            long startTime = System.currentTimeMillis();
            String content = null;
            for (int i = 0; i < 50; i++) {
                content = velocityUtils.template("test.vm", map);
            }
            long endTime = System.currentTimeMillis();
            logger.info("{},{} ", String.valueOf(endTime - startTime), content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
