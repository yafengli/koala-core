package org.koala.dao.jdbc;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.List;
import java.util.Map;

/**
 * @author liyafeng
 *         通用范例Dao 接口
 */
public interface IJDBCDao {

    /**
     * 保存
     *
     * @param t 实体
     */
    public <T> int save(String sql, T t);

    /**
     * 删除
     *
     * @param t 实体
     */
    public <T> int remove(String sql, T t);

    /**
     * 修改
     *
     * @param t 实体
     */
    public <T> int update(String sql, T t);

    /**
     * @param sql 执行SQL
     */
    public void execute(String sql);

    /**
     * @param sql 执行SQL
     */
    public <T> void execute(String sql, T t);

    /**
     * 调用内置函数
     *
     * @param functionName 数据库内置函数名
     * @param t            对象实体
     * @return
     */
    public <T> Map<String, Object> executeFunction(String functionName, T t);

    /**
     * 调用存储过程
     *
     * @param procedureName 存储过程名
     * @param t             设置查询参数的对象实体
     * @return
     */
    public <T> Map<String, Object> executeProcedure(String procedureName, T t);

    /**
     * 根据主键查找对象
     *
     * @param sql  SQL语句
     * @param args 参数集合
     * @return 实体
     */
    public <T> T findForObject(String sql, Class<T> c, Object... args);

    /**
     * 根据主键查找对象
     *
     * @param t 设置查询内容的对象实体
     * @return 实体
     */
    public <T> T findForObject(String sql, T t, Class<T> c);

    /**
     * 查找全部对象
     *
     * @return 查找全部对象
     */
    public <T> List<T> find(String sql, Class<T> c);

    /**
     * @param sql
     * @param t
     * @return
     */
    public <T> List<T> find(String sql, T t);

    /**
     * @param sql
     * @param args
     * @return
     */
    public <T> List<T> find(String sql, Class<T> c, Object... args);

    /**
     * 设置数据库字段与查询对象的属性之间映射关系
     *
     * @return
     */
    public <T> ParameterizedRowMapper<T> getRowMapper(Class<T> c);

    /**
     * 设置处理存储过程的工具SimpleJdbcCall
     *
     * @return
     */
    public SimpleJdbcCall getSimpleJdbcCall();

}