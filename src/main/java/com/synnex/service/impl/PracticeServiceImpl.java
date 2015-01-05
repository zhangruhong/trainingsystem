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
import com.synnex.utils.mailUtil.MailSenderInfo;
import com.synnex.utils.mailUtil.SimpleMailSender;

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

	@Override
	public void sendPracticeMailToTrainer(int practiceId) {
		Practice practice = practiceDao.get(practiceId);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("hty181818@163.com");
		mailInfo.setPassword("wwwcom130");// 您的邮箱密码
		mailInfo.setFromAddress("hty181818@163.com");
		mailInfo.setToAddress(practice.getUser().getEmail());
		mailInfo.setSubject(practice.getCourse().getName() + "课程作业习题");
		StringBuilder s = new StringBuilder();
		s.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
		s.append("<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->");
		s.append("<title>Training System</title><meta name='keywords' content='' />");
		s.append("<meta name='description' content='' /><meta name='viewport' content='width=device-width'>");
		s.append("</head><body>");
		s.append("Hi ").append(practice.getCourse().getTrainer().getLoginname()).append(",<br/>");
		s.append("&nbsp;&nbsp;").append(practice.getUser().getLoginname()).append(" have complete the exercise about ");
		s.append(practice.getCourse().getName()).append(",please check it as soon as possible.").append("<br/>");
		s.append("Thanks,<br/>Training System Admin");
		s.append("</body></html>");
		mailInfo.setContent(s.toString());
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendHtmlMail(mailInfo);
	}
}