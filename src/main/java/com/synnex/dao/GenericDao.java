package com.synnex.dao;

import java.util.List;

import org.hibernate.Session;

import com.synnex.model.PageResult;

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
	 * @param entity
	 *            The delete entity
	 */
	void delete(T entity);

	/**
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> findByHql(String hql, Object... objects);


	/**
	 * 分页列表
	 * 
	 * @param baseQuery
	 * @return
	 */
	public PageResult<T> listPageResult(int begin, int size, String hql, Object... objects);
}
