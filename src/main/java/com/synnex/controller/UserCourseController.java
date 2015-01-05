package com.synnex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.model.UserCourse;
import com.synnex.model.UserCourseList;
import com.synnex.utils.jsonUtil.JsonBean;

@Controller
public class UserCourseController extends GenericController {
	// 根据讲师这节课 显示所有学生的出勤状态
	@RequestMapping(value = { "/trainer/term/{termid}/courses/{courseid}/attendstatus/show" })
	public String showAttendStatusByCourse(@PathVariable("termid") int termid, @PathVariable("courseid") int courseid, Model model) {
		// PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByCourseid(1, 10, courseid);
		List<User> users = userServiceImpl.findAllTraineeInTerm(termid);
		// model.addAttribute("pageresult", ucs);
		model.addAttribute("courseid", courseid);
		model.addAttribute("users", users);
		return "/trainer/usercourse/show";
	}

	@RequestMapping(value = { "/admin/user/{userid}/attendstatus/show", "/trainee/{userid}/courses/attendstatus/show" })
	public String showAttendStatusByUser(@PathVariable("userid") int userid, Model model) {
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByUser(1, 10, userid);
		model.addAttribute("usercourses", ucs);
		return "////xxx";
	}

	@RequestMapping(value = { "/trainer/term/{termid}/courses/{courseid}/attendstatus/update" })
	public String addAttendStatus(UserCourseList usercourses, @PathVariable("courseid") int courseid,@PathVariable("termid") int termid) {
		// 谁、出勤状态、备注
		List<UserCourse> ucs=usercourses.getUserCourses();
		if (null==ucs) {
			return "redirect:/trainer/term/"+termid+"/courses/"+courseid+"/attendstatus/show";
		}
		userCourseServiceImpl.addAttendStatuss(ucs, courseid);	
		return "redirect:/trainer/term/"+termid+"/courses/"+courseid+"/attendstatus/show";
	}
}
