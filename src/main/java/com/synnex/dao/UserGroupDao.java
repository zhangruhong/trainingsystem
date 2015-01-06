package com.synnex.dao;

import com.synnex.model.PageResult;
import com.synnex.model.Usergroup;

public interface UserGroupDao extends GenericDao<Usergroup, Integer> {

	/**
	 * 查询usergroup带分页
	 * 
	 * @param begin
	 * @param size
	 * @param termid
	 * @return
	 */
	PageResult<Usergroup> listUserGroupPage(int begin, int size, int termid);
}
