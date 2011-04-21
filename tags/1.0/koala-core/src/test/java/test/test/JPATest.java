package test.test;

import org.junit.Before;
import org.junit.Test;
import org.koala.dao.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.dao.ExUserService;
import test.dao.UserService;
import test.dao.impl.jpa.ExUserServiceImpl;
import test.dao.impl.jpa.UserServiceImpl;
import test.model.ExUser;
import test.model.UserDetail;

import java.util.Date;

/**
 * @author liyafeng JPA
 */
public class JPATest {

	private static final Logger logger = LoggerFactory.getLogger(JPATest.class);
	private ApplicationContext ctx = null;
	private ExUserService exUserService = null;
	private UserService userService = null;
	private IDao baseDao = null;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"META-INF/spring/applicationContext-common.xml",
				"META-INF/spring/applicationContext-jpa.xml" });
		userService = UserServiceImpl.getInstance(ctx);
		exUserService = ExUserServiceImpl.getInstance(ctx);
		baseDao = (IDao) ctx.getBean("baseJPADao");
		for (int i = 0; i < 4; i++) {
			UserDetail ud = new UserDetail();
			ud.setAddress("fuck" + i);
			ud.setBirthday(new Date());
			ud.setUsername("fuck" + i);
			baseDao.save(ud);
			ExUser eu = new ExUser();
			eu.setAge(String.valueOf(i));
			eu.setExkey(String.valueOf(i));
			eu.setPassword("user" + String.valueOf(i));
			eu.setUsername("user" + String.valueOf(i));
			eu.setUserDetail(ud);
			exUserService.save(eu);
		}
	}

	@Test
	public void testFind() {
		for (ExUser eu : exUserService.findAll()) {
			System.out.println(eu.getUsername());
		}
	}
}
