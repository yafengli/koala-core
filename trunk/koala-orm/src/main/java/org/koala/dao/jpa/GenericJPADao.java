package org.koala.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.koala.dao.IGenericDao;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyafeng
 * <p>JPA泛型DAO接口</p>
 * @since 1.0
 */
@Transactional
public abstract class GenericJPADao<T, ID extends Serializable> extends JpaDaoSupport
        implements IGenericDao<T, ID> {

    private Class persistentClass;

    public GenericJPADao() {
        this.persistentClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class getPersistentClass() {
        return persistentClass;
    }

    @Override
    public List<T> findAll() {
        return getJpaTemplate().find("from " + this.getPersistentClass().getName());
    }

    @Override
    public T findById(ID id) {
        T entity;
        if (id != null) {
            entity = (T) getJpaTemplate().find(this.getPersistentClass(), id);

        } else {
            entity = null;
        }
        return entity;
    }

    @Override
    public void remove(T t) {
        getJpaTemplate().remove(getJpaTemplate().merge(t));

    }

    @Override
    public void save(T t) {
        getJpaTemplate().persist(t);
    }

    @Override
    public void update(T t) {
        getJpaTemplate().merge(t);
    }

    @Override
    public List<T> find(String queryName) {
        return getJpaTemplate().findByNamedQuery(queryName);
    }

    @Override
    public List<T> find(String queryName, String[] paramNames,
            Object[] paramValues) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        if (paramNames == null || paramValues == null || paramNames.length != paramValues.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], paramValues[i]);
        }
        return find(queryName, paramMap, -1, -1);
    }

    @Override
    public List<T> find(final String queryName,
            final int startPosition, final int maxResult) {
        return find(queryName, null, startPosition, maxResult);
    }

    @Override
    public List<T> find(final String queryName, final String[] paramNames, final Object[] paramValues, final int startPosition, final int maxResult) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        if (paramNames == null || paramValues == null || paramNames.length != paramValues.length) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], paramValues[i]);
        }
        return find(queryName, paramMap, startPosition, maxResult);
    }

    @Override
    public List<T> find(String queryName, Map<String, Object> paramMap) {
        return find(queryName, paramMap, -1, -1);
    }

    @Override
    public List<T> find(final String queryName, final Map<String, Object> paramMap, final int startPosition, final int maxResult) {

        return getJpaTemplate().executeFind(new JpaCallback< List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);

                if (paramMap != null) {
                    for (String key : paramMap.keySet()) {
                        query.setParameter(key, paramMap.get(key));
                    }
                }
                if (startPosition >= 0 && maxResult > 0) {
                    query.setFirstResult(startPosition);
                    query.setMaxResults(maxResult);
                }
                return query.getResultList();
            }
        });
    }

    @Override
    public void saveBatch(final List<T> objs) {
        getJpaTemplate().execute(new JpaCallback() {

            public Object doInJpa(EntityManager em) throws PersistenceException {
                try {
                    int i = 1;
                    for (T sl : objs) {
                        em.persist(sl);
                        if (i % 50 == 0 || i == objs.size()) {
                            em.flush();
                            em.clear();
                        }
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    @Override
    public T findSingle(String queryName) {
        return findSingle(queryName, null);
    }

    @Override
    public T findSingle(String queryName, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (paramNames != null && paramValues != null && paramNames.length == paramValues.length) {
            for (int i = 0; i < paramNames.length; i++) {
                map.put(paramNames[i], paramValues[i]);
            }
        } else {
            logger.error("[paramNames.length is not equal paramValues.length!]");
        }
        return findSingle(queryName, map);
    }

    @Override
    public T findSingle(final String queryName, final Map<String, Object> paramMap) {
        try {
            return getJpaTemplate().execute(new JpaCallback<T>() {

                @Override
                public T doInJpa(EntityManager em) throws PersistenceException {
                    Query query = em.createNamedQuery(queryName);
                    if (paramMap != null && paramMap.size() >= 0) {
                        for (String key : paramMap.keySet()) {
                            Object val = paramMap.get(key);
                            query.setParameter(key, val);
                        }
                    } else {
                        logger.error("[paramMap is null!]");
                    }
                    return (T) query.getSingleResult();
                }
            });
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <K> K findByType(final String queryName, final Map<String, Object> paramMap, final Class<K> resultType) {
        return getJpaTemplate().execute(new JpaCallback<K>() {

            @Override
            public K doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                if (paramMap != null && paramMap.size() >= 0) {
                    for (String key : paramMap.keySet()) {
                        Object val = paramMap.get(key);
                        query.setParameter(key, val);
                    }
                } else {
                    logger.error("[paramMap is null!]");
                }
                return (K) query.getSingleResult();
            }
        });
    }
}
