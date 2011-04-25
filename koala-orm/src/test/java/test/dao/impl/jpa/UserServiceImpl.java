package test.dao.impl.jpa;

import javax.persistence.EntityManagerFactory;

import org.koala.dao.jpa.GenericJPADao;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.dao.UserService;
import test.model.User;

@Component("userservice")
@Transactional
public class UserServiceImpl extends GenericJPADao<User, Integer> implements
        UserService {

    public static final String BEAN_NAME = "userservice";
    
    private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
        
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static UserService getInstance(ApplicationContext ctx) {
        UserService dao = (UserService) ctx.getBean(BEAN_NAME, UserService.class);
        return dao;
    }

}
