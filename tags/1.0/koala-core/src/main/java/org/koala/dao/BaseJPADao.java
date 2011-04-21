package org.koala.dao;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Date: 2009-9-24 Time: 14:59:21
 * 
 * @version 1.0
 * @authtor YaFengLi
 */
@Transactional
public class BaseJPADao extends JpaDaoSupport implements IDao {
	@Override
	public <T, ID extends Serializable> T findById(Class<T> c, ID id) {
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
	public <T> List<T> findAll(Class<T> c) {
		return getJpaTemplate().find(String.format("from %s", c.getName()));
	}

	@Override
	public long findCount(final String queryName) {
		return findCount(queryName, null, null);
	}

	@Override
	public long findCount(final String queryName, final String[] paramNames,
			final Object[] paramValues) {
		List ll = find(queryName, paramNames, paramValues);
		if (ll != null && ll.size() == 1) {
			Object obj = ll.get(0);
			if (obj instanceof Long) {
				return (Long) ll.get(0);
			} else if (obj instanceof Integer) {
				return Long.valueOf(obj.toString());
			}
		}
		return 0;
	}

	@Override
	public <T> List<T> find(String queryName) {
		return find(queryName, null, null, -1, -1);
	}

	@Override
	public <T> List<T> find(final String queryName, final int startPosition,
			final int maxResult) {
		return find(queryName, null, null, startPosition, maxResult);
	}

	@Override
	public <T> List<T> find(final String queryName, final String[] paramNames,
			final Object[] paramValues) {
		return find(queryName, paramNames, paramValues, -1, -1);
	}

	@Override
	public <T> List<T> find(final String queryName, final String[] paramNames,
			final Object[] paramValues, final int startPosition,
			final int maxResult) {
		return getJpaTemplate().executeFind(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
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

	public <T> void saveBatch(final List<T> objs) {
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
	public long findCount(String queryName, Map<String, Object> paramMap) {
		List ll = find(queryName, paramMap, -1, -1);
		if (ll != null && ll.size() == 1) {
			Object obj = ll.get(0);
			if (obj instanceof Long) {
				return (Long) obj;
			} else if (obj instanceof Integer) {
				return Long.valueOf(obj.toString());
			}
		}
		return 0;
	}

	@Override
	public <T> List<T> find(String queryName, Map<String, Object> paramMap) {
		return find(queryName, paramMap, -1, -1);
	}

	@Override
	public <T> List<T> find(final String queryName,
			final Map<String, Object> paramMap, final int startPosition,
			final int maxResult) {
		return getJpaTemplate().executeFind(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
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

	public <T> T findSingle(String queryName) {
		return (T) findSingle(queryName, null);
	}

	@Override
	public <T> T findSingle(String queryName, String[] paramNames,
			Object[] paramValues) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (paramNames != null && paramValues != null
				&& paramNames.length == paramValues.length) {
			for (int i = 0; i < paramNames.length; i++) {
				map.put(paramNames[i], paramValues[i]);
			}
		} else {
			logger.error("[paramNames.length is not equal paramValues.length!]");
		}
		return (T) findSingle(queryName, map);
	}

	@Override
	public <T> T findSingle(final String queryName,
			final Map<String, Object> paramMap) {
		try {
			return  getJpaTemplate().execute(new JpaCallback<T>() {

				public T doInJpa(EntityManager em)
						throws PersistenceException {
					Query query = em.createNamedQuery(queryName);
					if (paramMap != null && paramMap.size() >= 0) {
						for (String key : paramMap.keySet()) {
							Object val = paramMap.get(key);
							query.setParameter(key, val);
						}
					} else {
						logger.error("[paramMap is null!]");
					}
					return (T)query.getSingleResult();
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
}
