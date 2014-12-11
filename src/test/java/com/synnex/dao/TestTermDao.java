package com.synnex.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synnex.model.Term;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:/src/main/webapp/WEB-INF/applicationContext.xml"})
public class TestTermDao {

	@Test
	public void test() {
		Term term =new Term();
		term.setName("奋斗组");
		term.setDescription("奋斗组很叼id额");
		
	}

}
