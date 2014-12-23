package com.synnex.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.synnex.model.Usergroup;
import com.synnex.service.UserGroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class TestUsergroupDao {
	@Resource
	private UserGroupDao userGroupDaoImpl;
	@Resource
	private UserGroupService userGroupService;

	@Test
	@Transactional
	public void TestDelete() {
		// Usergroup ug = userGroupDaoImpl.get(new Integer(5));
		// ug.setDescription("---+++a");
		// Usergroup ug_new = new Usergroup();
		// userGroupDaoImpl.update(ug);
		// userGroupDaoImpl.delete(ug);
		Usergroup ug = userGroupService.getGroup(new Integer(1));
		System.out.println("desc:" + ug.getDescription());
		ug.setDescription("---+++a");
		System.out.println("desc:" + ug.getDescription());
		userGroupService.updateGroup(ug);

		Usergroup ug_update = userGroupService.getGroup(new Integer(2));
		// userGroupDaoImpl.delete(ug);
	}

}
