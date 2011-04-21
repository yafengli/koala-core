package test.dao.impl.ibatis;

import org.koala.dao.GenericDaoIBatis;

import test.dao.AccountIBatisService;
import test.model.Account;

public class AccountDaoImpl extends GenericDaoIBatis<Account, Long> implements
        AccountIBatisService {

    public static final String BEANNAME = "accountDao";
}
