package com.zrcx.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 实体类(MVC中的物理模型-M)
 * @author zhql
 */
public class Dict implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String dictName;
	private String ckey;
	private String cvalue;
	private int useFlag;
	private Date createDate;
	private int orderNo;
	
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
	public String getCkey() {
		return ckey;
	}
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public String getCvalue() {
		return cvalue;
	}
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public int getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "Dict [id=" + id + ", dictName=" + dictName + ", ckey=" + ckey
				+ ", cvalue=" + cvalue + ", useFlag=" + useFlag
				+ ", createDate=" + createDate + ", orderNo=" + orderNo + "]";
	}
}






