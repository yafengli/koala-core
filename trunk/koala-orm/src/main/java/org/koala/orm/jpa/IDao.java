package org.koala.orm.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author liyafeng
 *         通用范例Dao 接口
 */
public interface IDao {

    /**
     * 根据主键查找对象
     *
     * @param id 主键
     * @return 实体
     */
    public <T, ID extends Serializable> T findById(Class<T> c, ID id);

    /**
     * 保存
     *
     * @param t 实体
     */
    public <T> void save(T t);

    /**
     * 删除
     *
     * @param t 实体
     */
    public <T> void remove(T t);

    /**
     * 修改
     *
     * @param t 实体
     */
    public <T> void update(T t);

    /**
     * 查找全部对象
     *
     * @return 查找全部对象
     */
    public <T> List<T> findAll(Class c);

    /**
     * 根据命名查询查找返回条数
     *
     * @param queryName 命名查询
     * @return 查询结果数目
     */
    public long findCount(String queryName);

    /**
     * 根据命名查询查找返回条数
     *
     * @param queryName 命名查询
     * @return 查询结果数目
     */
    public long findCount(String queryName, String[] paramNames, Object[] paramValues);

    /**
     * 根据命名查询查找返回条数
     *
     * @param queryName 命名查询
     * @return 查询结果数目
     */
    public long findCount(String queryName, Map<String, Object> paramMap);

    /**
     * 根据命名查询查找返回对象列表
     *
     * @param queryName
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName);

    /**
     * 根据命名查询查找返回固定条数的对象列表
     *
     * @param queryName     命名查询
     * @param startPosition 游标起始位置
     * @param maxResult     结果最大条数
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName, int startPosition, int maxResult);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     *
     * @param queryName   命名查询
     * @param paramNames  参数名数组
     * @param paramValues 参数值数组
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName, String[] paramNames, Object[] paramValues);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     *
     * @param queryName 命名查询
     * @param paramMap  参数名:参数值
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName, Map<String, Object> paramMap);

    /**
     * 根据命名查询和输入参数查找返回固定条数的对象列表
     *
     * @param queryName     命名查询
     * @param paramNames    参数名数组
     * @param paramValues   参数值数组
     * @param startPosition 游标起始位置
     * @param maxResult     结果最大条数
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName, String[] paramNames, Object[] paramValues, int startPosition, int maxResult);

    /**
     * 根据命名查询和输入参数查找返回固定条数的对象列表
     *
     * @param queryName     命名查询
     * @param startPosition 游标起始位置
     * @param maxResult     结果最大条数
     * @return 查询结果对象集合
     */
    public <T> List<T> find(String queryName, Map<String, Object> paramMap, int startPosition, int maxResult);

    /**
     * 根据命名查询查找返回对象列表
     *
     * @param queryName
     * @return 查询结果对象集合
     */
    public <T> T findSingle(String queryName);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     *
     * @param queryName   命名查询
     * @param paramNames  参数名数组
     * @param paramValues 参数值数组
     * @return 查询结果对象集合
     */
    public <T> T findSingle(String queryName, String[] paramNames, Object[] paramValues);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     *
     * @param queryName 命名查询
     * @param paramMap  参数名:参数值
     * @return 查询结果对象集合
     */
    public <T> T findSingle(String queryName, Map<String, Object> paramMap);

    /**
     * 批量插入
     *
     * @param objs 对象列表
     */

    public <T> void saveBatch(List<T> objs);

}