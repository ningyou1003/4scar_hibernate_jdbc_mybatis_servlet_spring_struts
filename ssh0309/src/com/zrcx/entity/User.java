package com.zrcx.entity;

import java.util.Date;
/**
 * 用户类(MVC中的物理模型-M)
 * @author zhql
 */
public class User {
	private long id;
	private String name;
	private String username;
	private String password;
	private Date birthday;
	//状态(1-正常,2-无效)
	private int loginFlag;
	//性别(1-男,2-女)
	private int sex;
	private long roleId;
	private String filePath;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", birthday=" + birthday
				+ ", loginFlag=" + loginFlag + ", sex=" + sex + ", roleId="
				+ roleId + ", filePath=" + filePath + ", createDate="
				+ createDate + "]";
	}
}






