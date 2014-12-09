package com.synnex.service;

import java.util.List;

import com.synnex.dao.Order;
import com.synnex.model.User;

public interface UserService {
	/**
	 * ----是否应该添加个类或者修改大分类类来对学员进行分组？ 包括用户的（单个、批量）增删改查-管理员专属 包括用户自己修改密码
	 * 
	 */
	/**
	 * 新建一个User对象
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 批量新建User对象（应用场景-讲师输入学员名字批量建用户并发邮件通知）
	 * 
	 * @param users
	 */
	public void addUsers(List<User> users);

	/**
	 * 删除/冻结一个用户（应用场景-培训学员离职，或者不再参加培训等）
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * 批量删除/冻结/转存一批用户（应用场景-一批学员毕业）
	 * 
	 * @param users
	 */
	public void deleteUsers(List<User> users);

	/**
	 * 更新一个学员的信息(讲师维护)
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 批量更新学员信息（应用场景-新建学员时勾选多个然后一起分配到某一部门）
	 * 
	 * @param users
	 */
	public void updateUsers(List<User> users);

	/**
	 * 根据编号查学员
	 * 
	 * @param id
	 *            （此编号为系统内部编号，对所有人透明）
	 * @return
	 */
	public User getUser(Integer id);

	/**
	 * 
	 * @param condition
	 *            查询条件 被赋值的当前类
	 * @param orders
	 *            排列方式
	 * @param begin
	 *            开始页面
	 * @param size
	 *            步长
	 * @return
	 */
	public List<User> getUsersByCondition(User condition, List<Order> orders, int begin, int size);
	
	/**
	 * 
	 * @param username
	 * 			  用户名
	 * @param password
	 * 			 密码
	 * @return
	 * 		  	user对象
	 */
	public User checkLogin(String username, String password);
}
