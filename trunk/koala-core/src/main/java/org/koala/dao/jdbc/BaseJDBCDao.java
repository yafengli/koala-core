package org.koala.dao.jdbc;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.beans.BeanInfo;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.jdbc.core.RowMapper;

/**
 * Date: 2009-10-12
 * Time: 16:33:39
 *
 * @author YaFengLi
 * @version 1.0
 */
@Transactional
public class BaseJDBCDao extends SimpleJdbcDaoSupport implements IJDBCDao {

    public static final ConcurrentHashMap<Class, Method> mdmap = new ConcurrentHashMap<Class, Method>();

    static {
        try {
            mdmap.put(Integer.class, ResultSet.class.getMethod("getInt", String.class));
            mdmap.put(Long.class, ResultSet.class.getMethod("getLong", String.class));
            mdmap.put(String.class, ResultSet.class.getMethod("getString", String.class));
            mdmap.put(Timestamp.class, ResultSet.class.getMethod("getTimestamp", String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> int save(String sql, T t) {
        return update(sql, t);
    }

    @Override
    public <T> int remove(String sql, T t) {
        return update(sql, t);
    }

    @Override
    public <T> int update(String sql, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        return this.getSimpleJdbcTemplate().update(sql, sps);
    }

    @Override
    public <T> Map<String, Object> executeFunction(String functionName, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        this.getSimpleJdbcCall().withFunctionName(functionName);
        return this.getSimpleJdbcCall().execute(sps);
    }

    @Override
    public SimpleJdbcCall getSimpleJdbcCall() {
        return new SimpleJdbcCall(getJdbcTemplate());
    }

    @Override
    public <T> List<T> find(String sql, T t) {
        SqlParameterSource sps = null;
        if (t != null) {
            sps = new BeanPropertySqlParameterSource(t);
        }
        Class<T> c = (Class<T>) t.getClass();
        return this.getSimpleJdbcTemplate().query(sql, getRowMapper(c), sps);
    }

    @Override
    public <T> void execute(String sql, T t) {
        final SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        //TODO
        String sqlToUse = null;
        this.execute(sqlToUse);
    }

    @Override
    public <T> Map<String, Object> executeProcedure(String procedureName, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        this.getSimpleJdbcCall().withProcedureName(procedureName);
        return this.getSimpleJdbcCall().execute(sps);
    }

    @Override
    public <T> List<T> find(String sql, Class<T> c, Object... args) {
        return this.getSimpleJdbcTemplate().query(sql, getRowMapper(c), args);
    }

    @Override
    public <T> List<T> find(String sql, Class<T> c) {
        return this.getSimpleJdbcTemplate().query(sql, getRowMapper(c));
    }

    @Override
    public void execute(String sql) {
        this.getJdbcTemplate().execute(sql);
    }

    @Override
    public <T> T findForObject(String sql, Class<T> c, Object... args) {
        return (T) this.getSimpleJdbcTemplate().queryForObject(sql, getRowMapper(c), args);
    }

    @Override
    public <T> T findForObject(String sql, T t, Class<T> c) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        return (T) this.getSimpleJdbcTemplate().queryForObject(sql, getRowMapper(c), sps);
    }

    public <T extends Object> RowMapper<T> getRowMapper(final Class<T> c) {
        return new RowMapper<T>() {

            public T mapRow(ResultSet rs, int arg1) throws SQLException {
                T obj = null;
                try {
                    obj = c.newInstance();
                    BeanInfo info = Introspector.getBeanInfo(c, Object.class);
                    PropertyDescriptor[] pds = info.getPropertyDescriptors();
                    for (PropertyDescriptor pd : pds) {
                        Method wd = pd.getWriteMethod();
                        String name = pd.getName();
                        if (wd != null) {
                            try {
                                Class ptype = pd.getPropertyType();
                                Method gm = mdmap.get(ptype);
                                gm = gm != null ? gm : ResultSet.class.getMethod("getObject", String.class);
                                Expression exp = new Expression(rs, gm.getName(), new Object[]{name});
                                exp.execute();
                                wd.invoke(obj, exp.getValue());
                            } catch (Exception e) {
                                logger.debug(e.fillInStackTrace());
                            }
                        }
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                return obj;
            }
        };
    }
}

