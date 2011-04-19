package test.test;

import org.junit.Before;
import org.junit.Test;
import org.koala.dao.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dao.ExUserDao;
import test.dao.UserDao;
import test.dao.impl.jpa.ExUserDaoImpl;
import test.dao.impl.jpa.UserDaoImpl;
import test.model.ExUser;
import test.model.UserDetail;

import java.util.Date;

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

	}

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(new String[]{
				"applicationContext-common.xml", "applicationContext-jpa.xml"});
		judao = UserDaoImpl.getInstance(ctx);
		jeudao = ExUserDaoImpl.getInstance(ctx);
		dao = (IDao) ctx.getBean("baseJPADao");
		for (int i = 0; i < 4; i++) {
			UserDetail ud = new UserDetail();
			ud.setAddress("fuck" + i);
			ud.setBirthday(new Date());
			ud.setUsername("fuck" + i);
			dao.save(ud);
			ExUser eu = new ExUser();
			eu.setAge(String.valueOf(i));
			eu.setExkey(String.valueOf(i));
			eu.setPassword("user" + String.valueOf(i));
			eu.setUsername("user" + String.valueOf(i));
			eu.setUserDetail(ud);
			jeudao.save(eu);
		}
	}

	@Test
	public void testFind() {
		for (ExUser eu : jeudao.findAll()) {
			System.out.println(eu.getUsername());
		}
	}
}
