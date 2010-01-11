package test.dao.impl.jpa;

import org.koala.dao.jpa.GenericDaoJpa;
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
@Transactional
public class ExUserDaoImpl extends GenericDaoJpa<ExUser, Integer> implements
		ExUserDao {
	public static final String BEAN_NAME = "exuserservice";

	public static ExUserDao getInstance(ApplicationContext ctx) {
		return (ExUserDao) ctx.getBean(BEAN_NAME, ExUserDao.class);
	}
}
