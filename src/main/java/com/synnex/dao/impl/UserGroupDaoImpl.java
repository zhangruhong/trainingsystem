package com.synnex.dao.impl;

import org.springframework.stereotype.Repository;

import com.synnex.dao.UserGroupDao;
import com.synnex.model.PageResult;
import com.synnex.model.Usergroup;

/**
 * 继承通用的方法，如有必要可以覆盖相应的方法来达到特殊处理的功能
 * 
 * @author Davisz
 *
 */
@Repository("userGroupDaoImpl")
public class UserGroupDaoImpl extends GenericDaoImpl<Usergroup, Integer> implements UserGroupDao {

	@Override
	public PageResult<Usergroup> listUserGroupPage(int begin, int size, int termid) {
		String hql = "from Usergroup u where u.term.id=?";
		PageResult<Usergroup> pageResult = super.listPageResult(begin, size, hql, termid);
		return pageResult;
	}
}
