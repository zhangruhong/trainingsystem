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
	public PageResult<Course> listCoursePageByTerm(int begin, int size, Integer termid, String name) {
		String hql = "select c from Course c";
		HqlUtils hqlUtils = new HqlUtils();
		hqlUtils.add("c.term.id = ?", termid);
		hqlUtils.addLike("c.name like ?", name);
		hqlUtils.addOrder("c.id", HqlUtils.DESCENDING);
		hql += hqlUtils.getWhereClause();
		PageResult<Course> pageResult = super.listPageResult(begin, size, hql, hqlUtils.getValues());
		return pageResult;
	}

	@Override
	public PageResult<Course> listCoursePageByTrainer(Integer page, int pagesize, int trainerId) {
		String hql = "select c from Course c where c.trainer.id = ?";
		PageResult<Course> pageResult = super.listPageResult(page, pagesize, hql, trainerId);
		return pageResult;
	}

}
