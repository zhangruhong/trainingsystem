package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.UserDao;
import com.synnex.exception.LogicException;
import com.synnex.model.User;
import com.synnex.service.UserService;
import com.synnex.utils.md5Util.Md5Encode;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDaoImpl")
	private UserDao userDaoImpl;

	@Override
	public void addUser(User user) {
		userDaoImpl.save(user);
	}

	@Override
	public void addUsers(List<User> users) {
		userDaoImpl.save(users);
	}

	@Override
	public void deleteUser(User user) {
		userDaoImpl.delete(user);
	}

	@Override
	public void deleteUsers(List<User> users) {
		// TODO 有待优化
		for (User user : users) {
			this.deleteUser(user);
		}
	}

	@Override
	public void updateUser(User user) {
		userDaoImpl.update(user);

	}

	@Override
	public void updateUsers(List<User> users) {
		userDaoImpl.update(users);
	}

	@Override
	public User getUser(Integer id) {
		return userDaoImpl.get(id);
	}

	@Override
	public User checkLogin(String username, String password) {
		// 验证用户名
		password = Md5Encode.getStringMD5(password);
		String hql = "FROM User o WHERE o.loginname=? and o.password=?";
		List<User> users = userDaoImpl.findByHql(hql, username, password);
		if (users == null || users.size() != 1) {
			throw new LogicException("用户名或密码错误！！", -101);
		}
		User user = users.get(0);
		return user;
	}

	/**
	 * 已在底层添加前后%实现了 向前、向后都匹配搜索
	 */
	@Override
	public List<User> listByNameSimilar(User condition, List<Order> orders, int startpage, int pagesize) {
		return userDaoImpl.listByNameSimilar(condition, orders, startpage, pagesize);
	}

	@Override
	public User findTranerbyName(String loginname) {
		return userDaoImpl.findUserbyName(loginname, 1);
	}

	@Override
	public List<User> getAllUsers() {
		return userDaoImpl.findAllUsers();
	}

	@Override
	public User gettraineeByName(String loginname) {
		return userDaoImpl.findUserbyName(loginname, 2);
	}
}
