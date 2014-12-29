package com.synnex.dao;

import java.util.List;

import com.synnex.model.Course;

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

}
