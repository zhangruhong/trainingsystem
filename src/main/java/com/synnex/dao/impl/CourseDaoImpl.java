package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.CourseDao;
import com.synnex.model.Course;

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

}
