package com.synnex.service;

import java.util.List;

import com.synnex.model.Term;

public interface TermService {
	public void addTerm(Term term);
	public Term getTerm(int id);
	public List<Term> getAllTerms();
}
