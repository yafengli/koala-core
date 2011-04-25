package test.dao.impl.jpa;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.koala.dao.jpa.GenericJPADao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.dao.ExUserService;
import test.model.ExUser;

/**
 * Created by IntelliJ IDEA. User: liyafeng Date: 2007-12-13 Time: 11:20:02 To
 * change this template use File | Settings | File Templates.
 */
@Component("exuserservice")
@Transactional
public class ExUserServiceImpl extends GenericJPADao<ExUser, Integer> implements
        ExUserService {

    public static final Logger logger = LoggerFactory.getLogger(ExUserServiceImpl.class);
    public static final String BEAN_NAME = "exuserservice";

    public static ExUserService getInstance(ApplicationContext ctx) {
        return (ExUserService) ctx.getBean(BEAN_NAME, ExUserService.class);
    }

    @Override
    public int service(final String username) {
        ExUser eu = findSingle("find.test.eu", new String[]{"username"},
                new Object[]{username});
        logger.info("@@:{},{}", eu.getId(), eu.getUserDetail().getId());
        return eu.getId();
    }
}
