package com.synnex.model;

import java.util.List;

/*
 *@author Jeniss 2013-12-11 ����1:41:29
 *@tags
 */
public class PasswordName {
	private String name;
	private List<PasswordInfo> passwordInfos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PasswordInfo> getPasswordInfos() {
		return passwordInfos;
	}

	public void setPasswordInfos(List<PasswordInfo> passwordInfos) {
		this.passwordInfos = passwordInfos;
	}
}
