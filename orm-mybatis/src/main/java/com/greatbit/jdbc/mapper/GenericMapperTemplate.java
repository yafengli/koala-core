package com.greatbit.jdbc.mapper;

import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.List;

public abstract class GenericMapperTemplate implements IGenericMapperTemplate {

    @Override
    public <T> T getMapper(SqlSession session, Class<T> c) {
        return session.getMapper(c);
    }

    @Override
    public Collection<String> mappedStatementNames() {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            return session.getConfiguration().getMappedStatementNames();
        } finally {
            session.close();
        }
    }

    @Override
    public String mapperIdNs(String mapperid) {
        return getNamespace() + "." + mapperid;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, K> List<T> findList(String mapperId, K k, Class<T> c) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            if (k != null) {
                return session.selectList(mapperIdNs(mapperId), k);
            } else {
                return session.selectList(mapperIdNs(mapperId));
            }
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T, K> T findSingle(String mapperId, K k, Class<T> c) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            if (k != null) {
                return (T) session.selectOne(mapperIdNs(mapperId), k);
            } else {
                return (T) session.selectOne(mapperIdNs(mapperId));
            }
        } finally {
            session.close();
        }
    }

    @Override
    public <T> int update(String mapperId, T t) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            int flag = session.update(mapperIdNs(mapperId), t);
            session.commit();
            return flag;
        } finally {
            session.close();
        }
    }

    @Override
    public <T> int delete(String mapperId, T t) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            int flag = session.delete(mapperIdNs(mapperId), t);
            session.commit();
            return flag;
        } finally {
            session.close();
        }
    }

    @Override
    public <T> int insert(String mapperId, T t) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            int flag = session.insert(mapperIdNs(mapperId), t);
            session.commit();
            return flag;
        } finally {
            session.close();
        }
    }

    @Override
    public <T, K> K execute(ISessionCall call, T t, Class<K> c) {
        SqlSession session = getsSqlSessionFactory().openSession();
        K k = null;
        try {
            k = call.call(session, t, c);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return k;
    }

    @Override
    public <T> void execute(ISessionCall call, T t) {
        SqlSession session = getsSqlSessionFactory().openSession();
        try {
            call.call(session, t);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }
}
