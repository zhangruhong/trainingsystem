package com.synnex.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {

	private static final long serialVersionUID = 744077601120584167L;
	private int id;
	@Email(message = "请输入正确的email地址")
	private String email;

	@NotEmpty(message = "用户名不能为空")
	@Length(max = 20, min = 2, message = "用户名长度应该介于2~20之间")
	private String loginname;

	@NotEmpty(message = "用户名不能为空")
	@Length(max = 33, min = 6, message = "密码长度应该介于6~16之间")
	private String password;

	private String phoneno;
	// 0:TrainingManager（培训管理者）、1:Trainer（培训讲师）、2:NewComer（新员工）

	@Range(max = 3, min = 0, message = "选择的用户角色不在合法范围内")
	private int role;

	@JsonIgnore
	private Set<Usergroup> usergroups = new HashSet<Usergroup>();

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Set<Usergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(Set<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", loginname=" + loginname + ", password=" + password + ", phoneno=" + phoneno + ", role="
				+ role + "]";
	}

}
