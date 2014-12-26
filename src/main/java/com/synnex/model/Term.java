package com.synnex.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Term {
	private int id;
	@NotEmpty(message = "培训计划名称不能为空")
	@Length(max = 30, min = 1, message = "命名长度不合适（1~30）")
	private String name;
	@NotEmpty(message = "简介/描述不能为空")
	@Length(min = 1, max = 100, message = "简介/描述字符长度不对（不能超过100）")
	private String description;
	@JsonIgnore
	private Set<Usergroup> usergroups = new HashSet<Usergroup>();
	@JsonIgnore
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
