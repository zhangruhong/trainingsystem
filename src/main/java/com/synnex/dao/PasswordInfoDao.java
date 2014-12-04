package com.synnex.dao;

import java.util.List;

import com.synnex.model.PasswordInfo;

/**
 * @author Jeniss Dec 3, 2014 10:14:40 PM
 * @tags e.g
 */
public interface PasswordInfoDao {
	List<PasswordInfo> getSearchPasswords(String key);
	//
	// PasswordInfo getPasswordByID(int id);
	//
	// void createPassword(PasswordInfo passwordInfo);
	//
	// void deletePassword(int id);
	//
	// void modifyPassword(PasswordInfo passwordInfo);
	//
	// void deletePasswordByIds(int[] ids);
}
