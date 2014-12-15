package com.synnex.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;

@Controller
@RequestMapping(value = { "/admin/usergroup" })
public class UserGroupController {
	@Resource
	private UserGroupService userGroupServiceImpl;

	// @RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addUsergrouppage() {
		return "/admin/usergroup/addpage";
	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public String addUserGroup(Usergroup usergroup) {
		userGroupServiceImpl.addGroup(usergroup, usergroup.getTerm().getId());
		return "redirect:/admin/showall";
	}

	@RequestMapping(value = { "/show/{groupid}" }, method = { RequestMethod.GET })
	public String showUsergroup(@PathVariable("groupid") String usergroupid,
			Model model) {
		Usergroup usergroup = userGroupServiceImpl.getGroup(Integer
				.valueOf(usergroupid));
		model.addAttribute("usergroup", usergroup);
		return "/admin/usergroup/show";
	}

	@RequestMapping(value = { "/show" }, method = { RequestMethod.GET })
	public String showAllUsergroup(
			@RequestParam(value = "page", required = false) Integer page,
			String termid, Model model) {
		int size = 10;
		if (null == page || page < 1) {
			page = 1;
		}
		int count = userGroupServiceImpl.getCount();
		List<Usergroup> usergroups = userGroupServiceImpl.getAllGroups(Integer
				.valueOf(termid),null,size*(page-1),size);
		model.addAttribute("usergroups", usergroups);
		model.addAttribute("totolpages", count / 10 + 1);
		return "/admin/usergroup/show";
	}

	@RequestMapping(value = { "/delete/{groupid}" }, method = { RequestMethod.GET })
	public String deleteUsergroup(@PathVariable("groupid") String usergroupid) {
		userGroupServiceImpl.deleteGroup(Integer.valueOf(usergroupid));
		return "rediret:/admin/showall";
	}

}
