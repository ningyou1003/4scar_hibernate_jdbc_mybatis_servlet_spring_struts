package com.zrcx.entity;

import java.util.Date;

public class User {
	//记录ID
	private Long id;
	//姓名
	private String name;
	//性别
	private Integer sex;
	//出生日期
	private Date birthday;
	//用户名
	private String username;
	//密码
	private String password;
	//登录状态（1-正常，2-无效）
	private Integer loginFlag;
	//文件路径
	private String filePath;
	//创建日期
	private Date createDate;
	//角色id
	private Integer roleId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	
	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", birthday=" + birthday + ", username=" + username
				+ ", password=" + password + ", loginFlag=" + loginFlag
				+ ", filePath=" + filePath + ", createDate=" + createDate
				+ ", roleId=" + roleId + "]";
	}
	
	
}
