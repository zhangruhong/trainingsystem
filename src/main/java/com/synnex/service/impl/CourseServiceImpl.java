package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.CourseDao;
import com.synnex.dao.Order;
import com.synnex.model.Course;
import com.synnex.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;

	@Override
	public void addCourse(Course course) {
		courseDao.save(course);
	}

	@Override
	public void addCourses(List<Course> courses) {
		courseDao.save(courses);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDao.delete(course);
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
		courseDao.update(course);

	}

	@Override
	public Course getCourse(Integer id) {
		return courseDao.get(id);
	}

	@Override
	public List<Course> getCoursesByCondition(Course condition, List<Order> orders, int begin, int size) {
		return courseDao.list(condition, orders, begin, size);
	}

}
