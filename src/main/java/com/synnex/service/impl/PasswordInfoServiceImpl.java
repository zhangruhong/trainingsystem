package com.synnex.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.synnex.dao.PasswordInfoDao;
import com.synnex.model.PasswordInfo;
import com.synnex.model.PasswordName;
import com.synnex.service.PasswordInfoService;

/*
 *@author Jeniss 2013-12-7 ����8:26:04
 *@tags
 */
@Service(value = "passwordInfoService")
public class PasswordInfoServiceImpl implements PasswordInfoService {
	@Resource(name = "passwordInfoDao")
	PasswordInfoDao passwordInfoDao;

	@Override
	public List<PasswordName> getAllPasswordInfosOrderByName() {
		List<PasswordInfo> passwordInfos = passwordInfoDao
				.getSearchPasswords(null);
		return passwordInfoOrderByName(passwordInfos);
	}

	private List<PasswordName> passwordInfoOrderByName(
			List<PasswordInfo> passwordInfos) {
		List<PasswordName> passwordInfosOrderByName = new ArrayList<PasswordName>();
		if (passwordInfos.size() > 0) {
			String old_name = passwordInfos.get(0).getName();
			List<PasswordInfo> passwordInfoList = new ArrayList<PasswordInfo>();
			for (int i = 0; i < passwordInfos.size(); i++) {
				PasswordInfo passwordInfo = passwordInfos.get(i);
				if (!old_name.equals(passwordInfo.getName())) {
					PasswordName passwordName = new PasswordName();
					passwordName.setName(old_name);
					passwordName.setPasswordInfos(passwordInfoList);
					passwordInfosOrderByName.add(passwordName);
					old_name = passwordInfo.getName();
					passwordInfoList = new ArrayList<PasswordInfo>();
					passwordInfoList.add(passwordInfo);
				} else {
					passwordInfoList.add(passwordInfo);
				}
			}
			if (passwordInfoList.size() > 0) {
				PasswordName passwordName = new PasswordName();
				passwordName.setName(old_name);
				passwordName.setPasswordInfos(passwordInfoList);
				passwordInfosOrderByName.add(passwordName);
			}
		}
		return passwordInfosOrderByName;
	}
}
