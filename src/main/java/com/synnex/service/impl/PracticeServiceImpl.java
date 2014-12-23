package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.PracticeDao;
import com.synnex.model.Practice;
import com.synnex.service.PracticeService;

@Service("practiceServiceImpl")
public class PracticeServiceImpl implements PracticeService {

	@Resource(name = "practiceDaoImpl")
	private PracticeDao practiceDao;

	@Override
	public void addPractice(Practice practice) {
		practiceDao.save(practice);
	}

	@Override
	public void addPractices(List<Practice> practices) {
		practiceDao.save(practices);
	}

	@Override
	public void deletePractice(Practice practice) {
		practiceDao.delete(practice);
	}

	@Override
	public void deletePractices(List<Practice> practices) {
		// TODO 有待优化
		for (Practice practice : practices) {
			this.deletePractice(practice);
		}
	}

	@Override
	public void updatePractice(Practice practice) {
		practiceDao.update(practice);

	}

	@Override
	public void updatePractices(List<Practice> practices) {
		practiceDao.update(practices);
	}

	@Override
	public Practice getPractice(Integer id) {
		return practiceDao.get(id);
	}

	@Override
	public List<Practice> getPracticesByCondition(Practice condition, List<Order> orders, int begin, int size) {
		return practiceDao.list(condition, orders, begin, size);
	}
}