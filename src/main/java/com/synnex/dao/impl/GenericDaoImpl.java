package com.synnex.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synnex.dao.GenericDao;
import com.synnex.model.PageResult;

@Repository
public class GenericDaoImpl<T, PK> implements GenericDao<T, PK> {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	private Class<T> getClz() {
		if (entityClass == null) {
			// 获取泛型的Class对象
			entityClass = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
			System.out.println("entityClass：" + entityClass);
		}
		return entityClass;
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		logger.info("get");
		return (T) this.getSession().get(getClz(), (Serializable) id);
	}


	@Override
	public void save(T entity) {
		this.getSession().save(entity);
		logger.info("save");
	}

	@Override
	public void save(List<T> entitys) {
		for (T t : entitys) {
			save(t);
		}
	}

	@Override
	public void update(T entity) {
		logger.info("update");
		this.getSession().update(entity);
	}

	@Override
	public void update(List<T> entitys) {
		for (T t : entitys) {
			update(t);
		}
	}

	@Override
	public void delete(T entity) {
		logger.info("delete");
		this.getSession().delete(entity);
	}

	@Override
	public List<T> findByHql(String hql, Object... objects) {
		logger.info("findByHql");
		// 创建查询对象
		Query query = this.getSession().createQuery(hql);// Hql
		// 添加查询条件，参数
		if (null != objects && objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}


	@Override
	public PageResult<T> listPageResult(int begin, int size, String hql, Object... objects) {
		// 先查询count
		// 创建查询对象 查询 CountHql（总记录数）
		String cHql = getCountHql(hql);
		Query query = this.getSession().createQuery(cHql);
		// 添加查询条件，参数
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		Long count = (Long) query.uniqueResult();
		// 如果没有查询到数据，不用查baseQuery.getHql()
		if (count.intValue() == 0) {
			return new PageResult<T>();
		}
		final PageResult<T> pageResult = new PageResult<T>(begin, size, count.intValue());
		// 创建查询对象 查询数据
		Query queryDate = this.getSession().createQuery(hql);
		// 添加查询条件，参数
		for (int i = 0; i < objects.length; i++) {
			queryDate.setParameter(i, objects[i]);
		}
		// 处理分页:传入经过处理后的对象pageResult
		int first = (pageResult.getCurrentPage() - 1) * pageResult.getPageSize();
		int max = pageResult.getPageSize();
		queryDate.setFirstResult(first).setMaxResults(max);
		List<T> rows = queryDate.list();
		pageResult.setRows(rows);
		return pageResult;
	}

	private String getCountHql(String hql) {
		String s = hql.substring(0, hql.indexOf("from"));
		if (s == null || s.trim().equals("")) {
			hql = "select count(*) " + hql;
		} else {
			hql = hql.replace(s, "select count(*) ");
		}
		hql = hql.replace("fetch", "");
		return hql;
	}

}
