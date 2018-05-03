package com.zrcx.entity;

import java.util.Date;
import java.util.List;
/**
 * 班级类
 * @author zhql
 */
public class Tclass {
	private long id;
	private String name;
	private String charger;
	private Date createDate;
	private List<Student> students;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Tclass [id=" + id + ", name=" + name + ", charger=" + charger
				+ ", createDate=" + createDate + "]";
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}






