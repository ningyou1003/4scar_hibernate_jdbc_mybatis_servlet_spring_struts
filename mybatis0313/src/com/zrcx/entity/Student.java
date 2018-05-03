package com.zrcx.entity;

import java.util.Date;
/**
 * 学生类
 * @author zhql
 */
public class Student {
	private long id;
	private String name;
	//性别(1-男,2-女)
	private int sex;
	private Date birthday;
	private String snative;
	private Long classId;
	private Date createDate;
	//临时属性
	private String className;
	private Tclass tclass;
	
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
	public String getSnative() {
		return snative;
	}
	public void setSnative(String snative) {
		this.snative = snative;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", snative=" + snative
				+ ", classId=" + classId + ", createDate=" + createDate
				+ ", className=" + className + "]";
	}
	public Tclass getTclass() {
		return tclass;
	}
	public void setTclass(Tclass tclass) {
		this.tclass = tclass;
	}
}






