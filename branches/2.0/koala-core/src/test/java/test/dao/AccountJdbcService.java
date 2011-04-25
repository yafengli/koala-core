package test.dao;

import org.koala.dao.IGenericJDBCDao;
import test.model.Account;

public interface AccountJdbcService extends IGenericJDBCDao<Account, Long> {
}
