package com.entor.entity;

import java.util.Date;
/**
 * 用户类
 * @author ZHQL
 */
public class User {
	private long id;
	private long deptId;
	private String name;
	private int sex;
	private Date birthday;
	private Date entryDate;
	private String username;
	private String password;
	private String newpassword;
	private String password1;
	private int loginFlag;
	private int applyFlag;
	private long roleId;
	private Date createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
	}
	public int getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(int applyFlag) {
		this.applyFlag = applyFlag;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", deptId=" + deptId + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", entryDate="
				+ entryDate + ", username=" + username + ", password="
				+ password + ", loginFlag=" + loginFlag + ", applyFlag="
				+ applyFlag + ", roleId=" + roleId + ", createDate="
				+ createDate + "]";
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
}
