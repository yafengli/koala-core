package test.dao.impl.ibatis;

import org.koala.dao.mybatis.GenericDaoIBatis;

import test.dao.AccountIBatisService;
import test.model.Account;

public class AccountDaoImpl extends GenericDaoIBatis<Account, Long> implements
        AccountIBatisService {

    public static final String BEANNAME = "accountDao";
}
