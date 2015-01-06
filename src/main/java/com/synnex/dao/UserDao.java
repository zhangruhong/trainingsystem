package com.synnex.dao;

import java.util.List;

import com.synnex.model.PageResult;
import com.synnex.model.User;

public interface UserDao extends GenericDao<User, Integer> {
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

	/**
	 * 通过courseId查询所有的user
	 * 
	 * @author hiram
	 * @param courseId
	 * @return
	 */
	public List<User> queryUserByCourse(Integer courseId);

	/**
	 * 根据名字找全匹配的User
	 * 
	 * @param loginname
	 * @return
	 */
	public User findUserbyName(String loginname, int role);

	/**
	 * 查询所有非培训管理者的用户
	 * 
	 * @return
	 */
	public List<User> findAllUsers();

	/**
	 * 查询user带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public PageResult<User> listUserPage(Integer page, int pagesize);

}
