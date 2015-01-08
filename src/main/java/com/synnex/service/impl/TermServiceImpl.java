package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.TermDao;
import com.synnex.model.PageResult;
import com.synnex.model.Term;
import com.synnex.service.TermService;

@Service
public class TermServiceImpl implements TermService {
	@Resource(name = "termDaoImpl")
	private TermDao termDaoImpl;

	@Override
	public void addTerm(Term term) {
		termDaoImpl.save(term);
	}

	@Override
	public Term getTerm(int id) {
		return termDaoImpl.get(id);
	}

	@Override
	public void update(Term term) {
		termDaoImpl.update(term);
		return;

	}

	@Override
	public PageResult<Term> listPageResult(int begin, int size) {
		return termDaoImpl.showByPage(begin, size);
	}

//	@Override
//	public List<Term> listTermByTrainee(int traineeId) {
//		List<Term> terms = termDaoImpl.listTermByTrainee(traineeId);
//		return terms;
//	}

	@Override
	public PageResult<Term> listTermPageByTrainee(Integer userId, Integer page, int pagesize) {
		PageResult<Term> pageResult = termDaoImpl.listTermPageByTrainee(userId, page, pagesize);
		return pageResult;
	}

}
