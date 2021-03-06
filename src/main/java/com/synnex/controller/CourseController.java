package com.synnex.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Course;
import com.synnex.model.Dictionary;
import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.utils.jsonUtil.JsonBean;
import com.synnex.utils.variable.SystemVariable;

@Controller
public class CourseController extends GenericController {
	@ResponseBody
	@RequestMapping(value = "/admin/term/{termid}/courses/show")
	public JsonBean showCourseByTermjson(@PathVariable int termid) {
		List<Course> courses = courseServiceImpl.listCoursePageByTerm(0, 8, termid, null).getRows();
		JsonBean jsonBean = null;
		if (null == courses || courses.isEmpty()) {
			jsonBean = new JsonBean(false, "没有数据", null);
			return jsonBean;
		}
		jsonBean = new JsonBean(true, "获取数据成功！", courses);
		return jsonBean;
	}

	@RequestMapping(value = "/admin/term/{termid}/courses/showall")
	public String showCourseByTerm(@PathVariable int termid, @RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "page", required = false) Integer page, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Course> pageResult = courseServiceImpl.listCoursePageByTerm(page, SystemVariable.PageSize, termid, searchKey);
		List<Dictionary> dictionaries = dictionaryServiceImpl.listAll();
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("dictionaries", dictionaries);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("termid", termid);
		return "/admin/courses/show";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/term/{termid}/courses/add", method = { RequestMethod.POST })
	public JsonBean addCourse(@RequestBody @Valid Course course, BindingResult brt, @PathVariable("termid") int termid) {
		JsonBean jsonBean = null;
		String tranername = course.getTrainer().getLoginname();
		User u = userServiceImpl.findTranerbyName(tranername);
		int dictionaryid = course.getDictionary().getId();
		Dictionary dictionary = dictionaryServiceImpl.get(dictionaryid);
		if (null == dictionary) {
			brt.rejectValue("dictionary", "", "分类不存在诶");
		}
		if (null == u) {
			brt.rejectValue("trainer", "", "讲师不存在诶！");
		}
		if (course.getStarttime().after(course.getEndtime())) {
			brt.rejectValue("endtime", "", "开始时间晚于结束时间！");
		}
		if (brt.hasErrors()) {
			List<FieldError> errors = brt.getFieldErrors();
			Map<String, String> mapErrors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : errors) {
				mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			jsonBean = new JsonBean(false, "添加失败！", mapErrors);
			return jsonBean;
		}
		course.setDictionary(dictionary);
		course.setTrainer(u);
		courseServiceImpl.addCourse(course, termid);

		jsonBean = new JsonBean(true, "添加成功！", null);
		return jsonBean;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/term/{termid}/courses/{courseid}/update", method = { RequestMethod.POST })
	public JsonBean updateCourse(@RequestBody @Valid Course course, BindingResult brt, @PathVariable("termid") int termid) {
		JsonBean jsonBean = null;
		// 获取修改后的course的trainer
		String tranername = course.getTrainer().getLoginname();
		User u = userServiceImpl.findTranerbyName(tranername);

		// 获取修改后的course的所属分类
		int dictionaryid = course.getDictionary().getId();
		Dictionary dictionary = dictionaryServiceImpl.get(dictionaryid);
		if (null == dictionary) {
			brt.rejectValue("dictionary", "", "分类不存在诶");
		}
		if (null == u) {
			brt.rejectValue("trainer", "", "讲师不存在诶！");
		}
		if (course.getStarttime().after(course.getEndtime())) {
			brt.rejectValue("endtime", "", "开始时间晚于结束时间！");
		}
		if (brt.hasErrors()) {
			List<FieldError> errors = brt.getFieldErrors();
			Map<String, String> mapErrors = new LinkedHashMap<String, String>();
			for (FieldError fieldError : errors) {
				mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			jsonBean = new JsonBean(false, "修改失败！", mapErrors);
			return jsonBean;
		}
		Course course2 = courseServiceImpl.getCourse(course.getId());
		// 将修改后的course属性值全部复制到查出来的上面
		BeanUtils.copyProperties(course, course2, "id", "term", "practiseStatus", "practise");
		course2.setDictionary(dictionary);
		course2.setTrainer(u);
		courseServiceImpl.updateCourse(course2);
		// 添加与获取分离 不再写在一起
		jsonBean = new JsonBean(true, "修改成功！", null);
		return jsonBean;
	}

	@ResponseBody
	@RequestMapping(value = "/admin/term/{termid}/courses/get", method = { RequestMethod.GET })
	public JsonBean getCourse(@RequestParam(value = "id", required = true) int courseid) {
		JsonBean jsonBean = null;
		Course course = courseServiceImpl.getCourse(courseid);
		if (null == course) {
			jsonBean = new JsonBean(false, "获取该Course失败！", null);
			logger.debug("courseid=" + courseid + "的course不能获取到（通过该id获取到的course为空）。");
		}
		jsonBean = new JsonBean(true, "获取成功", course);
		return jsonBean;
	}

	/**
	 * trainee查看课程
	 * 
	 * @param termid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/trainee/{termid}/course/view")
	public String traineeViewCrouse(@RequestParam(value = "page", required = false) Integer page, @PathVariable int termid, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Course> pageResult = courseServiceImpl.listCoursePageByTerm(page, SystemVariable.PageSize, termid, null);
		model.addAttribute("pageResult", pageResult);
		return "/trainee/course/view";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/term/{termid}/courses/{courseid}/delete", method = { RequestMethod.GET })
	public JsonBean deleteCourse(@PathVariable("termid") int termid, @PathVariable("courseid") int courseid) {
		JsonBean jsonBean = null;
		try {
			Course course = courseServiceImpl.getCourse(courseid);
			courseServiceImpl.deleteCourse(course);
		} catch (Exception e) {
			jsonBean = new JsonBean(false, "删除失败！该课程已有作业，不能删除！", null);
			return jsonBean;
		}
		jsonBean = new JsonBean(true, "删除成功", null);
		return jsonBean;
	}
}
