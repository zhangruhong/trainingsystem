package com.synnex.dao;

import java.util.List;

import com.synnex.model.PageResult;
import com.synnex.model.Term;

public interface TermDao extends GenericDao<Term, Integer> {
	public PageResult<Term> showByPage(int begin, int size);

	public List<Term> listTermByTrainee(int traineeId);
}
