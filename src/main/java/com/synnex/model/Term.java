package com.synnex.model;

import java.util.HashSet;
import java.util.Set;

public class Term {
	private int id;
	private String name;
	private String description;
	private Set<Usergroup> usergroups = new HashSet<Usergroup>();
	private Set<Course> courses = new HashSet<Course>();

	public Term() {
		super();
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

	public Set<Usergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(Set<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Term [id=" + id + ", name=" + name + ", description="
				+ description + ", usergroups=" + "省略" + "]";
	}

}
