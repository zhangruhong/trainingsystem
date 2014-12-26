package com.synnex.dao;

import com.synnex.model.Practice;

public interface PracticeDao extends GenericDao<Practice, Integer> {

	Practice findPracticeByCourseAndUser(int courseId, int traineeId);
}
