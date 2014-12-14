package com.synnex.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Term;
import com.synnex.service.TermService;

@Controller
@RequestMapping(value = { "/admin/term" })
public class TermController {
	@Resource
	private TermService termServiceImpl;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	// TODO 暂时用模态框处理 还不需要单独的一个页面来处理
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String addTermpage() {
		return "/admin/term/add";
	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map addTerm(@RequestBody Term term) {
		termServiceImpl.addTerm(term);
		logger.info("term:" + term.toString());
		List<Term> terms = termServiceImpl.getAllTerms();
		Map termmap = new TreeMap();
		termmap.put("success", true);
		termmap.put("terms", terms);
		return termmap;
	}

	public Map addTerm1(@RequestBody Term term) {
		termServiceImpl.addTerm(term);
		logger.info("term:" + term.toString());
		List<Term> terms = termServiceImpl.getAllTerms();
		Map termmap = new TreeMap();
		termmap.put("data", terms);
		termmap.put("success ", "true");
		return null;
	}

	// TODO showall的时候将termid写到URL上去
	@RequestMapping(value = { "/show/{termid}" }, method = { RequestMethod.POST })
	public String showTerm(@PathVariable("termid") int id, Model model) {
		Term term = termServiceImpl.getTerm(id);
		model.addAttribute("term", term);
		return "/admin/term/show";
	}

	@RequestMapping(value = { "/showall" }, method = { RequestMethod.GET })
	public String showAllTerm(Model model) {
		List<Term> terms = termServiceImpl.getAllTerms();
		model.addAttribute("terms", terms);
		return "/admin/term/showall";
	}

}
