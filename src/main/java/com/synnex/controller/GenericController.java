package com.synnex.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synnex.service.CourseService;
import com.synnex.service.TermService;
import com.synnex.service.UserGroupService;
import com.synnex.service.UserService;

/*
 *@author Jeniss 2014-3-25 ����3:52:22
 *@tags
 */
public class GenericController {
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "userServiceImpl")
	public UserService userServiceImpl;

	@Resource(name = "courseServiceImpl")
	public CourseService courseServiceImpl;

	@Resource(name = "userGroupServiceImpl")
	public UserGroupService userGroupServiceImpl;

	@Resource(name = "termServiceImpl")
	public TermService termServiceImpl;
}
