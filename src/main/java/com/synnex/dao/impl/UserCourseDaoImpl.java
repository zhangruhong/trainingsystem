package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.UserCourseDao;
import com.synnex.model.PageResult;
import com.synnex.model.UserCourse;

@Repository
public class UserCourseDaoImpl extends GenericDaoImpl<UserCourse, Integer> implements UserCourseDao {

	@Override
	public PageResult<UserCourse> getUserCoursesByCourseid(int begin, int size, int courseid) {
		String hql = "from UserCourse uc where uc.course.id=?";
		PageResult<UserCourse> ucs = listPageResult(begin, size, hql, courseid);
		return ucs;
	}

	@Override
	public PageResult<UserCourse> getUserCoursesByUserid(int begin, int size, int user_id) {
		String hql = "from UserCourse uc where uc.user.id=?";
		PageResult<UserCourse> ucs = listPageResult(begin, size, hql, user_id);
		return ucs;
	}

	@Override
	public List<UserCourse> checkExist(int userid, int courseid) {
		String hql = "from UserCourse uc where uc.user.id=? and uc.course.id=?";
		List<UserCourse> userCourses = findByHql(hql, userid, courseid);
		return userCourses;
	}

}
