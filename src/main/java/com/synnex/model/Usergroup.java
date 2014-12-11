package com.synnex.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户分组
 * @author Davisz
 *
 */
public class Usergroup {
	private int id;
	private String name;
	private String description;
	private Term term;
	private Set<User> users = new HashSet<User>();

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

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
