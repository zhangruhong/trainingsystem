package com.synnex.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.synnex.model.Term;
import com.synnex.service.TermService;

@Controller
@RequestMapping(value={"/term"})
public class TermController {
	@Resource
	private TermService termServiceImpl;
	
	@RequestMapping(value={"/addterm"},method={RequestMethod.POST})
	public String addTerm(Term term){
		termServiceImpl.addTerm(term);
		return "redirct:/term/showall";
	}
	
	@RequestMapping(value={"/show"},method={RequestMethod.POST})
	public String showTerm(int id,Model model){
		Term term= termServiceImpl.getTerm(id);
		model.addAttribute("term",term);
		return "/term/show";
	}
	
	@RequestMapping(value={"/showall"})
	public String showAllTerm(Model model){
		List<Term> terms=termServiceImpl.getAllTerms();
		model.addAttribute("terms", terms);
		return "/term/showall";
	}

}
