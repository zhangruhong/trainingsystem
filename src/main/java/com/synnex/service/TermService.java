package com.synnex.service;

import java.util.List;

import com.synnex.model.PageResult;
import com.synnex.model.Term;

public interface TermService {
	public void addTerm(Term term);

	public Term getTerm(int id);

	public void update(Term term);

	/**
	 * 分页列表
	 * 
	 * @param baseQuery
	 * @return
	 */
	public PageResult<Term> listPageResult(int begin, int size);

	/**
	 * 通过trainee找term
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Term> listTermByTrainee(int traineeId);

	/**
	 * 通过trainee找term带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @param userId
	 * @return
	 */
	public PageResult<Term> listTermPageByTrainee(Integer page, int pagesize, int userId);
}
