package test.dao.impl.jpa;

import javax.persistence.EntityManagerFactory;

import org.koala.dao.GenericDaoJpa;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.dao.UserDao;
import test.model.User;

@Component("userservice")
@Transactional
public class UserDaoImpl extends GenericDaoJpa<User, Integer> implements
        UserDao {

    public static final String BEAN_NAME = "userservice";
    
    private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
        
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static UserDao getInstance(ApplicationContext ctx) {
        UserDao dao = (UserDao) ctx.getBean(BEAN_NAME, UserDao.class);
        return dao;
    }

}
