package com.synnex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作业相关操作
 * 
 * @author hiramh
 *
 */
@Controller
@RequestMapping("/trainer/practice")
public class PracticeController extends GenericController {

	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping(value = { "/{courseid}/input" })
	public String input() {
		return "/trainer/practice/show";
	}
}