package com.synnex.service;

import java.util.List;

import com.synnex.dao.Order;
import com.synnex.model.Term;

public interface TermService {
	public void addTerm(Term term);
	public Term getTerm(int id);
	public int getCount();

	public void update(Term term);
	public List<Term> getAllTerms(Object condition, List<Order> orders, int begin, int size);
}
