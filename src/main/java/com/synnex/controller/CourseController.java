package com.synnex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.dao.Order;
import com.synnex.model.Course;
import com.synnex.model.Term;
import com.synnex.utils.jsonUtil.JsonBean;

@Controller
@RequestMapping("/admin/term")
public class CourseController extends GenericController {
	@ResponseBody
	@RequestMapping(value = "/{termid}/courses/show")
	public JsonBean showCourseByTermjson(@PathVariable int termid) {
		Term term=new Term();
		term.setId(termid);
		Course course = new Course();
		course.setTerm(term);
		Order order1=Order.asc("id");
		List<Order> orders=new ArrayList<Order>();
		orders.add(order1);
		List<Course> courses = courseServiceImpl.getCoursesByCondition(course, orders, 0, 8);
		JsonBean jsonBean=null;
		if (null == course || courses.isEmpty()) {
			jsonBean = new JsonBean(false, "没有数据", null);
			return jsonBean;
		}
		jsonBean = new JsonBean(true, "获取数据成功！", jsonBean);
		return jsonBean;
	}

	@RequestMapping(value = "/{termid}/courses/showall")
	public String showCourseByTerm(@PathVariable int termid, Model model) {
		Term term = new Term();
		term.setId(termid);
		Course course = new Course();
		course.setTerm(term);
		Order order1 = Order.asc("id");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order1);
		List<Course> courses = courseServiceImpl.getCoursesByCondition(course, orders, 0, 8);
		model.addAttribute("terms", courses);
		return "/admin/courses/show";
	}
}
