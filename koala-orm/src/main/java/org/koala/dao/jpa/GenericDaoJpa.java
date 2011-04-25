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
 */
@Transactional
public abstract class GenericDaoJpa<T, ID extends Serializable> extends JpaDaoSupport
        implements IGenericDao<T, ID> {

    private Class persistentClass;

    public GenericDaoJpa() {
        this.persistentClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class getPersistentClass() {
        return persistentClass;
    }

    public List<T> findAll() {
        return getJpaTemplate().find("from " + this.getPersistentClass().getName());
    }

    public T findById(ID id) {
        T entity;
        if (id != null) {
            entity = (T) getJpaTemplate().find(this.getPersistentClass(), id);

        } else {
            entity = null;
        }
        return entity;
    }

    public void remove(T t) {
        getJpaTemplate().remove(getJpaTemplate().merge(t));

    }

    public void save(T t) {
        getJpaTemplate().persist(t);
    }

    public void update(T t) {
        getJpaTemplate().merge(t);
    }

    public List<T> findByQueryName(String queryName) {
        return getJpaTemplate().findByNamedQuery(queryName);
    }

    public List<T> findByQueryName(String queryName, String[] paramNames,
            Object[] paramValues) {
        if (paramNames.length != paramValues.length) {
            throw new IllegalArgumentException();
        }
        Map<String, Object> map = new java.util.HashMap<String, Object>(
                paramNames.length);
        for (int i = 0; i < paramNames.length; ++i) {
            map.put(paramNames[i], paramValues[i]);
        }
        return getJpaTemplate().findByNamedQueryAndNamedParams(queryName, map);
    }

    public List<T> findByQueryName(final String queryName,
            final int startPosition, final int maxResult) {
        return getJpaTemplate().executeFind(
                new JpaCallback() {

                    public Object doInJpa(EntityManager em) throws PersistenceException {
                        Query query = em.createNamedQuery(queryName);
                        query.setFirstResult(startPosition);
                        query.setMaxResults(maxResult);
                        return query.getResultList();
                    }
                });
    }

    public List<T> findByQueryName(final String queryName, final String[] paramNames, final Object[] paramValues, final int startPosition, final int maxResult) {
        if (paramNames.length != paramValues.length) {
            throw new IllegalArgumentException();
        }
        return getJpaTemplate().executeFind(new JpaCallback() {

            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                query.setFirstResult(startPosition);
                query.setMaxResults(maxResult);
                for (int i = 0; i < paramNames.length; i++) {
                    query.setParameter(paramNames[i], paramValues[i]);
                }
                return query.getResultList();
            }
        });
    }

    public long findCountByQueryName(final String queryName) {

        List ll = getJpaTemplate().executeFind(new JpaCallback() {

            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                return query.getResultList();
            }
        });
        if (ll.size() == 1 && ll.get(0) instanceof Long) {
            Long size = (Long) ll.get(0);
            return size.longValue();
        } else {
            return -1;
        }
    }

    public long findCountByQueryName(final String queryName, final String[] paramNames,
            final Object[] paramValues) {

        List ll = getJpaTemplate().executeFind(new JpaCallback() {

            public Object doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                for (int i = 0; i < paramNames.length; i++) {
                    query.setParameter(paramNames[i], paramValues[i]);
                }
                return query.getResultList();
            }
        });
        if (ll.size() == 1 && ll.get(0) instanceof Long) {
            Long size = (Long) ll.get(0);
            return size.longValue();
        } else {
            return -1;
        }
    }

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
            return (T) getJpaTemplate().execute(new JpaCallback() {

                public Object doInJpa(EntityManager em) throws PersistenceException {
                    Query query = em.createNamedQuery(queryName);
                    if (paramMap != null && paramMap.size() >= 0) {
                        for (String key : paramMap.keySet()) {
                            Object val = paramMap.get(key);
                            query.setParameter(key, val);
                        }
                    } else {
                        logger.error("[paramMap is null!]");
                    }
                    return query.getSingleResult();
                }
            });
        } catch (Exception e) {
            return null;
        }       
    }
}
