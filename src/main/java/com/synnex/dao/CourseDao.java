package com.synnex.dao;

import java.util.List;

import com.synnex.model.Course;
import com.synnex.model.PageResult;

public interface CourseDao extends GenericDao<Course, Integer> {

	/**
	 * 通过term找course
	 * 
	 * @param termid
	 * @return
	 */
	List<Course> ListCourseByTerm(int termid);

	/**
	 * 通过trainer找course
	 * 
	 * @param trainerId
	 * @return
	 */
	List<Course> listCourseByTrainer(int trainerId);

	/**
	 * 通过trainee找course
	 * 
	 * @param traineeId
	 * @return
	 */
	List<Course> listCourseByTrainee(int traineeId);

	/**
	 * 通过term找course带分页
	 * 
	 * @param termid
	 * @return
	 */
	PageResult<Course> listCoursePageByTerm(int begin, int size, int termid);

	/**
	 * 通过trainer找course带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @param trainerId
	 * @return
	 */
	PageResult<Course> listCoursePageByTrainer(Integer page, int pagesize, int trainerId);

}
