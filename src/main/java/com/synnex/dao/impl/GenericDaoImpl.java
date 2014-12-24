package com.synnex.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synnex.dao.GenericDao;
import com.synnex.dao.Order;
import com.synnex.model.PageResult;
import com.synnex.query.BaseQuery;

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
	public List<T> list(Object condition, List<Order> orders, int begin, int size) {
		Criteria criteria = this.getSession().createCriteria(getClz());
		if (condition != null) {
			// criteria.add(Example.create(condition).excludeZeroes());
			criteria.add(Example.create(condition));
		}
		if (orders != null) {
			for (Order order : orders) {
				if (order.isAsc()) {
					criteria.addOrder(org.hibernate.criterion.Order.asc(order.getField()));
				} else {
					criteria.addOrder(org.hibernate.criterion.Order.desc(order.getField()));
				}
			}
		}
		if (begin >= 0) {
			criteria.setFirstResult(begin).setMaxResults(size);
		}
		List<T> tlist = criteria.list();
		logger.info(tlist.toString());
		return tlist;
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
	public int getTotolCount() {
		logger.info("getTotolCount");
		return list(null, null, -1, 0).size();
	}

	@Override
	public PageResult<T> listPageResult(BaseQuery baseQuery) {
		// 先查询count
		// 创建查询对象 查询 CountHql（总记录数）
		Query query = this.getSession().createQuery(baseQuery.getCountHql());
		// 添加查询条件，参数
		for (int i = 0; i < baseQuery.getParamList().size(); i++) {
			query.setParameter(i, baseQuery.getParamList().get(i));
		}
		Long count = (Long) query.uniqueResult();
		// 如果没有查询到数据，不用查baseQuery.getHql()
		if (count.intValue() == 0) {
			return new PageResult<T>();
		}
		final PageResult<T> pageResult = new PageResult<T>(baseQuery.getCurrentPage(), baseQuery.getPageSize(), count.intValue());
		// 创建查询对象 查询数据
		Query queryDate = this.getSession().createQuery(baseQuery.getHql());
		// 添加查询条件，参数
		for (int i = 0; i < baseQuery.getParamList().size(); i++) {
			queryDate.setParameter(i, baseQuery.getParamList().get(i));
		}
		// 处理分页:传入经过处理后的对象pageResult
		int first = (pageResult.getCurrentPage() - 1) * pageResult.getPageSize();
		int max = pageResult.getPageSize();
		queryDate.setFirstResult(first).setMaxResults(max);
		List<T> rows = queryDate.list();
		pageResult.setRows(rows);
		return pageResult;
	}

}
