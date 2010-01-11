package org.koala.dao.jdbc;

import java.io.Serializable;
import java.util.List;

import java.util.Map;

import org.koala.spring.support.SqlCreatorUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YaFengLi
 */
@Transactional
public abstract class GenericDaoJDBC<T, ID extends Serializable> extends SimpleJdbcDaoSupport implements IGenericJDBCDao<T, ID> {

    private SimpleJdbcCall sjc;
    private SqlCreatorUtils scu;

    public int remove(String sql, T t) {
        return update(sql, t);
    }

    public int save(String sql, T t) {
        return update(sql, t);
    }

    public int update(String sql, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        return this.getSimpleJdbcTemplate().update(sql, sps);
    }

    public T findForObject(String sql, Object[] args) {
        return this.getSimpleJdbcTemplate().queryForObject(sql, getRowMapper(), args);
    }

    public T findForObject(String sql, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        return this.getSimpleJdbcTemplate().queryForObject(sql, getRowMapper(), sps);
    }

    public List<T> find(String sql) {
        T t = null;
        return this.find(sql, t);
    }

    public List<T> find(String sql, T t) {
        SqlParameterSource sps = null;
        if (t != null) {
            sps = new BeanPropertySqlParameterSource(t);
        }
        return this.getSimpleJdbcTemplate().query(sql, getRowMapper(), sps);
    }

    public List<T> find(String sql, Object... args) {
        return this.getSimpleJdbcTemplate().query(sql, getRowMapper(), args);
    }

    public Map<String, Object> executeFunction(String functionName, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        this.getSimpleJdbcCall().withFunctionName(functionName);
        return this.getSimpleJdbcCall().execute(sps);
    }

    public Map<String, Object> executeProcedure(String procedureName, T t) {
        SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        this.getSimpleJdbcCall().withProcedureName(procedureName);
        return this.getSimpleJdbcCall().execute(sps);
    }

    public SimpleJdbcCall getSimpleJdbcCall() {
        if (sjc == null) {
            sjc = new SimpleJdbcCall(this.getJdbcTemplate());
        }
        return sjc;
    }

    public SqlCreatorUtils getScu() {
        return scu;
    }

    public void setScu(SqlCreatorUtils scu) {
        this.scu = scu;
    }

    public void execute(String sql) {
        this.getJdbcTemplate().execute(sql);
    }

    public void execute(final String sql, T t) {
        final SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
        final SqlCreatorUtils fscu = scu;
        String sqlToUse = fscu.createSql(sql, sps);
        this.execute(sqlToUse);
    }
}
