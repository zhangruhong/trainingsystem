package com.synnex.service;

import java.util.List;

import com.synnex.model.PasswordName;

/*
 *@author Jeniss 2013-12-7 ����8:24:35
 *@tags
 */
public interface PasswordInfoService {
	List<PasswordName> getAllPasswordInfosOrderByName();
}
