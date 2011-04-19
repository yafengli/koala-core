package test.dao.impl.jpa;

import org.koala.dao.GenericDaoJpa;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import test.dao.ExUserDao;
import test.model.ExUser;

/**
 * Created by IntelliJ IDEA. User: liyafeng Date: 2007-12-13 Time: 11:20:02 To
 * change this template use File | Settings | File Templates.
 */
@Component("exuserservice")
public class ExUserDaoImpl extends GenericDaoJpa<ExUser, Integer> implements
		ExUserDao {
	public static final String BEAN_NAME = "exuserservice";

	public static ExUserDao getInstance(ApplicationContext ctx) {
		return (ExUserDao) ctx.getBean(BEAN_NAME, ExUserDao.class);
	}


	@Transactional
	@Override
	public int service(String username) {
		ExUser eu = findSingle("find.test.eu", new String[]{"username"}, new Object[]{username});
		System.out.printf("@@:%s,%s", eu.getId(), eu.getUserDetail().getId());
		return eu.getId();
	}
}
