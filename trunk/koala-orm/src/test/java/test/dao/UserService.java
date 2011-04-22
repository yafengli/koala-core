package test.dao;

import org.koala.dao.IGenericDao;
import org.springframework.transaction.annotation.Transactional;

import test.model.User;

/**
 * Created by IntelliJ IDEA.
 * User: liyafeng
 * Date: 2007-12-13
 * Time: 11:17:07
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends IGenericDao<User, Integer> {
}