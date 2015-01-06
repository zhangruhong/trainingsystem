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
		PageResult<UserCourse> ucs = userCourseDaoImpl.getUserCoursesByUserid(begin, size, user_id);
		for (UserCourse uc : ucs.getRows()) {
			uc.getCourse().getName();
		}
		return ucs;
	}

	@Override
	public PageResult<UserCourse> getAttendStatusByCourseid(int begin, int size, int course_id) {
		PageResult<UserCourse> ucs = userCourseDaoImpl.getUserCoursesByCourseid(begin, size, course_id);
		for (UserCourse uc : ucs.getRows()) {
			uc.getUser().getLoginname();
		}
		return ucs;
	}

	@Override
	public void addAttendStatuss(List<UserCourse> usercourses, int courseid) {
		for (UserCourse userCourse : usercourses) {
			int userid = userCourse.getUser().getId();
			List<UserCourse> existlist = userCourseDaoImpl.checkExist(courseid, userid);
			// 没有获取到即不存在
			if (null == existlist || existlist.size() == 0) {
				User user = userDaoImpl.get(userid);
				Course course = courseDaoImpl.get(courseid);
				userCourse.setUser(user);
				userCourse.setCourse(course);
				userCourseDaoImpl.save(userCourse);
			} else {
				// 存在就更新
				UserCourse updatebean = existlist.get(0);
				updatebean.setAttendCourseStatus(userCourse.getAttendCourseStatus());
				updatebean.setDescription(userCourse.getDescription());
				userCourseDaoImpl.update(updatebean);
			}

		}
	}

}
