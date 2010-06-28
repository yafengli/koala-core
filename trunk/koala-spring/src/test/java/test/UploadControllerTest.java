package test;

import cn.demo.scan.controller.DemoContorller;
import cn.demo.scan.controller.UploadContorller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
        ctx = new FileSystemXmlApplicationContext(new String[]{
                "f:\\Google\\koala-core\\koala-spring\\src\\main\\webapp\\WEB-INF\\config\\spring\\applicationContext.xml",
                "f:\\Google\\koala-core\\koala-spring\\src\\main\\webapp\\WEB-INF\\config\\spring\\applicationContext-aop.xml",
                "f:\\Google\\koala-core\\koala-spring\\src\\main\\webapp\\WEB-INF\\config\\spring\\applicationContext-dao.xml"
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
