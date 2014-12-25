package com.synnex.dao.impl;

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
}
