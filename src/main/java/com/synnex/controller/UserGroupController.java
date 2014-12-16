package com.synnex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.dao.Order;
import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;
import com.synnex.utils.jsonUtil.JsonBean;

@Controller
@RequestMapping(value = { "/admin/term" })
public class UserGroupController {
	@Resource
	private UserGroupService userGroupServiceImpl;

	// @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addUsergrouppage() {
		return "/admin/usergroup/addpage";
	}

	@RequestMapping(value = { "/{termid}/usergroup/add" }, method = { RequestMethod.POST })
	@ResponseBody
	public JsonBean addUserGroup(@RequestBody Usergroup usergroup, @PathVariable(value = "termid") int termid) {
		userGroupServiceImpl.addGroup(usergroup, termid);
		Order order = Order.asc("id");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		List<Usergroup> terms = userGroupServiceImpl.getAllGroups(termid, orders, 0, 8);
		JsonBean jsonBean = new JsonBean(true, "", terms);
		return jsonBean;
	}

	@RequestMapping(value = { "/{termid}/usergroup/{groupid}" }, method = { RequestMethod.GET })
	public String showUsergroup(@PathVariable("groupid") String usergroupid, Model model) {
		Usergroup usergroup = userGroupServiceImpl.getGroup(Integer.valueOf(usergroupid));
		model.addAttribute("usergroup", usergroup);
		return "/admin/usergroup/show";
	}

	@RequestMapping(value = { "/{termid}/usergroup/show" }, method = { RequestMethod.GET })
	public String showAllUsergroup(@RequestParam(value = "page", required = false) Integer page, @PathVariable String termid, Model model) {
		int size = 10;
		if (null == page || page < 1) {
			page = 1;
		}
		int count = userGroupServiceImpl.getCount();
		List<Usergroup> usergroups = userGroupServiceImpl.getAllGroups(Integer.valueOf(termid), null, size * (page - 1), size);
		model.addAttribute("usergroups", usergroups);
		model.addAttribute("totolpages", count / 10 + 1);
		return "/admin/usergroup/show";
	}

	@RequestMapping(value = { "/{termid}/usergroup/delete/{groupid}" }, method = { RequestMethod.GET })
	public String deleteUsergroup(@PathVariable("groupid") String usergroupid) {
		userGroupServiceImpl.deleteGroup(Integer.valueOf(usergroupid));
		return "rediret:/admin/showall";
	}

}
