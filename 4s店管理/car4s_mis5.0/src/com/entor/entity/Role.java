package com.entor.entity;

import java.util.Date;

public class Role {
	//角色ID
	private int id;
	//角色名称
	private String name;
	//创建日期
	private Date createDate;
	//删除标志(0-未删,1-已删)
	private int delFlag;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
