package com.synnex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.dao.Order;
import com.synnex.model.PageResult;
import com.synnex.model.Term;
import com.synnex.model.User;
import com.synnex.utils.jsonUtil.JsonBean;
import com.synnex.utils.variable.SystemVariable;

@Controller
public class TermController extends GenericController {

	// TODO 暂时用模态框处理 还不需要单独的一个页面来处理
	@RequestMapping(value = { "/admin/term/add" }, method = { RequestMethod.GET })
	public String addTermpage() {
		return "/admin/term/add";
	}

	@RequestMapping(value = { "/admin/term/add" }, method = { RequestMethod.POST })
	@ResponseBody
	public JsonBean addTerm(@RequestBody Term term) {
		termServiceImpl.addTerm(term);
		logger.info("term:" + term.toString());
		Order order = Order.asc("id");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		List<Term> terms = termServiceImpl.getAllTerms(null, orders, 0, 8);
		// TODO 加入@valid验证 将错误放入json对象返回 前端js显示出来
		JsonBean jsonBean = new JsonBean(true, "", terms);
		return jsonBean;

	}

	// TODO showall的时候将termid写到URL上去
	@RequestMapping(value = { "/admin/term/show/{termid}" }, method = { RequestMethod.POST })
	public String showTerm(@PathVariable("termid") int id, Model model) {
		Term term = termServiceImpl.getTerm(id);
		model.addAttribute("term", term);
		return "/admin/term/show";
	}

	@RequestMapping(value = { "/admin/term/showall" }, method = { RequestMethod.GET })
	public String showPagerTerm(@RequestParam(value = "page", required = false) Integer page, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Term> pageResult = termServiceImpl.listPageResult(page, SystemVariable.PageSize);
		model.addAttribute("pageResult", pageResult);
		return "/admin/term/showall";
	}

	/**
	 * trainee跳转到学期查看页面
	 * 
	 * 需要trainee的id
	 */
	@RequestMapping("/trainee/term/view")
	public String traineeViewTerm(HttpSession session, Model model) {
		User user = (User) session.getAttribute("USER_IN_SESSION");
		int userId = user.getId();
		List<Term> terms = termServiceImpl.listTermByTrainee(userId);
		model.addAttribute("terms", terms);
		return "/trainee/term/view";
	}
}
