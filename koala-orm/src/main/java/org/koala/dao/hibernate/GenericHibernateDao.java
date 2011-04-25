package org.koala.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.koala.dao.IGenericDao;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericHibernateDao<T, ID extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, ID> {

    private Class persistentClass;

    public GenericHibernateDao() {
        this.persistentClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class getPersistentClass() {
        return persistentClass;
    }

    @Override
    public List<T> findAll() {
        return getHibernateTemplate().loadAll(this.getPersistentClass());
    }

    @Override
    public T findById(ID id) {
        T entity = null;
        if (id != null) {
            entity = (T) getHibernateTemplate().load(this.getPersistentClass(),
                    id);
        }
        return entity;
    }

    @Override
    public List<T> find(final String queryName, final int startPosition,
            final int maxResult) {
        return find(queryName, null, startPosition, maxResult);
    }

    @Override
    public List<T> find(final String queryName, final String[] paramNames,
            final Object[] paramValues, final int startPosition, final int maxResult) {

        if (paramNames == null || paramValues == null || paramNames.length != paramValues.length) {
            throw new IllegalArgumentException();
        }
        final Map<String, Object> paramMap = new HashMap<String, Object>();

        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], paramValues[i]);
        }
        return find(queryName, paramMap, startPosition, maxResult);
    }

    @Override
    public List<T> find(final String queryName, final Map<String, Object> paramMap) {
        return find(queryName, paramMap, -1, -1);
    }

    @Override
    public List<T> find(final String queryName, final Map<String, Object> paramMap, final int startPosition, final int maxResult) {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.getNamedQuery(queryName);
                if (paramMap != null) {
                    for (String key : paramMap.keySet()) {
                        query.setParameter(key, paramMap.get(key));
                    }
                }
                if (startPosition >= 0 && maxResult > 0) {
                    query.setFirstResult(startPosition);
                    query.setMaxResults(maxResult);
                }
                return query.list();
            }
        });
    }

    @Override
    public List<T> find(String queryName, String[] paramNames,
            Object[] paramValues) {

        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
                paramNames, paramValues);
    }

    @Override
    public List<T> find(String queryName) {

        return getHibernateTemplate().findByNamedQuery(queryName);
    }

    @Override
    public void remove(T t) {
        getHibernateTemplate().delete(t);

    }

    @Override
    public void save(T t) {
        getHibernateTemplate().persist(t);

    }

    @Override
    public void saveBatch(final List<T> objs) {

        getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {

            @Override
            public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
                int i = 1;
                for (T t : objs) {
                    session.persist(t);
                    if (i % 50 == 0 || i == objs.size()) {
                        session.flush();
                        session.clear();
                    }
                    i++;
                }
                return null;
            }
        });
    }

    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public T findSingle(String queryName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T findSingle(String queryName, String[] paramNames, Object[] paramValues) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T findSingle(String queryName, Map<String, Object> paramMap) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <K> K findByType(final String queryName, final Map<String, Object> paramMap, final Class<K> resultType) {
        return getHibernateTemplate().execute(new HibernateCallback<K>() {

            @Override
            public K doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.getNamedQuery(queryName);
                return (K) query.uniqueResult();
            }
        });
    }
}
