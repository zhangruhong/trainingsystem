package com.synnex.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.synnex.dao.Order;
import com.synnex.dao.UserDao;
import com.synnex.model.User;
import com.synnex.utils.md5Util.Md5Encode;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	// 加密密码后存储 覆盖存储的方法
	@Override
	public void save(User entity) {
		entity.setPassword(Md5Encode.getStringMD5(entity.getPassword()));
		this.getSession().save(entity);
	}

	// TODO 删除user的时候应该级联删除与该user相关的信息

	@Override
	public List<User> listByNameSimilar(User condition, List<Order> orders, int begin, int size) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		if (null != condition) {
			criteria.add(Restrictions.like("loginname", "%" + condition.getLoginname() + "%")).add(Restrictions.eq("role", condition.getRole()));
		}
		if (orders != null) {
			for (Order order : orders) {
				if (order.isAsc()) {
					criteria.addOrder(org.hibernate.criterion.Order.asc(order.getField()));
				} else {
					criteria.addOrder(org.hibernate.criterion.Order.desc(order.getField()));
				}
			}
		}
		if (begin >= 0) {
			criteria.setFirstResult(begin).setMaxResults(size);
		}
		List<User> tlist = criteria.list();
		logger.info(tlist.toString());
		return tlist;
	}

	// 通过courseId查询所有的user
	@Override
	public List<User> queryUserByCourse(Integer courseId) {
		String hql = "select distinct u from User u,com.synnex.model.Usergroup g,com.synnex.model.Term t,com.synnex.model.Course c where c.term.id = t.id and g.term.id = t.id and u in elements(g.users) and c.id = ?";
		List<User> users = super.findByHql(hql, courseId);
		// System.out.println(users);
		return users;
	}

	@Override
	public User findUserbyName(String loginname, int role) {
		String hql = "from User u where u.loginname=? and role=?";
		List<User> users = this.findByHql(hql, loginname, role);
		if (null == users || users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public List<User> findAllUsers() {
		String hql = "from User u where u.role != 0 ";
		List<User> users = this.findByHql(hql);
		if (null == users || users.isEmpty()) {
			return null;
		}
		return users;
	}

}
