package test.dao;

import org.koala.dao.IGenericJDBCDao;
import test.model.Account;

public interface AccountJdbcService extends IGenericJDBCDao<Account, Long> {
    /**
     * 无法抽象、复用特定业务的接口调用
     */
    //TODO
}
