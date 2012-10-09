package com.greatbit.jdbc.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;
import java.util.List;

public interface IGenericMapperTemplate {

    public SqlSessionFactory getsSqlSessionFactory();

    public String getNamespace();

    public void setNamespace(String namespace);

    public Collection<String> mappedStatementNames();

    public String mapperIdNs(String mapperid);

    public <T, K> List<T> findList(String mapperId, K k, Class<T> c);

    public <T, K> T findSingle(String mapperId, K k, Class<T> c);

    public <T> int update(String mapperId, T t);

    public <T> int delete(String mapperId, T t);

    public <T> int insert(String mapperId, T t);

    public <T, K> K execute(ISessionCall call, T t, Class<K> c);

    public <T> void execute(ISessionCall call, T t);

    public <T> T getMapper(SqlSession session, Class<T> c);
}
