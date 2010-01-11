package test.dao;

import org.koala.dao.jdbc.IGenericJDBCDao;
import test.model.Account;

public interface AccountJDBCDao extends IGenericJDBCDao<Account, Long> {
    /**
     * 无法抽象、复用特定业务的接口调用
     */
    //TODO
}
