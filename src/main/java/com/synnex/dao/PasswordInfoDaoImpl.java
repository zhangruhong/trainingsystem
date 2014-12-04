package com.synnex.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.synnex.model.PasswordInfo;

/*
 *@author Jeniss 2013-12-7 ����8:20:51
 *@tags
 */
@Component(value = "passwordInfoDao")
public class PasswordInfoDaoImpl extends GenericDaoImpl<PasswordInfo, Integer>
		implements PasswordInfoDao {

	@Override
	public List<PasswordInfo> getSearchPasswords(String key) {
		StringBuilder sql = new StringBuilder(
				"select passwordInfo from PasswordInfo passwordInfo");
		sql = sql.append(" order by passwordInfo.name");

		return getSession().createQuery(sql.toString()).list();
	}
}
