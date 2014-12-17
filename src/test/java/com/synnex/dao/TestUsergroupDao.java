package com.synnex.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.synnex.model.Usergroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class TestUsergroupDao {
	@Resource
	private UserGroupDao userGroupDaoImpl;

	@Test
	@Transactional
	public void TestDelete() {
		Usergroup ug = userGroupDaoImpl.get(new Integer(5));
		ug.setDescription("---+++a");
		userGroupDaoImpl.delete(ug);
	}

}
