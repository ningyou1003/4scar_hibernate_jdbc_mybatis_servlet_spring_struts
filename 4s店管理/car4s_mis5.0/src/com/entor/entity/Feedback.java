package com.entor.entity;

import java.util.Date;
/**
 * 客户反馈信息
 * @author ZHQL
 */
public class Feedback {
	//ID
	private long id;
	//标题
	private String title;
	//反馈信息
	private String info;
	//客户ID
	private long customerId;
	//反馈日期((年月日时分))
	private Date createDate;
	//客户姓名
	private String name;
	//联系电话
	private String contactTel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
}

