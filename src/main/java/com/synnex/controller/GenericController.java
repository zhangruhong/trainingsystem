package com.synnex.controller;

import javax.annotation.Resource;

import com.synnex.service.CourseService;
import com.synnex.service.UserService;

/*
 *@author Jeniss 2014-3-25 ����3:52:22
 *@tags
 */
public class GenericController {
	
	@Resource(name = "userServiceImpl")
	public UserService userService;

	@Resource(name = "courseServiceImpl")
	public CourseService courseService;
}
