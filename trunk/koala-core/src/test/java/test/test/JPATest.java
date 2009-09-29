package test.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.koala.dao.BaseJPADao;
import org.koala.dao.IDao;

import test.dao.ExUserDao;
import test.dao.UserDao;
import test.dao.impl.jpa.ExUserDaoImpl;
import test.dao.impl.jpa.UserDaoImpl;
import test.model.ExUser;
import test.model.User;

/**
 * @author liyafeng 这是JPA的测试程序！
 */
public class JPATest {

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
    }

    @Test
    public void add() {

        User user = new User();
        user.setUsername("测试");
        user.setPassword("测试密码");
        judao.save(user);

        ExUser exuser = new ExUser();
        exuser.setUsername("测试第二");
        exuser.setPassword("测试第二密码");
        jeudao.save(exuser);
    }

    @Test
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

    @Test
    public void finaCount() {
        String queryName = "find.count.test";
        Long count = judao.findCountByQueryName(queryName);
        System.out.println("[count=]" + count);
        Long c2 = dao.findCount(queryName);
        System.out.println("[c2=]" + c2);
    }

    @Test
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
