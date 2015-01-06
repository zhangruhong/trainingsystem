package com.synnex.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.model.UserCourse;
import com.synnex.model.UserCourseList;

@Controller
public class UserCourseController extends GenericController {
	// 根据讲师这节课 显示所有学生的出勤状态
	@RequestMapping(value = { "/trainer/term/{termid}/courses/{courseid}/attendstatus/show" })
	public String showAttendStatusByCourse(@PathVariable("termid") int termid, @PathVariable("courseid") int courseid, Model model) {
		List<User> users = userServiceImpl.findAllTraineeInTerm(termid);
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByCourseid(1, 10, courseid);
		Map<Integer, UserCourse> usercoursemap = new TreeMap<Integer, UserCourse>();
		for (UserCourse usercourse : ucs.getRows()) {
			usercoursemap.put(usercourse.getUser().getId(), usercourse);
		}

		model.addAttribute("courseid", courseid);
		model.addAttribute("users", users);
		model.addAttribute("usercoursemap", usercoursemap);
		return "/trainer/usercourse/show";
	}

	@RequestMapping(value = { "/admin/term/{termid}/courses/{courseid}/attendstatus/show" })
	public String showAttendStatusByCourseForAdmin(@PathVariable("termid") int termid, @PathVariable("courseid") int courseid, Model model) {
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByCourseid(1, 10, courseid);
		Map<String, UserCourse> usercoursemap = new TreeMap<String, UserCourse>();
		for (UserCourse usercourse : ucs.getRows()) {
			usercoursemap.put(usercourse.getUser().getLoginname(), usercourse);
		}

		model.addAttribute("courseid", courseid);
		model.addAttribute("usercoursemap", usercoursemap);
		return "/admin/usercourse/listuserbycourse";
	}

	@RequestMapping(value = { "/admin/term/{termid}/user/{userid}/courses/attendstatus/show",
			"/term/{termid}/trainee/{userid}/courses/attendstatus/show" })
	public String showAttendStatusByUser(@PathVariable("userid") int userid, @PathVariable("termid") int termid, Model model) {
		PageResult<UserCourse> ucs = userCourseServiceImpl.getAttendStatusByUser(1, 10, userid);
		List<UserCourse> usercourses = ucs.getRows();
		Map<String, UserCourse> listCourseofuser = new TreeMap<String, UserCourse>();
		for (UserCourse userCourse : usercourses) {
			listCourseofuser.put(userCourse.getCourse().getName(), userCourse);
		}
		model.addAttribute("listCourseofuser", listCourseofuser);
		return "/admin/usercourse/listcoursebyuser";
	}

	@RequestMapping(value = { "/trainer/term/{termid}/courses/{courseid}/attendstatus/update" })
	public String addAttendStatus(UserCourseList usercourses, @PathVariable("courseid") int courseid, @PathVariable("termid") int termid,
			HttpServletRequest request) {
		String param = request.getParameter("userCourses[0].description");
		// 谁、出勤状态、备注
		List<UserCourse> ucs = usercourses.getUserCourses();
		if (null == ucs || ucs.size() == 0) {
			return "redirect:/trainer/term/" + termid + "/courses/" + courseid + "/attendstatus/show";
		}
		userCourseServiceImpl.addAttendStatuss(ucs, courseid);
		return "redirect:/trainer/term/" + termid + "/courses/" + courseid + "/attendstatus/show";
	}
}
