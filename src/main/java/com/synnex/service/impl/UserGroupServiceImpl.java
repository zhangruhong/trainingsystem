package com.synnex.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.TermDao;
import com.synnex.dao.UserGroupDao;
import com.synnex.model.Term;
import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Resource(name = "userGroupDaoImpl")
	private UserGroupDao userGroupDaoImpl;
	@Resource(name = "termDaoImpl")
	private TermDao termDaoImpl;

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
		List<Usergroup> usergroups = userGroupDaoImpl.list(usergroup,orders, begin,size);
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

}
