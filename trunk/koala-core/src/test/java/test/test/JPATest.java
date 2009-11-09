package test.test;

import org.junit.Test;
import org.koala.dao.IDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.dao.ExUserDao;
import test.dao.UserDao;
import test.dao.impl.jpa.ExUserDaoImpl;
import test.dao.impl.jpa.UserDaoImpl;
import test.model.ExUser;
import test.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyafeng 这是JPA的测试程序！
 */
public class JPATest {

    private static final Logger logger = LoggerFactory.getLogger(JPATest.class);
    private ApplicationContext ctx = null;
    private ExUserDao jeudao = null;
    private UserDao judao = null;
    private IDao dao = null;

    public JPATest() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-common.xml", "applicationContext-jpa.xml"});
        judao = UserDaoImpl.getInstance(ctx);
        jeudao = ExUserDaoImpl.getInstance(ctx);
        dao = (IDao) ctx.getBean("baseJPADao");
        System.out.println("[dao]" + dao);
    }

    @Test
    public void add() {
        logger.info("########add");
        List<User> items = dao.findAll(User.class);
        logger.info("[items:]" + items);
        for (User item : items) {
            System.out.println(item);
        }
        User item = dao.findSingle("find.test.id", new String[]{"id"}, new Object[]{200});
        System.out.println("****" + item);
    }

    //    @Test
    public void find() {
        List<User> lus = judao.findAll();
        for (User u : lus) {
            System.out.println("[findAll()]" + u.getUsername() + "|" + u.getPassword());
        }
        List<User> lusf = judao.findByQueryName("find.test");
        for (User u : lusf) {
            System.out.println("[findByQueryName()]" + u.getUsername() + "|" + u.getPassword());
        }
        List<User> elusf = judao.findByQueryName("find.test", 1, 3);
        for (User u : elusf) {
            System.out.println("[findByQueryName(1,3)]" + u.getId() + "|" + u.getUsername() + "|" + u.getPassword());
        }
        List<ExUser> leus = jeudao.findAll();
        for (ExUser eu : leus) {
            System.out.println("[findAll()]" + eu.getUsername() + "|" + eu.getPassword());
        }
        List<User> pelusf = judao.findByQueryName("find.test.param", new String[]{"username"}, new Object[]{"测试"}, 1, 3);
        for (User eu : pelusf) {
            System.out.println("[pfindByQueryName()]" + eu.getUsername() + "|" + eu.getPassword());
        }
        for (User u : elusf) {
            System.out.println("[findByQueryName(1,3)]" + u.getId() + "|" + u.getUsername() + "|" + u.getPassword());
        }
    }

    //    @Test
    public void finaCount() {
        String queryName = "find.count.test";
        Long count = judao.findCountByQueryName(queryName);
        System.out.println("[count=]" + count);
        Long c2 = dao.findCount(queryName);
        System.out.println("[c2=]" + c2);
    }

    //    @Test
    public void save() {
        List<User> lusf = judao.findByQueryName("find.test");
        List<User> adl = new ArrayList<User>();
        for (User t : lusf) {
            User u = new User();
            u.setPassword(t.getPassword());
            u.setAge(t.getAge());
            u.setUsername(t.getUsername());
            adl.add(u);
        }
        judao.saveBatch(adl);
    }
}
