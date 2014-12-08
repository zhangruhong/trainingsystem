package com.synnex.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synnex.dao.GenericDao;
import com.synnex.dao.Order;

@Repository
public class GenericDaoImpl<T, PK> implements GenericDao<T, PK> {
	@Autowired
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass ;
	
	//TODO 此处需要问问Jennifer
	@SuppressWarnings("unchecked")
	public Class<T> getClz() {
		if (entityClass == null) {
			// 获取泛型的Class对象
			entityClass = ((Class<T>) (((ParameterizedType) (this.getClass()
					.getGenericSuperclass())).getActualTypeArguments()[0]));
			System.out.println("entityClass："+entityClass);
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
		return (T) this.getSession().get(getClz(), (Serializable) id);
	}

	@Override
	public List<T> list(Object condition, List<Order> orders, int begin,
			int size) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		if (condition != null) {
			criteria.add(Example.create(condition).excludeZeroes());
		}
		if (orders != null) {
			for (Order order : orders) {
				if (order.isAsc()) {
					criteria.addOrder(org.hibernate.criterion.Order.asc(order
							.getField()));
				} else {
					criteria.addOrder(org.hibernate.criterion.Order.desc(order
							.getField()));
				}
			}
		}
		if (begin >= 0) {
			criteria.setFirstResult(begin).setMaxResults(size);
		}
		return criteria.list();
	}

	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void save(List<T> entitys) {
		for (T t : entitys) {
			save(t);
		}
	}

	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void update(List<T> entitys) {
		for (T t : entitys) {
			update(t);
		}
	}

	@Override
	public void delete(Object object) {
		this.getSession().delete(object);
	}

}
