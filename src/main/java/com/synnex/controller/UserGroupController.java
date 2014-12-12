package com.synnex.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;

@Controller
@RequestMapping(value = { "/admin/usergroup" })
public class UserGroupController {
	@Resource
	private UserGroupService userGroupServiceImpl;

	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addUsergrouppage() {
		return "/admin/usergroup/addpage";
	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public String addUserGroup(Usergroup usergroup) {
		userGroupServiceImpl.addGroup(usergroup, usergroup.getTerm().getId());
		return "redirect:/admin/showall";
	}

	@RequestMapping(value = { "/show/{groupid}" }, method = { RequestMethod.GET })
	public String showUsergroup(@PathVariable("groupid") String usergroupid, Model model) {
		Usergroup usergroup = userGroupServiceImpl.getGroup(Integer.valueOf(usergroupid));
		model.addAttribute("usergroup", usergroup);
		return "/admin/usergroup/show";
	}

	@RequestMapping(value = { "/showall" }, method = { RequestMethod.GET })
	public String showAllUsergroup(String termid, Model model) {
		List<Usergroup> usergroups = userGroupServiceImpl.getAllGroups(Integer.valueOf(termid));
		model.addAttribute("usergroups", usergroups);
		return "/admin/usergroup/showall";
	}

	@RequestMapping(value = { "/delete/{groupid}" }, method = { RequestMethod.GET })
	public String deleteUsergroup(@PathVariable("groupid") String usergroupid) {
		userGroupServiceImpl.deleteGroup(Integer.valueOf(usergroupid));
		return "rediret:/admin/showall";
	}

}
