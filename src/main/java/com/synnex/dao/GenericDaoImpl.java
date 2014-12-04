package com.synnex.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDaoImpl<T, PK> implements GenericDao<T, PK> {
	@Autowired
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
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
}
