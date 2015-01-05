package com.synnex.service;

import java.util.List;

import com.synnex.model.PageResult;
import com.synnex.model.UserCourse;

public interface UserCourseService {
	public void addAttendStatus(UserCourse usercourse, int courseid);
	public void addAttendStatuss(List<UserCourse> usercourses, int courseid);

	public PageResult<UserCourse> getAttendStatusByUser(int begin, int size, int user_id);

	public PageResult<UserCourse> getAttendStatusByCourseid(int begin, int size, int course_id);
}
