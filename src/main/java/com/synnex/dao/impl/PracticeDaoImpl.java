package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.PracticeDao;
import com.synnex.model.Practice;

@Repository
public class PracticeDaoImpl extends GenericDaoImpl<Practice, Integer> implements PracticeDao {

	@Override
	public Practice findPracticeByCourseAndUser(int courseId, int traineeId) {
		String hql = "select p from Practice p where p.course.id = ? and p.user.id = ?";
		List<Practice> pracices = super.findByHql(hql, courseId, traineeId);
		return pracices.get(0);
	}
}
