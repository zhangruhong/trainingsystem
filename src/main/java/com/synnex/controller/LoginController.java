package com.synnex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synnex.exception.LogicException;
import com.synnex.model.User;

/**
 * @author Hiram
 * @create time 2014-12-8 时间9:53:06
 * 
 */
@Controller
public class LoginController extends GenericController {
	/**
	 * 
	 * @param modelMap
	 * @return String url route
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public String checkLogin(String username, String password, Map<String, Object> map, HttpSession session) throws Exception {
		User user = null;
		try {
			user = userServiceImpl.checkLogin(username, password);
			// 把用户放入session
			session.setAttribute("USER_IN_SESSION", user);
			// 成功提示
			map.put("success", true);
			map.put("msg", "操作成功！！");
		} catch (LogicException e) {
			// 错误提示
			map.put("success", false);
			map.put("msg", e.getMessage());
			map.put("errorCode", e.getErrorCode());
			return "redirect:/login.html";
		}
		int role = user.getRole();
		switch (role) {
			case 0:
				return null;
			case 1:
				return null;
			default:
				return "/common/homepage";
		}
	}

	/**
	 * 
	 * @param session
	 * @return 返回到登录页面
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("USER_IN_SESSION");
		return "redirect:/login.html";
	}
}
