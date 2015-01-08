package com.synnex.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.synnex.dao.TermDao;
import com.synnex.model.PageResult;
import com.synnex.model.Term;

@Repository
public class TermDaoImpl extends GenericDaoImpl<Term, Integer> implements TermDao {

	@Override
	public PageResult<Term> showByPage(int begin, int size) {
		String hql = "from Term";
		return super.listPageResult(begin, size, hql);
	}

//	@Override
//	public List<Term> listTermByTrainee(int traineeId) {
//		String hql = "select distinct t from com.synnex.model.User u,com.synnex.model.Usergroup g,com.synnex.model.Term t where g.term.id = t.id and u in elements(g.users) and u.id = ?";
//		return super.findByHql(hql, traineeId);
//	}

	@Override
	public PageResult<Term> listTermPageByTrainee(Integer userId, Integer page, int pagesize) {
		// String hql =
		// "select distinct t from com.synnex.model.User u,com.synnex.model.Usergroup g,com.synnex.model.Term t where g.term.id = t.id and u in elements(g.users) and u.id = ?";
		// String hql = "select t from Term t,Usergroup g,User u";
		String hql = "select distinct t from Term t, Usergroup g, User u";
		HqlUtils hqlUtils = new HqlUtils();
		hqlUtils.addWhere("g.term.id = t.id and u in elements(g.users)");
		hqlUtils.add("u.id = ?", userId);
		hqlUtils.addOrder("t.id", HqlUtils.DESCENDING);
		hql += hqlUtils.getWhereClause();

		PageResult<Term> pageResult = super.listPageResult(page, pagesize, hql, userId);
		return pageResult;
	}
}
