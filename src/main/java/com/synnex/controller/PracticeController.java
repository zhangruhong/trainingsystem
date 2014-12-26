package com.synnex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Course;
import com.synnex.model.Practice;
import com.synnex.model.User;
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
	public JsonBean trainerSave(@RequestBody Course course) {
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

	/**
	 * trainee跳转到提交练习页面
	 * 
	 * 需要课程id和trainee的id
	 */
	@RequestMapping("/trainee/practice/commit")
	public String traineeInput(@RequestParam("id") String id, HttpSession session, Model model) {
		Course course = courseServiceImpl.getCourse(Integer.valueOf(id));
		model.addAttribute("course", course);
		User user = (User) session.getAttribute("USER_IN_SESSION");
		int userId = user.getId();
		// System.out.println("userId" + userId);
		Practice practice = practiceServiceImpl.findPracticeByCourseAndUser(Integer.valueOf(id), userId);
		model.addAttribute("practice", practice);
		return "/trainee/practice/commit";
	}

	/**
	 * trainee提交练习页面
	 * 
	 * 需要trainee的id和课程的id
	 */
	@RequestMapping(value = { "/trainee/practice/save" })
	@ResponseBody
	public JsonBean traineeSave(@RequestBody Practice practice) {
		Practice practiceDate = practiceServiceImpl.getPractice(practice.getId());
		practiceDate.setContent(practice.getContent());
		practiceDate.setStatus(1);
		JsonBean jsonBean = new JsonBean(true, "上传成功", null);
		return jsonBean;
	}

}