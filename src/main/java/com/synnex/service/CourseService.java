package com.synnex.service;

import java.util.List;

import com.synnex.model.Course;
import com.synnex.model.PageResult;

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

	/**
	 * 通过trianee找course
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<Course> listCourseByTrainee(int traineeId);

	/**
	 * 通过term找course带分页
	 * 
	 * @param termid
	 * @return
	 */
	public PageResult<Course> listCoursePageByTerm(int begin, int size, int termid);

	/**
	 * 通过trianer找course带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @param trainerId
	 * @return
	 */
	public PageResult<Course> listCoursePageByTrainer(Integer page, int pagesize, int trainerId);

}
