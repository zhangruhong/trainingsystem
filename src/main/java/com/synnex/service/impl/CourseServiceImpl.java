package com.synnex.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.CourseDao;
import com.synnex.dao.TermDao;
import com.synnex.dao.UserCourseDao;
import com.synnex.dao.UserDao;
import com.synnex.model.Course;
import com.synnex.model.PageResult;
import com.synnex.model.Term;
import com.synnex.model.UserCourse;
import com.synnex.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDaoImpl;
	@Resource(name = "termDaoImpl")
	private TermDao termDaoImpl;
	@Resource(name = "userCourseDaoImpl")
	private UserCourseDao userCourseDaoImpl;
	@Resource(name = "userDaoImpl")
	private UserDao userDaoImpl;

	@Override
	public void addCourse(Course course, int termid) {
		Term term = termDaoImpl.get(termid);

		Set<Course> courses = term.getCourses();
		courses.add(course);
		term.setCourses(courses);
		termDaoImpl.update(term);
		course.setTerm(term);
		UserCourse us = new UserCourse();
		us.setCourse(course);

		courseDaoImpl.save(course);
	}

	@Override
	public void addCourses(List<Course> courses, int termid) {
		for (Course course : courses) {
			this.addCourse(course, termid);
		}
	}

	@Override
	public void deleteCourse(Course course) {
		courseDaoImpl.delete(course);
	}

	@Override
	public void deleteCourses(List<Course> courses) {
		// TODO 有待优化
		for (Course course : courses) {
			this.deleteCourse(course);
		}
	}

	@Override
	public void updateCourse(Course course) {
		courseDaoImpl.update(course);
	}

	@Override
	public Course getCourse(Integer id) {
		Course course = courseDaoImpl.get(id);
		course.getTrainer().getLoginname();
		return course;
	}


	@Override
	public List<Course> ListCourseByTerm(int termid) {
		List<Course> courses = courseDaoImpl.ListCourseByTerm(termid);
		for (Course course : courses) {
			course.getTerm().getName();
		}
		return courses;
	}

	@Override
	public List<Course> listCourseByTrainer(int trainerId) {
		List<Course> courses = courseDaoImpl.listCourseByTrainer(trainerId);
		return courses;
	}

	@Override
	public List<Course> listCourseByTrainee(int traineeId) {
		List<Course> courses = courseDaoImpl.listCourseByTrainee(traineeId);
		return courses;
	}

	@Override
	public PageResult<Course> ListCoursePageByTerm(int begin, int size, int termid) {
		PageResult<Course> pageResult = courseDaoImpl.ListCoursePageByTerm(begin, size, termid);
		List<Course> courses = pageResult.getRows();
		for (Course course : courses) {
			course.getTerm().getName();
		}
		return pageResult;
	}

}
