package com.synnex.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.TermDao;
import com.synnex.dao.UserDao;
import com.synnex.dao.UserGroupDao;
import com.synnex.exception.UserException;
import com.synnex.model.Term;
import com.synnex.model.User;
import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;

@Service("userGroupServiceImpl")
public class UserGroupServiceImpl implements UserGroupService {

	@Resource(name = "userGroupDaoImpl")
	private UserGroupDao userGroupDaoImpl;
	@Resource(name = "termDaoImpl")
	private TermDao termDaoImpl;
	@Resource(name = "userDaoImpl")
	private UserDao userDaoImpl;

	@Override
	public void addGroup(Usergroup ug, int termid) {
		Term term = termDaoImpl.get(termid);
		ug.setTerm(term);
		Set<Usergroup> usergroups = term.getUsergroups();
		usergroups.add(ug);
		term.setUsergroups(usergroups);
		userGroupDaoImpl.save(ug);
	}

	@Override
	public void deleteGroup(int groupid) {
		userGroupDaoImpl.delete(userGroupDaoImpl.get(groupid));
	}

	@Override
	public Usergroup getGroup(int groupid) {
		return userGroupDaoImpl.get(groupid);
	}

	@Override
	public List<Usergroup> getAllGroups(int termid, List<Order> orders, int begin, int size) {
		Usergroup usergroup = new Usergroup();
		usergroup.setTerm(termDaoImpl.get(termid));
		List<Usergroup> usergroups = userGroupDaoImpl.list(usergroup, orders, begin, size);
		return usergroups;
	}

	@Override
	public void updateGroup(Usergroup ug) {
		userGroupDaoImpl.update(ug);
	}

	@Override
	public int getCount() {
		return userGroupDaoImpl.getTotolCount();
	}

	@Override
	public void addUserToGroup(String loginname, int usergroupid) throws UserException {
		User user = new User();
		user.setLoginname(loginname);
		user.setRole(1);
		List<User> users = userDaoImpl.list(user, null, 0, 1);
		if (null == users || users.isEmpty()) {
			throw new UserException("用户名不存在");
		}
		User u = users.get(0);
		Usergroup usergroup = getGroup(usergroupid);
		// 将需要添加的user添加到usergroup
		Set<User> groupusers = usergroup.getUsers();
		groupusers.add(u);
		usergroup.setUsers(groupusers);

		// 将usergroup也关联到user
		Set<Usergroup> usergroups = u.getUsergroups();
		usergroups.add(usergroup);
		u.setUsergroups(usergroups);

		userDaoImpl.update(u);
		this.updateGroup(usergroup);
	}

}
