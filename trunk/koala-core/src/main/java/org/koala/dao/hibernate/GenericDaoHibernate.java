package org.koala.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.koala.dao.IGenericDao;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
public class GenericDaoHibernate<T, ID extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, ID> {

    private Class persistentClass;

    public GenericDaoHibernate() {
        this.persistentClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class getPersistentClass() {
        return persistentClass;
    }

    public List<T> findAll() {
        return getHibernateTemplate().loadAll(this.getPersistentClass());
    }

    public T findById(ID id) {
        T entity = null;
        if (id != null) {
            entity = (T) getHibernateTemplate().load(this.getPersistentClass(),
                    id);
        }
        return entity;
    }

    public List<T> findByQueryName(final String queryName, final int startPosition,
            final int maxResult) {
        return this.findByQueryName(queryName, null, null, startPosition, maxResult);
    }

    public List<T> findByQueryName(final String queryName, final String[] paramNames,
            final Object[] paramValues, final int startPosition, final int maxResult) {

        return (List<T>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.getNamedQuery(queryName);
                if (paramNames != null && paramValues != null && paramNames.length == paramValues.length) {
                    for (int i = 0; i < paramNames.length; i++) {
                        query.setParameter(paramNames[i], paramValues[i]);
                    }
                }
                if (startPosition >= 0) {
                    query.setFirstResult(startPosition);
                }
                if (maxResult >= 0) {
                    query.setMaxResults(maxResult);
                }
                return query.list();
            }
        });
    }

    public List<T> findByQueryName(String queryName, String[] paramNames,
            Object[] paramValues) {

        return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
                paramNames, paramValues);
    }

    public List<T> findByQueryName(String queryName) {

        return getHibernateTemplate().findByNamedQuery(queryName);
    }

    public long findCountByQueryName(String queryName, String[] paramNames,
            Object[] paramValues) {
        List l = getHibernateTemplate().findByNamedQueryAndNamedParam(
                queryName, paramNames, paramValues);
        if (l != null && l.size() == 1 && l.get(0) instanceof Long) {
            Long size = (Long) l.get(0);
            return size.longValue();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public long findCountByQueryName(String queryName) {
        List l = getHibernateTemplate().findByNamedQuery(queryName);
        if (l != null && l.size() == 1 && l.get(0) instanceof Long) {
            Long size = (Long) l.get(0);
            return size.longValue();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void remove(T t) {
        getHibernateTemplate().delete(t);

    }

    public void save(T t) {
        getHibernateTemplate().persist(t);

    }

    public void saveBatch(final List<T> objs) {

        getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
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
}
