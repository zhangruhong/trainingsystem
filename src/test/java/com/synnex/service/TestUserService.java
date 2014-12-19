package com.synnex.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synnex.dao.Order;
import com.synnex.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class TestUserService {

	// @Autowired
	@Resource(name = "userServiceImpl")
	private UserService userServiceImpl;

	@Test
	public void addUserTest() {
		User user = new User();
		user.setLoginname("1123a");
		user.setPassword("1231234");
		user.setRole(1);
		user.setPhoneno("123456789");
		user.setEmail("123@123.cn");
		userServiceImpl.addUser(user);
	}

	@Test
	public void addUsersTest() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setEmail("bitch" + i + "@123.cn");
			user.setLoginname("bitch" + i + "loginname");
			user.setPassword(i + "password");
			user.setPhoneno("bitch" + i + "phoneno");
			user.setRole(i % 3);
			users.add(user);
		}
		userServiceImpl.addUsers(users);
	}

	@Test
	public void getUser() {
		User user = userServiceImpl.getUser(new Integer(7));
		System.out.println("user:" + user.getEmail());
	}

	@Test
	public void getUsers() {
		List<User> users = new ArrayList<User>();
		List<Order> orderss = new ArrayList<Order>();
		User condition = new User();
		condition.setRole(0);
		Order orders = Order.asc("role");
		Order orders1 = Order.asc("phoneno");
		orderss.add(orders);
		orderss.add(orders1);

		users = userServiceImpl.getUsersByCondition(condition, orderss, 0, 50);
		for (User user : users) {
			System.out.println("user:" + user);
		}
	}

	@Test
	public void getUsersByName() {
		System.out.println("_______________begin");
		List<User> users = new ArrayList<User>();
		// List<Order> orderss = new ArrayList<Order>();
		User condition = new User();
		condition.setLoginname("123");
		condition.setRole(1);
		// condition.setId(1);
		// Order orders = Order.asc("role");
		// Order orders1 = Order.asc("phoneno");
		// orderss.add(orders);
		// orderss.add(orders1);

		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("---begin--");
		users = userServiceImpl.getUsersByCondition(condition, null, -1, 50);
		logger.info("-----" + users.size());
		for (User user : users) {
			System.out.println("user:" + user);
		}
	}

	@Test
	public void modifyUser() {
		List<User> users = new ArrayList<User>();
		List<Order> orderss = new ArrayList<Order>();
		User condition = new User();
		condition.setRole(0);
		Order orders = Order.asc("role");
		Order orders1 = Order.asc("phoneno");
		orderss.add(orders);
		orderss.add(orders1);

		users = userServiceImpl.getUsersByCondition(condition, orderss, 0, 50);
		for (User user : users) {
			System.out.println(user);
			user.setRole(7);
		}
		userServiceImpl.updateUsers(users);
		System.out.println("-------------------");
		for (User user : users) {
			System.out.println(user);
		}

	}

}
