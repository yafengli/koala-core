package test.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.koala.jdbc.helper.JpaOrmFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.model.ExUser;
import test.model.User;
import test.model.UserDetail;

import java.util.Date;
import java.util.List;

/**
 * User: ya_feng_li@163.com
 * Date: 13-4-24
 * Time: 上午9:55
 */
public class JpaOrmTest {
    private static final Logger logger = LoggerFactory.getLogger(JpaOrmTest.class);
    private static ApplicationContext ctx = null;
    private static JpaOrmFacade facade = null;

    @BeforeClass
    public static void init() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                "META-INF/spring/applicationContext-common.xml",
                "META-INF/spring/applicationContext-jpa.xml"});
        facade = ctx.getBean("facade", JpaOrmFacade.class);
    }

    @Test
    public void testOne() {
        Long start = System.currentTimeMillis();

//        for (int i = 0; i < 2; i++) {
//            UserDetail ud = facade.get((long) (i + 150), UserDetail.class);
//            logger.error("#ud:" + ud.getId() + "," + ud.getUsername());
//            ExUser user = new ExUser();
//            user.setId(i);
//            user.setAge(String.valueOf(i));
//            user.setUsername(String.valueOf(i));
//            user.setPassword(String.valueOf(i));
//            user.setExkey(String.valueOf(i));
//            user.setUserDetail(ud);
//
//            facade.insert(user);
//        }
        Long count = facade.count("find.count.exuser", null);
        List<ExUser> list = facade.fetch("find.all.exuser", null,ExUser.class);
        Long end = System.currentTimeMillis();
        logger.info("#time:{}ms.", (end - start));
        logger.info("#count:{} list:{}.", count, list.size());
    }
}
