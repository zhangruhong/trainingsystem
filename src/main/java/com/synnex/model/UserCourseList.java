package com.synnex.model;

import java.util.ArrayList;
import java.util.List;

public class UserCourseList {
	List<UserCourse> userCourses = new ArrayList<UserCourse>();

	public UserCourseList() {
		super();
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	public UserCourseList(List<UserCourse> userCourses) {
		super();
		this.userCourses = userCourses;
	}

}
