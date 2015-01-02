package com.synnex.model;

import java.io.Serializable;

public class UserCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	// 课程进度有3种状态：0-New、1-Absent、2-Completed
	private int attendCourseStatus;
	private String description;
	private User user;
	private Course course;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttendCourseStatus() {
		return attendCourseStatus;
	}

	public void setAttendCourseStatus(int attendCourseStatus) {
		this.attendCourseStatus = attendCourseStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
