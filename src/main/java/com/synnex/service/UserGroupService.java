package com.synnex.service;

import com.synnex.exception.UserException;
import com.synnex.model.PageResult;
import com.synnex.model.Usergroup;

public interface UserGroupService {
	/**
	 * 对分组进行增删改查
	 */
	/**
	 * 在一个term下新建一个group
	 * 
	 * @param ug
	 * @param termid
	 */
	public void addGroup(Usergroup ug, int termid);

	/**
	 * 删除一个Group 注意 不要删除Group里的user哦
	 * 
	 * @param groupid
	 */
	public void deleteGroup(int groupid);

	/**
	 * 根据groupid获取该group对象
	 * 
	 * @param groupid
	 */
	public Usergroup getGroup(int groupid);

	/**
	 * 更新一个组的信息
	 * 
	 * @param ug
	 */
	public void updateGroup(Usergroup ug);


	/**
	 * 将User与分组绑定
	 * 
	 * @param loginname
	 * @param usergroupid
	 * @throws UserException
	 */
	public void addUserToGroup(String loginname, int usergroupid) throws UserException;

	/**
	 * 移除User与分组的关联关系
	 * 
	 * @param loginname
	 * @param usergroupid
	 * @throws UserException
	 */
	public void deleteUserFromGroup(int userid, int usergroupid) throws UserException;

	/**
	 * 查询userGroup带分页
	 * 
	 * @param begin
	 * @param size
	 * @return
	 */
	public PageResult<Usergroup> listUserGroupPage(int begin, int size, int termid);
}
