package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.PracticeDao;
import com.synnex.dao.UserDao;
import com.synnex.model.Course;
import com.synnex.model.Practice;
import com.synnex.model.User;
import com.synnex.service.PracticeService;

@Service("practiceServiceImpl")
public class PracticeServiceImpl implements PracticeService {

	@Resource(name = "practiceDaoImpl")
	private PracticeDao practiceDao;

	@Resource(name = "userDaoImpl")
	private UserDao userDao;

	@Override
	public void addPractice(Practice practice) {
		practiceDao.save(practice);
	}

	@Override
	public void addPractices(Integer courseId) {
		List<User> users = userDao.queryUserByCourse(courseId);
		for (User user : users) {
			Practice practice = new Practice();
			practice.setStatus(0);
			practice.setUser(user);
			Course course = new Course();
			course.setId(courseId);
			practice.setCourse(course);
			practiceDao.save(practice);
		}
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
	public Practice findPracticeByCourseAndUser(int courseId, int traineeId) {
		Practice practice = practiceDao.findPracticeByCourseAndUser(courseId, traineeId);
		return practice;
	}

	@Override
	public List<Practice> findPracticeByCourse(int courseId) {
		List<Practice> practices = practiceDao.findPracticeByCourse(courseId);
		return practices;
	}

	@Override
	public List<Practice> findPracticeByLoginname(String loginname) {
		List<Practice> practices = practiceDao.findPracticeByLoginname(loginname);
		return practices;
	}

	@Override
	public List<Practice> findPracticeByUser(int traineeId) {
		List<Practice> practices = practiceDao.findPracticeByUser(traineeId);
		return practices;
	}
}