package com.synnex.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户分组
 * 
 * @author Davisz
 *
 */
public class Usergroup {
	private int id;
	@NotEmpty(message = "组名不能为空")
	@Length(max = 15, min = 1, message = "组名长度不合适（1~15）")
	private String name;
	@NotEmpty(message = "分组描述不能为空")
	@Length(max = 40, min = 1, message = "分组描述/简介长度不合适（1~40）")
	private String description;
	private Term term;
	private Set<User> users = new HashSet<User>();

	public Usergroup() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
