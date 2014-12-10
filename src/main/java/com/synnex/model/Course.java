package com.synnex.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date starttime;
	private Date endtime;
	private String name;
	private String content;
	private String goal;
	// 由trainer发布的练习题
	private String practise;
	private User trainer;
	private Dictionary dictionary;
	private Term term;
	private Set<User> users = new HashSet<User>();
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

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
}
