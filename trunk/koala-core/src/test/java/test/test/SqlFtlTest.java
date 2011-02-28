package test.test;

import org.junit.Before;
import org.junit.Test;
import org.koala.spring.support.SqlFtlUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YaFengli.
 * User: YaFengLi
 * Date: 2009-5-12
 * Time: 10:36:11
 */
public class SqlFtlTest {
    ApplicationContext ctx = null;

    @Before
    public void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext-sql.xml"});
    }

    @Test
    public void testSql() {

        SqlFtlUtils sfu = (SqlFtlUtils) ctx.getBean("sqlftlutil");
        Map map = new HashMap();
        map.put("user", "This is a user name!");
        Map hello = new HashMap();
        hello.put("name", "The name is OK!");
        hello.put("age", "The age is OK!");
        map.put("hello", hello);
        try {
            String content = "SELECT count(*) as total FROM cnet.a11log WHERE reporttime<='${item.startdate}' and  reporttime<='${item.enddate}' and areacode='${item.areacode}' and requettype=:requesttype <#if item.pdsnip?exists&&item.pdsnip!='%'> and pdsnip = :pdsnip </#if> <#if item.pcfip?exists&&item.pcfip!='%'> and pcfip =:pcfip </#if> <#if item.bsid?exists> and bsid = :bsid </#if> <#if item.msid?exsits> and (imsinumber like :imsinumber or mobilenumber like :mobilenumber) </#if> ORDER BY areacode,pdsnip";
            long startl=System.currentTimeMillis();
            for (int i = 0; i < 50000; i++) {
//                System.out.printf("[%s]\n", sfu.process(content, map));
                sfu.process(content, map);
            }
            long endl=System.currentTimeMillis();
            System.out.printf("[start,end,delay][%s,%s,%s]\n",startl,endl,(endl-startl)/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
