package com.synnex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synnex.model.Course;
import com.synnex.model.PageResult;
import com.synnex.model.Practice;
import com.synnex.model.User;
import com.synnex.utils.jsonUtil.JsonBean;
import com.synnex.utils.variable.SystemVariable;

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
	public String trainerInputPractice(@RequestParam("id") String id, Model model) {
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
	public JsonBean trainerSavePractice(@RequestBody Course course) {
		Course courseDate = courseServiceImpl.getCourse(course.getId());
		if (courseDate.getPractiseStatus() == null || courseDate.getPractiseStatus() == 0) {
			practiceServiceImpl.addPractices(course.getId());
			courseDate.setPractiseStatus(1);
		}
		courseDate.setPractise(course.getPractise());
		courseServiceImpl.updateCourse(courseDate);
		userServiceImpl.sendPracticeMailToTrainee(courseDate);
		JsonBean jsonBean = new JsonBean(true, "上传成功", null);
		return jsonBean;
	}

	/**
	 * trainer查看练习进度页面
	 * 
	 * 需要trainer的id
	 */
	@RequestMapping(value = { "/trainer/practice/view" })
	public String trainerViewPractice(@RequestParam(value = "page", required = false) Integer page, HttpSession session, Model model) {
		User user = (User) session.getAttribute("USER_IN_SESSION");
		int TrainerId = user.getId();
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Course> pageResult = courseServiceImpl.listCoursePageByTrainer(page, SystemVariable.PageSize, TrainerId);
		model.addAttribute("pageResult", pageResult);
		return "/trainer/practice/view";
	}

	/**
	 * trainer管理练习进度页面
	 * 
	 * 需要trainer的id
	 */
	@RequestMapping(value = { "/trainer/practice/arrange" })
	public String trainerArrangePractice(@RequestParam(value = "page", required = false) Integer page, HttpSession session, Model model) {
		User user = (User) session.getAttribute("USER_IN_SESSION");
		int TrainerId = user.getId();
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Course> pageResult = courseServiceImpl.listCoursePageByTrainer(page, SystemVariable.PageSize, TrainerId);
		model.addAttribute("pageResult", pageResult);
		return "/trainer/practice/arrange";
	}

	/**
	 * trainer管理课程进度页面
	 * 
	 * 需要trainer的id
	 */
	@RequestMapping(value = { "/trainer/practice/progress" })
	public String trainerCuorseProcess(@RequestParam(value = "page", required = false) Integer page, HttpSession session, Model model) {
		User user = (User) session.getAttribute("USER_IN_SESSION");
		int TrainerId = user.getId();
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Course> pageResult = courseServiceImpl.listCoursePageByTrainer(page, SystemVariable.PageSize, TrainerId);
		model.addAttribute("pageResult", pageResult);
		return "/trainer/practice/progress";
	}

	/**
	 * trainer查看练习进度详细页面
	 * 
	 * 需要trainer的id
	 */
	@RequestMapping(value = { "/trainer/practice/viewDetail" })
	public String trainerViewPracticeDetail(@RequestParam(value = "page", required = false) Integer page, @RequestParam("id") int id, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		PageResult<Practice> pageResult = practiceServiceImpl.listPracticePageByCourse(page, SystemVariable.PageSize, id);
		model.addAttribute("pageResult", pageResult);
		Course course = courseServiceImpl.getCourse(id);
		model.addAttribute("course", course);
		return "/trainer/practice/viewDetail";
	}

	/**
	 * trainer录入分数
	 * 
	 * 需要practice的id和score
	 */
	@RequestMapping(value = { "/trainer/practice/inputScore" })
	@ResponseBody
	public JsonBean trainerInputScore(@RequestBody Practice practice) {
		Practice practiceData = practiceServiceImpl.getPractice(practice.getId());
		practiceData.setScore(practice.getScore());
		practiceData.setScoreDescription(practice.getScoreDescription());
		practiceData.setStatus(2);
		practiceServiceImpl.updatePractice(practiceData);
		JsonBean jsonBean = new JsonBean(true, "录入成功", null);
		return jsonBean;
	}

	/**
	 * trainee跳转到查看练习页面 从菜单跳转
	 * 
	 */
	@RequestMapping("/trainee/practice/showall")
	public String traineeViewAllPractice(@RequestParam(value = "page", required = false) Integer page, HttpSession session, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		User trainee = (User) session.getAttribute("USER_IN_SESSION");
		int traineeId = trainee.getId();
		PageResult<Practice> pageResult = practiceServiceImpl.listPracticePageByTrainee(page, SystemVariable.PageSize, traineeId, null);
		model.addAttribute("pageResult", pageResult);
		return "/trainee/practice/showall";
	}

	@RequestMapping("/trainee/practice/show/{courseId}")
	public String traineeViewPractice(@PathVariable Integer courseId, @RequestParam(value = "page", required = false) Integer page,
			HttpSession session, Model model) {
		if (null == page || page < 1) {
			page = 1;
		}
		User trainee = (User) session.getAttribute("USER_IN_SESSION");
		int traineeId = trainee.getId();
		PageResult<Practice> pageResult = practiceServiceImpl.listPracticePageByTrainee(page, SystemVariable.PageSize, traineeId, courseId);
		model.addAttribute("pageResult", pageResult);
		return "/trainee/practice/show";
	}

	/**
	 * trainee跳转到提交练习页面
	 * 
	 * 需要课程id和trainee的id
	 */
	@RequestMapping("/trainee/practice/commit")
	public String traineeInputPractice(@RequestParam("id") String id, HttpSession session, Model model) {
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
	public JsonBean traineeSavePractice(@RequestBody Practice practice) {
		Practice practiceDate = practiceServiceImpl.getPractice(practice.getId());
		practiceDate.setContent(practice.getContent());
		practiceDate.setStatus(1);
		practiceServiceImpl.updatePractice(practiceDate);
		practiceServiceImpl.sendPracticeMailToTrainer(practiceDate.getId());
		JsonBean jsonBean = new JsonBean(true, "上传成功", null);
		return jsonBean;
	}

	/**
	 * admin跳转到练习查看页面（主要是查看分数）
	 * 
	 */
	@RequestMapping("/admin/practice/view")
	public String adminViewPractice() {
		return "/admin/practice/show";
	}

	/**
	 * admin查询practice页面（主要是查看分数）
	 * 
	 */
	@RequestMapping("/admin/practice/search")
	@ResponseBody
	public JsonBean adminSearchPractice(@RequestBody User user) {
		String loginname = user.getLoginname();
		List<Practice> practices = practiceServiceImpl.findPracticeByLoginname(loginname);
		JsonBean jsonBean = new JsonBean(true, "上传成功", practices);
		return jsonBean;
	}
}