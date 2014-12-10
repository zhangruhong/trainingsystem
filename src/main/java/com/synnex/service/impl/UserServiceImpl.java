package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.UserDao;
import com.synnex.model.User;
import com.synnex.service.UserService;
import com.synnex.utils.exception.LogicException;
import com.synnex.utils.md5Util.Md5Encode;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDaoImpl")
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public void addUsers(List<User> users) {
		userDao.save(users);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
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
		userDao.update(user);

	}

	@Override
	public void updateUsers(List<User> users) {
		userDao.update(users);
	}

	@Override
	public User getUser(Integer id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getUsersByCondition(User condition, List<Order> orders, int begin, int size) {
		return userDao.list(condition, orders, begin, size);
	}

	@Override
	public User checkLogin(String username, String password) {
		// 验证用户名
		password = Md5Encode.getStringMD5(password);
		String hql = "FROM User o WHERE o.loginname=? and o.password=?";
		List<User> users = userDao.findByHql(hql, username, password);
		if (users == null || users.size() != 1) {
			throw new LogicException("用户名或密码错误！！", -101);
		}
		User user = users.get(0);
		return user;
	}

}
