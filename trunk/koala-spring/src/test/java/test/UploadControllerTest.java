package test;

import cn.demo.scan.controller.UploadContorller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: phoenixup
 * Date: 2010-6-28
 * Time: 17:09:05
 */
public class UploadControllerTest {

    ApplicationContext ctx;
    UploadContorller controller;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                    "META-INF/spring/applicationContext.xml", 
                    "META-INF/spring/applicationContext-aop.xml",
                    "META-INF/spring/applicationContext-dao.xml"
                });
        controller = (UploadContorller) ctx.getBean("uploadContorller");
    }

    @Test
    public void test() {
        controller.setName("hello");
        controller.test(null, null);
        System.out.println("##############################");
    }
}
