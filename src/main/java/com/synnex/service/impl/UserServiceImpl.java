package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.synnex.dao.Order;
import com.synnex.dao.UserDao;
import com.synnex.model.User;
import com.synnex.service.UserService;

@Repository
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
	public User getUser(int id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getUsersByCondition(User condition, List<Order> orders, int begin, int size) {
		return userDao.list(condition, orders, begin, size);
	}

}
