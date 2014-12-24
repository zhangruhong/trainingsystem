package com.synnex.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 课程
 * 
 * @author Davisz
 *
 */
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	@NotEmpty
	private String name;
	private Date starttime;
	private Date endtime;
	private String location;
	private String goal;
	private String content;
	// 由trainer发布的练习题
	private String practise;
	private User trainer;
	private Dictionary dictionary;
	private Term term;
	private Set<User> users = new HashSet<User>();

	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPractise() {
		return practise;
	}

	public void setPractise(String practise) {
		this.practise = practise;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	@JsonIgnore
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", starttime=" + starttime + ", endtime=" + endtime + ", location=" + location + ", goal="
				+ goal + ", content=" + content + ", practise=" + practise + ", dictionary=" + dictionary + "]";
	}


}
