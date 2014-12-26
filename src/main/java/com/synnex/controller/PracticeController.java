package com.synnex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Course;
import com.synnex.utils.jsonUtil.JsonBean;

/**
 * 作业相关操作
 * 
 * @author hiramh
 *
 */
@Controller
public class PracticeController extends GenericController {

	/**
	 * trainer跳转到添加练习页面
	 * 
	 * 需要课程id和trainer的id
	 */
	@RequestMapping("/trainer/practice/input")
	public String trainerInput(@RequestParam("id") String id, Model model) {
		Course course = courseServiceImpl.getCourse(Integer.valueOf(id));
		model.addAttribute("course", course);
		return "/trainer/practice/show";
	}

	/**
	 * trainer保存练习页面
	 * 
	 * 需要trainer的id和课程的id
	 */
	@RequestMapping(value = { "/trainer/practice/save" })
	@ResponseBody
	public JsonBean trainSave(@RequestBody Course course) {
		Course courseDate = courseServiceImpl.getCourse(course.getId());
		if (courseDate.getPractiseStatus() == null || courseDate.getPractiseStatus() == 0) {
			practiceServiceImpl.addPractices(course.getId());
			courseDate.setPractiseStatus(1);
		}
		courseDate.setPractise(course.getPractise());
		courseServiceImpl.updateCourse(courseDate);
		JsonBean jsonBean = new JsonBean(true, "上传成功", null);
		return jsonBean;
	}
}