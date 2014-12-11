package com.synnex.model;

import java.io.Serializable;
/**
 * 课程所属分类
 * @author Davisz
 *
 */
public class Dictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String value;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
