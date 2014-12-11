package com.synnex.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synnex.dao.Order;
import com.synnex.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class TestUserService {

	// @Autowired
		@Resource(name = "userServiceImpl")
		private UserService userService;

		@Test
		public void addUserTest() {
			User user = new User();
			user.setLoginname("123");
			user.setPassword("123");
			user.setRole(1);
			user.setPhoneno("123456789");
			user.setEmail("123@123.cn");
			userService.addUser(user);
		}

		@Test
		public void addUsersTest() {
			List<User> users = new ArrayList<User>();
			for (int i = 0; i < 100; i++) {
				User user = new User();
				user.setEmail("bitch" + i + "@123.cn");
				user.setLoginname("bitch" + i + "loginname");
				user.setPassword("bitch" + i + "password");
				user.setPhoneno("bitch" + i + "phoneno");
				user.setRole(i % 3);
				users.add(user);
			}
			userService.addUsers(users);
		}

		@Test
		public void getUser() {
			User user = userService.getUser(new Integer(7));
			System.out.println("user:" + user.getEmail());
		}

		@Test
		public void getUsers() {
			List<User> users = new ArrayList<User>();
			List<Order> orderss=new ArrayList<Order>();
			User condition=new User();
			condition.setRole(0);
			Order orders = Order.asc("role");
			Order orders1 = Order.asc("phoneno");
			orderss.add(orders);
			orderss.add(orders1);
			
			users = userService.getUsersByCondition(condition, orderss, 0, 50);
			for (User user : users) {
				System.out.println("user:" + user);
			}
		}

		@Test
		public void modifyUser() {
			List<User> users = new ArrayList<User>();
			List<Order> orderss = new ArrayList<Order>();
			User condition = new User();
			condition.setRole(5);
			Order orders = Order.asc("role");
			Order orders1 = Order.asc("phoneno");
			orderss.add(orders);
			orderss.add(orders1);

			users = userService.getUsersByCondition(condition, orderss, 0, 50);
			for (User user : users) {
				System.out.println(user);
				user.setRole(7);
			}
			System.out.println("-------------------");
			for (User user : users) {
				System.out.println(user);
			}
			userService.updateUsers(users);

		}

}
