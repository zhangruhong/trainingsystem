package com.synnex.dao;

import java.util.List;

import com.synnex.model.Practice;

public interface PracticeDao extends GenericDao<Practice, Integer> {

	/**
	 * 根据course和user找practice
	 * 
	 * @param courseId
	 * @param traineeId
	 * @return
	 */
	Practice findPracticeByCourseAndUser(int courseId, int traineeId);

	/**
	 * 根据course找practice集合
	 * 
	 * @param courseId
	 * @return
	 */
	List<Practice> findPracticeByCourse(int courseId);
}
