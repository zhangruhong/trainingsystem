package com.synnex.dao.impl;

import org.springframework.stereotype.Repository;

import com.synnex.dao.UserDao;
import com.synnex.model.User;
import com.synnex.utils.md5Util.Md5Encode;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements
		UserDao {

	// 加密密码后存储 覆盖存储的方法
	@Override
	public void save(User entity) {
		entity.setPassword(Md5Encode.getStringMD5(entity.getPassword()));
		this.getSession().save(entity);
	}
	//TODO 删除user的时候应该级联删除与该user相关的信息
}
