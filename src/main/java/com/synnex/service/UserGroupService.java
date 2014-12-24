package com.synnex.service;

import java.util.List;

import com.synnex.dao.Order;
import com.synnex.exception.UserException;
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
	 * 获取该期下的所有UserGroup
	 * 
	 * @param termid
	 * @return
	 */
	public List<Usergroup> getAllGroups(int termid, List<Order> orders, int begin, int size);

	/**
	 * 更新一个组的信息
	 * 
	 * @param ug
	 */
	public void updateGroup(Usergroup ug);

	/**
	 * 获取记录数
	 * 
	 * @return
	 */
	public int getCount();

	public void addUserToGroup(String loginname, int usergroupid) throws UserException;
}
