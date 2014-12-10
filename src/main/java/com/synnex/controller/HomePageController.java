package com.synnex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController extends GenericController {

	/**
	 * 
	 * @return String url route
	 */
	@RequestMapping(value = "/home")
	public String homepage() {
		return "/common/homepage";
	}
}
