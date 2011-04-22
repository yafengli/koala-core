package test.test;

import java.util.Date;

import org.junit.BeforeClass;
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

/**
 * @author liyafeng JPA
 */
public class JPATest {

	private static final Logger logger = LoggerFactory.getLogger(JPATest.class);
	private static ApplicationContext ctx = null;
	private static ExUserService exUserService = null;
	private static UserService userService = null;
	private static IDao baseDao = null;

	@BeforeClass
	public static void init() {
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"META-INF/spring/applicationContext-common.xml",
				"META-INF/spring/applicationContext-jpa.xml" });
		userService = UserServiceImpl.getInstance(ctx);
		exUserService = ExUserServiceImpl.getInstance(ctx);
		baseDao = (IDao) ctx.getBean("baseJPADao");

		if (exUserService.findAll().size() == 0) {
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
	}

	@Test
	public void testFind() {
		for (ExUser eu : exUserService.findAll()) {
			logger.info(eu.getUsername());
		}
	}

	@Test
	public void testQuery() {
		ExUser eu1 = exUserService.findSingle("find.username",
				new String[] { "username" }, new Object[] { "user2" });
		logger.info("@@{},{}", eu1.getId(), eu1.getPassword());
		ExUser eu2 = exUserService.findSingle("find.exkey",
				new String[] { "exkey" }, new Object[] { "3" });
		logger.info("##{},{}", eu2.getId(), eu2.getPassword());

		int result = exUserService.service("user3");
		logger.info("##{}", result);
	}

	// @AfterClass
	public static void clear() {
		for (ExUser eu : exUserService.findAll()) {
			exUserService.remove(eu);
		}
	}
}
