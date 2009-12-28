package test.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.koala.dao.GenericDaoJDBC;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import test.dao.AccountJDBCDao;
import test.model.Account;

@Service("test.dao.impl.jdbc.AccountJDBCDaoImpl")
public class AccountJDBCDaoImpl extends GenericDaoJDBC<Account, Long> implements
        AccountJDBCDao {

    public ParameterizedRowMapper<Account> getRowMapper() {
        return new ParameterizedRowMapper<Account>() {

            public Account mapRow(ResultSet rs, int num) throws SQLException {
                Account account = new Account();
                try {
                    account.setAid(rs.getLong("AID"));
                } catch (Exception e) {
                }
                try {
                    account.setAname(rs.getString("ANAME"));
                } catch (Exception e) {
                }
                try {
                    account.setAdddesc(rs.getString("ADDDESC"));
                } catch (Exception e) {
                }
                try {
                    account.setAddress(rs.getString("ADDRESS"));
                } catch (Exception e) {
                }
                try {
                    account.setAdesc(rs.getString("ADESC"));
                } catch (Exception e) {
                }
                return account;
            }
        };
    }
}
