package test.dao.impl.ibatis;

import org.koala.dao.ibatis.GenericDaoIBatis;

import test.dao.AccountIBatisDao;
import test.model.Account;

public class AccountDaoImpl extends GenericDaoIBatis<Account, Long> implements
        AccountIBatisDao {

    public static final String BEANNAME = "accountDao";
}
