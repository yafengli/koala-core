package org.koala.dao.jpa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.koala.dao.IDao;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Date: 2009-9-24 Time: 14:59:21
 * 
 * @version 1.0
 * @authtor YaFengLi
 */
@Transactional
public class BaseJPADao extends JpaDaoSupport implements IDao {

    @Override
    public <T, ID extends Serializable> T findById(ID id, Class<T> c) {
        T entity;
        if (id != null) {
            entity = (T) getJpaTemplate().find(c, id);
        } else {
            entity = null;
        }
        return entity;
    }

    @Override
    public <T> void save(T t) {
        getJpaTemplate().persist(t);
    }

    @Override
    public <T> void remove(T t) {
        getJpaTemplate().remove(getJpaTemplate().merge(t));
    }

    @Override
    public <T> void update(T t) {
        getJpaTemplate().merge(t);
    }

    @Override
    public <T> List<T> findAll(final Class<T> c) {
        return getJpaTemplate().execute(new JpaCallback<List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(c);
                cq.select(cq.from(c));
                return em.createQuery(cq).getResultList();
            }
        });
    }

    @Override
    public <T> List<T> find(String queryName, Class<T> c) {
        return find(queryName, null, null, -1, -1, c);
    }

    @Override
    public <T> List<T> find(final String queryName, final int startPosition,
            final int maxResult, Class<T> c) {
        return find(queryName, null, null, startPosition, maxResult, c);
    }

    @Override
    public <T> List<T> find(final String queryName, final String[] paramNames,
            final Object[] paramValues, Class<T> c) {
        return find(queryName, paramNames, paramValues, -1, -1, c);
    }

    @Override
    public <T> List<T> find(final String queryName, final String[] paramNames,
            final Object[] paramValues, final int startPosition,
            final int maxResult, Class<T> c) {
        return getJpaTemplate().executeFind(new JpaCallback<List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                if (startPosition >= 0 && maxResult >= startPosition) {
                    query.setFirstResult(startPosition);
                    query.setMaxResults(maxResult);
                }
                if (paramNames != null && paramValues != null
                        && paramNames.length == paramValues.length) {
                    for (int i = 0; i < paramNames.length; i++) {
                        query.setParameter(paramNames[i], paramValues[i]);
                    }
                } else {
                    logger.error("[paramNames.length is not equal paramValues.length!]");
                }
                return query.getResultList();
            }
        });
    }

    @Override
    public <T> void saveBatch(final List<T> objs) {
        getJpaTemplate().execute(new JpaCallback< List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
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
    public <T> List<T> find(String queryName, Map<String, Object> paramMap,
            Class<T> c) {
        return find(queryName, paramMap, -1, -1, c);
    }

    @Override
    public <T> List<T> find(final String queryName,
            final Map<String, Object> paramMap, final int startPosition,
            final int maxResult, Class<T> c) {
        return getJpaTemplate().executeFind(new JpaCallback< List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                Query query = em.createNamedQuery(queryName);
                if (startPosition >= 0 && maxResult >= startPosition) {
                    query.setFirstResult(startPosition);
                    query.setMaxResults(maxResult);
                }
                if (paramMap != null && paramMap.size() >= 0) {
                    for (String key : paramMap.keySet()) {
                        Object val = paramMap.get(key);
                        query.setParameter(key, val);
                    }
                } else {
                    logger.error("[paramMap is null!]");
                }
                return query.getResultList();
            }
        });
    }

    @Override
    public <T> T findSingle(String queryName, Class<T> c) {
        return (T) findSingle(queryName, null, c);
    }

    @Override
    public <T> T findSingle(String queryName, String[] paramNames,
            Object[] paramValues, Class<T> c) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (paramNames != null && paramValues != null
                && paramNames.length == paramValues.length) {
            for (int i = 0; i < paramNames.length; i++) {
                map.put(paramNames[i], paramValues[i]);
            }
        } else {
            logger.error("[paramNames.length is not equal paramValues.length!]");
        }
        return (T) findSingle(queryName, map, c);
    }

    @Override
    public <T> T findSingle(final String queryName,
            final Map<String, Object> paramMap, Class<T> c) {
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
}
