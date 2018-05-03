package com.zrcx.entity;

import java.util.Date;
/**
 * 教师类
 * @author zhql
 */
public class Teacher {
	private long id;
	private String name;
	//性别(1-男,2-女)
	private int sex;
	private Date birthday;
	private Date workDate;
	private String specialty;
	private int level;
	private Date createDate;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", workDate=" + workDate
				+ ", specialty=" + specialty + ", level=" + level
				+ ", createDate=" + createDate + "]";
	}
}






