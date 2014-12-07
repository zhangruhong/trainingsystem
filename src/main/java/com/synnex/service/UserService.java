package com.synnex.service;

import java.util.List;

import com.synnex.model.User;


public interface UserService {
/**
 * ----是否应该添加个类或者修改大分类类来对学员进行分组？
 * 包括用户的（单个、批量）增删改查-管理员专属
 * 包括用户个人信息的修改（自己修改）
 * 包括用户自己修改密码
 * 
 */
	public void addUser(User user);
	
	public void addUsers(List<User> users);
	
	public void deleteUser(User user);
	
	public void deleteUsers(List<User> users);
	
	public void updateUser(User user);
	
	public void updateUsers(List<User> users);
	
	public User getUser(int id);
	
	public List<User> getUsers();
}
