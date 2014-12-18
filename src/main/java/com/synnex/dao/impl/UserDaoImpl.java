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
			criteria.add(Restrictions.like("loginname", "%" + condition.getLoginname() + "%"));
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
}
