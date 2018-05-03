package com.entor.entity;

import java.util.Date;

/**
 * 数据字典类
 * @author ZHQL
 */
public class Dict {
	private long id;
	private String dictName;
	private String key;
	private String value;
	private int useFlag;
	private int orderNo;
	private Date createDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Dict [id=" + id + ", dictName=" + dictName + ", key=" + key
				+ ", value=" + value + ", useFlag=" + useFlag + ", orderNo="
				+ orderNo + ", createDate=" + createDate + "]";
	}
}
