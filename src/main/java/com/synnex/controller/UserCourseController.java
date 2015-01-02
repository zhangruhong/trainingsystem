package com.synnex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.PageResult;
import com.synnex.model.UserCourse;
import com.synnex.utils.jsonUtil.JsonBean;

@Controller
public class UserCourseController extends GenericController {
	// 根据讲师这节课 显示所有学生的出勤状态
	@RequestMapping(value = { "/trainer/term/{termid}/courses/{courseid}/attendstatus/show" })
	public String showAttendStatusByCourse(@PathVariable("termid") int termid, @PathVariable("courseid") int courseid, Model model) {
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByCourseid(1, 10, courseid);
		model.addAttribute("usercourses", ucs);
		return "////xxx";
	}

	@RequestMapping(value = { "/admin/user/{userid}/attendstatus/show", "/trainee/{userid}/courses/attendstatus/show" })
	public String showAttendStatusByUser(@PathVariable("userid") int userid, Model model) {
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByUser(1, 10, userid);
		model.addAttribute("usercourses", ucs);
		return "////xxx";
	}

	@RequestMapping(value = { "/trainer/courses/{courseid}/attendstatus/add" })
	@ResponseBody
	public JsonBean addAttendStatus(@RequestBody UserCourse userCourse, @PathVariable("courseid") int courseid) {
		// 谁、出勤状态、备注
		userCourseServiceImpl.addAttendStatus(userCourse, courseid);
		JsonBean jsonBean = new JsonBean(true, "标注成功", null);
		return jsonBean;
	}
}
