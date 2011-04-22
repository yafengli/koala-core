/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.dao;

import cn.demo.dao.DemoPersonDao;
import cn.demo.pojo.DemoPerson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class DaoTest {

    ApplicationContext ctx = null;
    DemoPersonDao dpdao = null;

    @Before
    public void before() {
        ctx = new FileSystemXmlApplicationContext(new String[]{
                    "F:/Projects/koala/koala-spring/src/main/webapp/WEB-INF/config/spring/applicationContext.xml",
                    "F:/Projects/koala/koala-spring/src/main/webapp/WEB-INF/config/spring/applicationContext-dao.xml"
                });
        dpdao = (DemoPersonDao) ctx.getBean("demoPersonDaoImpl");
    }

    //@Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            DemoPerson dp = new DemoPerson();
            dp.setId(i + 10L);
            dp.setName(String.valueOf(Math.random() * 1000));
            dp.setVersion(Math.round(11.5f) * 10);
            dpdao.save("insert into demo_person(id,name,version) values(:id,:name,:version)", dp);
        }
    }
    @Test
    public void testStr(){
        System.out.printf("[Hello World!{%s}]","Test");
    }
}
