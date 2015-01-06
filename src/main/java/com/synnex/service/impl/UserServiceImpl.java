package com.synnex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.Order;
import com.synnex.dao.UserDao;
import com.synnex.exception.LogicException;
import com.synnex.model.Course;
import com.synnex.model.PageResult;
import com.synnex.model.User;
import com.synnex.service.UserService;
import com.synnex.utils.mailUtil.MailSenderInfo;
import com.synnex.utils.mailUtil.SimpleMailSender;
import com.synnex.utils.md5Util.Md5Encode;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDaoImpl")
	private UserDao userDaoImpl;

	@Override
	public void addUser(User user) {
		userDaoImpl.save(user);
	}

	@Override
	public void addUsers(List<User> users) {
		userDaoImpl.save(users);
	}

	@Override
	public void deleteUser(User user) {
		userDaoImpl.delete(user);
	}

	@Override
	public void deleteUsers(List<User> users) {
		// TODO 有待优化
		for (User user : users) {
			this.deleteUser(user);
		}
	}

	@Override
	public void updateUser(User user) {
		userDaoImpl.update(user);

	}

	@Override
	public void updateUsers(List<User> users) {
		userDaoImpl.update(users);
	}

	@Override
	public User getUser(Integer id) {
		return userDaoImpl.get(id);
	}

	@Override
	public User checkLogin(String username, String password) {
		// 验证用户名
		password = Md5Encode.getStringMD5(password);
		String hql = "FROM User o WHERE o.loginname=? and o.password=?";
		List<User> users = userDaoImpl.findByHql(hql, username, password);
		if (users == null || users.size() != 1) {
			throw new LogicException("用户名或密码错误！！", -101);
		}
		User user = users.get(0);
		return user;
	}

	/**
	 * 已在底层添加前后%实现了 向前、向后都匹配搜索
	 */
	@Override
	public List<User> listByNameSimilar(User condition, List<Order> orders, int startpage, int pagesize) {
		return userDaoImpl.listByNameSimilar(condition, orders, startpage, pagesize);
	}

	@Override
	public User findTranerbyName(String loginname) {
		return userDaoImpl.findUserbyName(loginname, 1);
	}

	@Override
	public List<User> getAllUsers() {
		return userDaoImpl.findAllUsers();
	}

	@Override
	public User gettraineeByName(String loginname) {
		return userDaoImpl.findUserbyName(loginname, 2);
	}

	@Override
	public List<User> findAllTraineeInTerm(int course_id) {
		return userDaoImpl.queryUserByCourse(course_id);
	}

	@Override
	public void sendPracticeMailToTrainee(Course course) {
		List<User> users = userDaoImpl.queryUserByCourse(course.getId());
		for (User user : users) {
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.163.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			mailInfo.setUserName("hty181818@163.com");
			mailInfo.setPassword("wwwcom130");// 您的邮箱密码
			mailInfo.setFromAddress("hty181818@163.com");
			mailInfo.setToAddress(user.getEmail());
			mailInfo.setSubject(course.getName() + "课程作业习题");
			StringBuilder s = new StringBuilder();
			s.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
			s.append("<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->");
			s.append("<title>Training System</title><meta name='keywords' content='' />");
			s.append("<meta name='description' content='' /><meta name='viewport' content='width=device-width'>");
			s.append("</head><body>");
			s.append("Hi ").append(user.getLoginname()).append(",<br/>");
			s.append("&nbsp;&nbsp;Your trainer ").append(course.getTrainer().getLoginname()).append(" have assigned you some exercises about ");
			s.append(course.getName()).append(",please finish it as soon as possible.").append("<br/>");
			s.append("Thanks,<br/>Training System Admin");
			s.append("</body></html>");
			mailInfo.setContent(s.toString());
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			sms.sendHtmlMail(mailInfo);
		}
	}

	@Override
	public PageResult<User> listUserPage(Integer page, int pagesize) {
		PageResult<User> pageResult = userDaoImpl.listUserPage(page, pagesize);
		return pageResult;
	}
}
