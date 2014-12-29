package com.synnex.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.CourseDao;
import com.synnex.dao.Order;
import com.synnex.dao.TermDao;
import com.synnex.model.Course;
import com.synnex.model.Term;
import com.synnex.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDaoImpl;
	@Resource(name = "termDaoImpl")
	private TermDao termDaoImpl;

	@Override
	public void addCourse(Course course, int termid) {
		Term term = termDaoImpl.get(termid);

		Set<Course> courses = term.getCourses();
		courses.add(course);
		term.setCourses(courses);
		termDaoImpl.update(term);

		course.setTerm(term);
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
		return courseDaoImpl.get(id);
	}

	@Override
	public List<Course> getCoursesByCondition(Course condition, List<Order> orders, int begin, int size) {
		return courseDaoImpl.list(condition, orders, begin, size);
	}

	@Override
	public List<Course> ListCourseByTerm(int termid) {
		List<Course> courses = courseDaoImpl.ListCourseByTerm(termid);
		for (Course course : courses) {
			course.getTerm().getName();
		}
		return courses;
	}

}
