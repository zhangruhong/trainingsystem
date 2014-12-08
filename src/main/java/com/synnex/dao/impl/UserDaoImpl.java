package com.synnex.dao.impl;

import java.lang.reflect.ParameterizedType;

import org.springframework.stereotype.Repository;

import com.synnex.dao.UserDao;
import com.synnex.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements
		UserDao {
}
