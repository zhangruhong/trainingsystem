package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.PracticeDao;
import com.synnex.model.PageResult;
import com.synnex.model.Practice;

@Repository
public class PracticeDaoImpl extends GenericDaoImpl<Practice, Integer> implements PracticeDao {

	@Override
	public Practice findPracticeByCourseAndUser(int courseId, int traineeId) {
		String hql = "select p from Practice p where p.course.id = ? and p.user.id = ?";
		List<Practice> pracices = super.findByHql(hql, courseId, traineeId);
		return pracices.get(0);
	}

	@Override
	public List<Practice> findPracticeByCourse(int courseId) {
		String hql = "select p from Practice p where p.course.id = ?";
		List<Practice> pracices = super.findByHql(hql, courseId);
		for (Practice practice : pracices) {
			practice.getUser().getLoginname();
			practice.getCourse().getName();
		}
		return pracices;
	}

	@Override
	public List<Practice> findPracticeByLoginname(String loginname) {
		String hql = "select p from User u , Practice p where p.user.id = u.id and u.loginname = ?";
		List<Practice> pracices = super.findByHql(hql, loginname);
		return pracices;
	}

	@Override
	public List<Practice> findPracticeByUser(int traineeId) {
		String hql = "select p from Practice p where p.user.id = ?";
		List<Practice> pracices = super.findByHql(hql, traineeId);
		return pracices;
	}

	@Override
	public PageResult<Practice> listPracticePageByTrainee(Integer page, int pagesize, Integer traineeId, Integer courseId) {
		String hql = "select p from Practice p";
		HqlUtils hqlUtils = new HqlUtils();
		hqlUtils.add("p.user.id = ?", traineeId);
		hqlUtils.add("p.course.id=?", courseId);
		hql += hqlUtils.getWhereClause();
		PageResult<Practice> pageResult = super.listPageResult(page, pagesize, hql, hqlUtils.getValues());
		return pageResult;
	}

	@Override
	public PageResult<Practice> listPracticePageByCourse(Integer page, int pagesize, int courseId) {
		String hql = "select p from Practice p where p.course.id = ?";
		PageResult<Practice> pageResult = super.listPageResult(page, pagesize, hql, courseId);
		return pageResult;
	}
}
