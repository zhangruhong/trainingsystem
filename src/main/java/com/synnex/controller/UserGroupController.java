package com.synnex.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.dao.Order;
import com.synnex.exception.UserException;
import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.model.Usergroup;
import com.synnex.utils.jsonUtil.JsonBean;
import com.synnex.utils.variable.SystemVariable;

@Controller
@RequestMapping(value = { "/admin/term" })
public class UserGroupController extends GenericController {

	/**
	 * 接收json数据添加分组
	 * 
	 * @param usergroup
	 * @param brt
	 * @param termid
	 * @return
	 */
	@RequestMapping(value = { "/{termid}/usergroup/add" }, method = { RequestMethod.POST })
	@ResponseBody
	public JsonBean addUserGroup(@RequestBody @Valid Usergroup usergroup, BindingResult brt, @PathVariable(value = "termid") int termid) {
		JsonBean jsonBean = null;
		if (brt.hasErrors()) {
			List<FieldError> errors = brt.getFieldErrors();
			Map<String, String> mapErrors = new LinkedHashMap<String, String>();
			for (FieldError err : errors) {
				mapErrors.put(err.getField(), err.getDefaultMessage());
			}
			jsonBean = new JsonBean(false, "Add failed！", mapErrors);
			logger.info("mapErrors:" + mapErrors.toString());
			return jsonBean;
		}
		userGroupServiceImpl.addGroup(usergroup, termid);
		Order order = Order.asc("id");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		List<Usergroup> usergroups = userGroupServiceImpl.listUserGroupPage(0, 8, termid).getRows();
		jsonBean = new JsonBean(true, "", usergroups);
		return jsonBean;
	}

	@RequestMapping(value = { "/{termid}/usergroup/{groupid}" }, method = { RequestMethod.GET })
	public String showUsergroup(@PathVariable("groupid") String usergroupid, Model model) {
		Usergroup usergroup = userGroupServiceImpl.getGroup(Integer.valueOf(usergroupid));
		model.addAttribute("usergroup", usergroup);
		return "/admin/usergroup/show";
	}

	// TODO 这里的关联关系没有建立起 不同学期分组无效
	@RequestMapping(value = { "/{termid}/usergroup/show" }, method = { RequestMethod.GET })
	public String showAllUsergroup(@RequestParam(value = "page", required = false) Integer page, @PathVariable int termid, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		String termname=termServiceImpl.getTerm(termid).getName();
		PageResult<Usergroup> pageResult = userGroupServiceImpl.listUserGroupPage(page, SystemVariable.PageSize,termid);
		model.addAttribute("pageResult", pageResult);
//		model.addAttribute("termid", termid);
		model.addAttribute("termname", termname);
		return "/admin/usergroup/show";
	}

	// TODO 删除、更新无效 后面排查
	@RequestMapping(value = { "/{termid}/usergroup/{groupid}/delete" }, method = { RequestMethod.GET })
	@ResponseBody
	public JsonBean deleteUsergroup(@PathVariable(value = "termid") int termid, @PathVariable("groupid") int usergroupid) {
		userGroupServiceImpl.deleteGroup(usergroupid);
		Order order = Order.asc("id");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		List<Usergroup> terms = userGroupServiceImpl.listUserGroupPage(0, 8, termid).getRows();
		JsonBean jsonBean = new JsonBean(true, "data delete success!", terms);
		return jsonBean;
	}

	@RequestMapping(value = { "/{termid}/usergroup/{groupid}/add/{loginname}" }, method = { RequestMethod.GET })
	@ResponseBody
	public JsonBean addUserToGroup(@PathVariable("loginname") String loginname, @PathVariable(value = "termid") int termid,
			@PathVariable("groupid") int usergroupid) {
		JsonBean jsonBean = null;
		logger.info("--loginname----:" + loginname);
		// 双向多对多
		try {
			userGroupServiceImpl.addUserToGroup(loginname, usergroupid);
		} catch (UserException e) {
			logger.error(e.getMessage());
			jsonBean = new JsonBean(false, "add failed！name does not exist", null);
			return jsonBean;
		}
		// 不返回数据 由前端发起ajax请求获取数据
		jsonBean = new JsonBean(true, "add Success", null);
		return jsonBean;
	}

	@RequestMapping(value = { "/{termid}/usergroup/{groupid}/users" }, method = { RequestMethod.GET })
	@ResponseBody
	public JsonBean showUserOfGroup(@PathVariable(value = "termid") int termid, @PathVariable("groupid") int groupid) {
		Usergroup usergroup = userGroupServiceImpl.getGroup(groupid);
		Set<User> users = usergroup.getUsers();
		JsonBean jsonBean = null;
		if (null != users && users.size() > 0) {
			jsonBean = new JsonBean(true, "", users);
		} else {
			jsonBean = new JsonBean(false, "No user is added to this group", null);
		}
		return jsonBean;
	}

	@RequestMapping(value = { "/{termid}/usergroup/{groupid}/delete/{userid}" }, method = { RequestMethod.GET })
	@ResponseBody
	public JsonBean deleteUserFromGroup(@PathVariable("userid") int userid, @PathVariable(value = "termid") int termid,
			@PathVariable("groupid") int usergroupid) {
		JsonBean jsonBean = null;
		// 双向多对多
		try {
			logger.info(userid + "-_-" + usergroupid);
			userGroupServiceImpl.deleteUserFromGroup(userid, usergroupid);
		} catch (UserException e) {
			logger.error(e.getMessage());
			jsonBean = new JsonBean(false, "Remove failed!" + e.getMessage(), null);
			return jsonBean;
		}
		// 不返回数据 由前端发起ajax请求获取数据
		jsonBean = new JsonBean(true, "Remove Succuss", null);
		return jsonBean;
	}

}
