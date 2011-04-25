package org.koala.dao.jdbc;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * 针对JDBC通用泛型DAO接口
 * @author liyafeng
 * @since 1.0
 */
public interface IJDBCDao {

    /**
     * 保存对象
     * @param t 对象
     */
    public <T> int save(String sql, T t);

    /**
     * 删除对象
     * @param t 对象
     */
    public <T> int remove(String sql, T t);

    /**
     * 修改对象
     * @param t 对象
     */
    public <T> int update(String sql, T t);

    /**
     * 执行给定的SQL
     * @param sql 执行sql字串
     */
    public void execute(String sql);

    /**
     * 执行给定SQL，以类型为T的对象为入参
     * @param <T> 对象类型
     * @param sql 执行sql字串
     * @param t 对象
     */
    public <T> void execute(String sql, T t);

    /**
     * 调用内置函数
     * @param functionName 数据库内置函数名
     * @param t            对象实体
     * @return
     */
    public <T> Map<String, Object> executeFunction(String functionName, T t);

    /**
     * 调用存储过程
     * @param procedureName 存储过程名
     * @param t             设置查询参数的对象实体
     * @return
     */
    public <T> Map<String, Object> executeProcedure(String procedureName, T t);

    /**
     * 根据主键查找对象
     * @param sql  SQL语句
     * @param args 参数集合
     * @return 实体
     */
    public <T> T findForObject(String sql, Class<T> c, Object... args);

    /**
     * 根据主键查找对象
     * @param t 设置查询内容的对象实体
     * @return 实体
     */
    public <T> T findForObject(String sql, T t, Class<T> c);

    /**
     * 以T类型的对象t为入参，执行SQL命令
     * @param <T> 对象类型
     * @param sql 执行sql字串
     * @param c 对象类型类对象
     * @return 查询集合
     */
    public <T> List<T> find(String sql, Class<T> c);

    /**
     * 以T类型的对象t为入参，执行SQL命令
     * @param <T> 对象类型
     * @param sql 执行sql字串
     * @param t 对象
     * @return 查询集合
     */
    public <T> List<T> find(String sql, T t);

    /**
     * 
     * @param <T> 对象类型
     * @param sql 执行sql字串
     * @param c 对象类型类对象
     * @param args 查询参数
     * @return 查询集合
     */
    public <T> List<T> find(String sql, Class<T> c, Object... args);

    /**
     * 设置数据库字段与查询对象的属性之间映射关系
     * @return rowMapper
     */
    public <T> RowMapper<T> getRowMapper(Class<T> c);

    /**
     * 设置处理存储过程的工具SimpleJdbcCall
     * @return
     */
    public SimpleJdbcCall getSimpleJdbcCall();
}