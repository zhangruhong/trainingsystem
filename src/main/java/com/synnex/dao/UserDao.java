package com.synnex.dao;

import java.util.List;

import com.synnex.model.User;

public interface UserDao  extends GenericDao<User, Integer> {
	/**
	 * 相似搜索所有用户
	 * 
	 * @param condition
	 * @param orders
	 * @param begin
	 * @param size
	 * @return
	 */
	public List<User> listByNameSimilar(User condition, List<Order> orders, int begin, int size);
}
