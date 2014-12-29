package com.synnex.service;

import java.util.List;

import com.synnex.dao.Order;
import com.synnex.model.Course;

public interface CourseService {

	/**
	 * 新建一个course对象
	 * 
	 * @param course
	 */
	public void addCourse(Course course, int termid);

	/**
	 * 批量新建Course对象
	 * 
	 * @param courses
	 */
	public void addCourses(List<Course> courses, int termid);

	/**
	 * 删除一个课程
	 * 
	 * @param course
	 */
	public void deleteCourse(Course course);

	/**
	 * 批量删除课程
	 * 
	 * @param courses
	 */
	public void deleteCourses(List<Course> courses);

	/**
	 * 更新一个课程
	 * 
	 * @param course
	 */
	public void updateCourse(Course course);

	/**
	 * 根据编号查课程
	 * 
	 * @param id
	 *            （此编号为系统内部编号，对所有人透明）
	 * @return
	 */
	public Course getCourse(Integer id);

	/**
	 * 
	 * @param condition
	 *            查询条件 被赋值的当前类
	 * @param orders
	 *            排列方式
	 * @param begin
	 *            开始页面
	 * @param size
	 *            步长
	 * @return
	 */
	public List<Course> getCoursesByCondition(Course condition, List<Order> orders, int begin, int size);

	/**
	 * 通过term找course
	 * 
	 * @param termid
	 * @return
	 */
	public List<Course> ListCourseByTerm(int termid);

	/**
	 * 通过trianer找course
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<Course> listCourseByTrainer(int trainerId);

}
