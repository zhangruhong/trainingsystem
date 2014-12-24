package com.synnex.service;

import java.util.List;

import com.synnex.dao.Order;
import com.synnex.model.PageResult;
import com.synnex.model.Term;
import com.synnex.query.BaseQuery;

public interface TermService {
	public void addTerm(Term term);

	public Term getTerm(int id);

	public int getCount();

	public void update(Term term);

	public List<Term> getAllTerms(Object condition, List<Order> orders, int begin, int size);

	/**
	 * 分页列表
	 * 
	 * @param baseQuery
	 * @return
	 */
	public PageResult<Term> listPageResult(final BaseQuery baseQuery);
}
