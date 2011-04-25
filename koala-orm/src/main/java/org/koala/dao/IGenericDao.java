package org.koala.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author liyafeng
 * 通用范例Dao 接口
 */
public interface IGenericDao<T, KeyId extends Serializable> {

    /**
     * 根据主键查找对象
     * @param id 主键
     * @return 对象实体
     */
    public T findById(KeyId id);

    /**
     * 保存对象
     * @param t 实体
     */
    public void save(T t);

    /**
     * 删除对象
     * @param t 对象
     */
    public void remove(T t);

    /**
     * 修改对象
     * @param t 对象
     */
    public void update(T t);

    /**
     * 查找全部对象
     * @return 查找全部对象
     */
    public List<T> findAll();

    /**
     * 根据命名查询查找返回对象列表
     * @param queryName
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName);

    /**
     * 根据命名查询查找返回固定条数的对象列表
     * @param queryName 命名查询
     * @param startPosition 游标起始位置
     * @param maxResult 结果最大条数
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName, int startPosition, int maxResult);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     * @param queryName 命名查询
     * @param paramNames 参数名数组
     * @param paramValues 参数值数组
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName, String[] paramNames,
            Object[] paramValues);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     * @param queryName　命名查询
     * @param paramMap　参数名:参数值
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName, Map<String, Object> paramMap);

    /**
     * 根据命名查询和输入参数查找返回固定条数的对象列表
     * @param queryName　命名查询
     * @param paramNames　参数名数组
     * @param paramValues　参数值数组
     * @param startPosition　游标起始位置
     * @param maxResult　结果最大条数
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName, String[] paramNames,
            Object[] paramValues, int startPosition, int maxResult);

    /**
     * 根据命名查询和输入参数查找返回固定条数的对象列表
     * @param queryName　命名查询
     * @param startPosition　游标起始位置
     * @param maxResult　结果最大条数
     * @return 查询结果对象集合
     */
    public List<T> find(String queryName, Map<String, Object> paramMap,
            int startPosition, int maxResult);

    /**
     * 根据命名查询查找返回对象列表
     * @param queryName
     * @return 查询结果对象集合
     */
    public T findSingle(String queryName);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     * @param queryName　命名查询
     * @param paramNames　参数名数组
     * @param paramValues 参数值数组
     * @return 查询结果对象集合
     */
    public T findSingle(String queryName, String[] paramNames,
            Object[] paramValues);

    /**
     * 根据命名查询和输入参数查找返回的对象列表
     * @param queryName　命名查询
     * @param paramMap　参数名:参数值
     * @return 查询结果对象集合
     */
    public T findSingle(String queryName, Map<String, Object> paramMap);

    /**
     * 根据命名查询/输入参数/返回结果类型，查找返回的对象列表
     * @param queryName　命名查询
     * @param paramMap　参数名:参数值
     * @param resultType 返回结果类型
     * @return 返回结果
     */
    public <K> K findByType(String queryName, Map<String, Object> paramMap, Class<K> resultType);

    /**
     * 批量插入
     * @param objs　对象列表
     */
    public void saveBatch(List<T> objs);

    public <K> K executeNative(final String nativeSql, final Map<String, Object> paramMap, final Class<K> resultType);

    public <K> K executeNativeByNamed(final String queryName, final Map<String, Object> paramMap, final Class<K> resultType);
}
