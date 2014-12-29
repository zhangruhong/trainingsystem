package com.synnex.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 课程
 * 
 * @author Davisz
 *
 */
@JsonIgnoreProperties(value = { "dictionary1" })
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
	// 练习题状态 0：未上传， 1：已上传
	private Integer practiseStatus = 0;
	private User trainer;
	// 课程所属分类 如：技术类 业务类 spring maven 等
	private Dictionary dictionary;
	private Term term;

	public Course() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

	public Integer getPractiseStatus() {
		return practiseStatus;
	}

	public void setPractiseStatus(Integer practiseStatus) {
		this.practiseStatus = practiseStatus;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
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
