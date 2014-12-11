package com.synnex.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.synnex.model.Term;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class TestTermService {
	@Resource(name = "termServiceImpl")
	private TermService tsi;

	@Test
	public void addtermtest() {
		Term term = new Term();
		term.setName("奋斗组1");
		term.setDescription("奋斗组2很叼id额2");
		tsi.addTerm(term);
	}

	@Test
	public void gettermbyid() {
		Term t = tsi.getTerm(1);
		System.out.println(t);
	}
	
	@Test
	public void getallterms(){
		System.out.println(tsi.getAllTerms());
	}

}
