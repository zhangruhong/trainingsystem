package com.synnex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@RequestMapping(value = { "/trainer/practice/input" })
	public String trainerInput(@RequestParam("id") String id, Model model) {
		model.addAttribute("courseId", id);
		return "/trainer/practice/show";
	}

	/**
	 * trainer保存练习页面
	 * 
	 * 需要trainer的id和课程的id
	 */
	@RequestMapping(value = { "/trainer/practice/save" })
	public String trainSave(String courseId) {
		return "/trainer/practice/show";
	}
}