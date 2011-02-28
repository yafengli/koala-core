package org.koala.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * User: liyafeng
 * Date: 2008-3-19
 * Time: 10:13:04
 *
 * @author YaFengLi
 * @version 1.0
 */
@Transactional
public class BaseIBatisDao extends SqlMapClientDaoSupport implements IIBatisDao {    

    public <T, Id extends Serializable> T findById(String statementName, Id id) {
        return (T) getSqlMapClientTemplate().queryForObject(statementName, id);
    }

    public <T> void save(String statementName, T t) {
        getSqlMapClientTemplate().insert(statementName, t);
    }

    public <T> void remove(String statementName, T t) {
        getSqlMapClientTemplate().delete(statementName, t);
    }

    public <T> void update(String statementName, T t) {
        getSqlMapClientTemplate().update(statementName, t);
    }

    public long findCount(final String statementName) {
        return this.findCount(statementName, null);
    }

    public long findCount(final String statementName, final Object paramObject) {
        Object obj = getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                return executor.queryForObject(statementName, paramObject);
            }
        });
        if (obj != null && obj instanceof Long) {
            return (Long) obj;
        } else {
            return -1;
        }
    }

    public <T> List<T> find(final String statementName) {
        return this.find(statementName, null);
    }

    public <T> List<T> find(final String statementName, final int startPosition, final int maxResult) {
        return this.find(statementName, null, startPosition, maxResult);
    }

    public <T> List<T> find(final String statementName, final Object paramObject) {
        return getSqlMapClientTemplate().execute(new SqlMapClientCallback<List<T>>() {

            public List<T> doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                return executor.queryForList(statementName, paramObject);
            }
        });
    }

    public <T> List<T> find(final String statementName, final Object paramObject, final int startPosition, final int maxResult) {
        return getSqlMapClientTemplate().execute(new SqlMapClientCallback<List<T>>() {
            @Override
            public List<T> doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                return executor.queryForList(statementName, paramObject, startPosition, maxResult);

            }
        });
    }

    public <T> void saveBatch(final String statementName, final List<T> objs) {
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {

            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                int i = 1;
                for (T t : objs) {
                    executor.insert(statementName, t);
                    if (i % 500 == 0 || i == objs.size()) {
                        executor.startBatch();
                        executor.executeBatch();
                    }
                    i++;
                }
                return null;
            }
        });
    }
}
