package com.synnex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synnex.model.User;

@Controller("user")
public class UserController extends GenericController {

	/**
	 * 由SpringMVC参数自动绑定到User 这里需要传递loginname、email、phoneno、role 不是很符合rest风格
	 * 
	 * @param user
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String createUser(User user) {
		userService.addUser(user);
		return "redirect:showusers";
	}

	@RequestMapping(value = "showusers", method = RequestMethod.GET)
	public String showUsers(Model model) {
		List<User> users = userService.getUsersByCondition(null, null, -1, 0);
		model.addAttribute("users", users);
		return "showuser";
	}

	@RequestMapping(value = "deleteuser")
	public String deleteUser(User user) {
		userService.deleteUser(user);
		return "redirect:showusers";
	}

	/**
	 * 不知道页面传过来的对象用来保存会不会出问题？
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="updateuser")
	public String updateUser(User user) {
		User databaseuser = userService.getUser(user.getId());
		user.setPassword(databaseuser.getPassword());
		userService.updateUser(user);
		return "redirect:showusers";

	}
}
