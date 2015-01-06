package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.CourseDao;
import com.synnex.model.Course;
import com.synnex.model.PageResult;

@Repository
public class CourseDaoImpl extends GenericDaoImpl<Course, Integer> implements CourseDao {

	@Override
	public List<Course> ListCourseByTerm(int termid) {
		String hql = "select c from Course c where c.term.id = ?";
		return super.findByHql(hql, termid);
	}

	@Override
	public List<Course> listCourseByTrainer(int trainerId) {
		String hql = "select c from Course c where c.trainer.id = ?";
		List<Course> courses = super.findByHql(hql, trainerId);
		for (Course course : courses) {
			course.getTerm().getName();
			course.getDictionary().getName();
		}
		return courses;
	}

	@Override
	public List<Course> listCourseByTrainee(int traineeId) {
		String hql = "select distinct c from com.synnex.model.User u,com.synnex.model.Usergroup g,com.synnex.model.Term t,com.synnex.model.Course c where c.term.id = t.id and g.term.id = t.id and u in elements(g.users) and u.id = ?";
		List<Course> courses = super.findByHql(hql, traineeId);
		for (Course course : courses) {
			course.getTerm().getName();
			course.getDictionary().getName();
		}
		return courses;
	}

	@Override
	public PageResult<Course> ListCoursePageByTerm(int begin, int size, int termid) {
		String hql = "select c from Course c where c.term.id = ?";
		PageResult<Course> pageResult = super.listPageResult(begin, size, hql, termid);
		return pageResult;
	}

}
