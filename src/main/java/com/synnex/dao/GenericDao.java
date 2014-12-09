package com.synnex.dao;

import java.util.List;

import org.hibernate.Session;

/**
 * @author Jeniss Dec 3, 2014 9:25:39 PM
 * @tags generic dao
 */
public interface GenericDao<T, PK> {
	Session getSession();

	/**
	 * @tags Get by id.
	 * @param id
	 *            The id
	 * @return Return T
	 */
	T get(PK id);

	/**
	 * @tags List the limit object by condition, orders, begin, size.
	 * @param condition
	 *            The condition
	 * @param orders
	 *            The order by
	 * @param begin
	 *            The begin
	 * @param size
	 *            The max query size
	 * @return Return List
	 */
	public List<T> list(Object condition, List<Order> orders, int begin, int size);

	/**
	 * @tags Save entity to DB.
	 */
	void save(T entity);

	/**
	 * @tags Batch save entities to DB.
	 */
	void save(List<T> entitys);

	/**
	 * @tags Update entity to DB.
	 */
	void update(T entity);

	/**
	 * @tags Batch update entities to DB.
	 */
	void update(List<T> entitys);

	/**
	 * @tags Delete by id.
	 * @param object
	 *            The delete object
	 */
	void delete(Object object);

	/**
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> findByHql(String hql, Object... objects);

}
