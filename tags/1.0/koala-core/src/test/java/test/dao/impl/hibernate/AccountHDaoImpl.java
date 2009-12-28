package test.dao.impl.hibernate;

import org.koala.dao.GenericDaoHibernate;
import org.springframework.transaction.annotation.Transactional;

import test.dao.AccountDao;
import test.model.Account;

@Transactional
public class AccountHDaoImpl extends GenericDaoHibernate<Account, Long> implements AccountDao {

    public static final String beanName = "haccountDao";
}
