package com.synnex.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		System.out.println("hhaaaaaaaaaaa");
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

	/**
	 * @author jennifert
	 * 从前台传入后台的时间处理
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	
	@ResponseBody
	@RequestMapping(value = "/{termid}/courses/add", method = { RequestMethod.POST })
	public JsonBean addCourse(Course course, BindingResult brt, @PathVariable("termid") int termid, HttpServletRequest request) {
		JsonBean jsonBean = null;
		String name = request.getParameter("name");
		String time = request.getParameter("starttime");
		System.out.println("---course:" + course);
		if (brt.hasErrors()) {
			List<FieldError> errors = brt.getFieldErrors();
			Map<String, String> mapErrors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : errors) {
				mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			jsonBean = new JsonBean(false, "添加失败！", mapErrors);
			return jsonBean;
		}
		courseServiceImpl.addCourse(course, termid);
		// 添加与获取分离 不再写在一起
		jsonBean = new JsonBean(true, "添加成功！", null);
		return jsonBean;
	}
}
