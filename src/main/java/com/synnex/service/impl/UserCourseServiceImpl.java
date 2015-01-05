package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.CourseDao;
import com.synnex.dao.UserCourseDao;
import com.synnex.dao.UserDao;
import com.synnex.model.Course;
import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.model.UserCourse;
import com.synnex.service.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {
	@Resource(name = "userCourseDaoImpl")
	private UserCourseDao userCourseDaoImpl;
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDaoImpl;
	@Resource(name = "userDaoImpl")
	private UserDao userDaoImpl;

	@Override
	public void addAttendStatus(UserCourse usercourse, int courseid) {
		Course course = courseDaoImpl.get(courseid);
		User user = userDaoImpl.get(usercourse.getUser().getId());
		usercourse.setCourse(course);
		usercourse.setUser(user);
		userCourseDaoImpl.save(usercourse);
	}

	@Override
	public PageResult<UserCourse> getAttendStatusByUser(int begin, int size, int user_id) {
		return userCourseDaoImpl.getUserCoursesByUserid(begin, size, user_id);
	}

	@Override
	public PageResult<UserCourse> getAttendStatusByCourseid(int begin, int size, int course_id) {
		return userCourseDaoImpl.getUserCoursesByCourseid(begin, size, course_id);
	}

	@Override
	public void addAttendStatuss(List<UserCourse> usercourses, int courseid) {
		for (UserCourse userCourse : usercourses) {
			int id = userCourse.getUser().getId();
			User user = userDaoImpl.get(id);
			Course course = courseDaoImpl.get(courseid);
			userCourse.setUser(user);
			userCourse.setCourse(course);
			//TODO 应该先查一下有没有 没有再执行
			userCourseDaoImpl.getSession().saveOrUpdate(userCourse);
		}
	}

}
