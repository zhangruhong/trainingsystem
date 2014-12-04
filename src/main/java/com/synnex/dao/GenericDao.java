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
	public List<T> list(Object condition, List<Order> orders, int begin,
			int size);
}
