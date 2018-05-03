package com.entor.entity;

import java.util.Date;

public class Dept{
	// 部门ID(PK)
	private int id;
	// 部门名称
	private String name;
	// 负责人
	private String charger;
	// 联系电话
	private String contactTel;
	// 创建日期
	private Date createDate;
	// 删除标志(0-未删,1-已删)
	private int delFlag;

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

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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
	
}
