package com.greatbit.jdbc.mapper;

import org.apache.ibatis.session.SqlSession;

public interface ISessionCall {
    public <T, K> K call(SqlSession session, T t, Class<K> c) throws Exception;

    public <T> void call(SqlSession session, T t) throws Exception;

}
