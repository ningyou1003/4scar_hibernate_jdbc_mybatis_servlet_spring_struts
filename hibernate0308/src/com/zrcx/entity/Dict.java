package com.zrcx.entity;

import java.util.Date;

/**
 * Dict entity. @author MyEclipse Persistence Tools
 */

public class Dict implements java.io.Serializable {

	// Fields

	private Long id;
	private String dictName;
	private String ckey;
	private String cvalue;
	private Long useFlag;
	private Long orderNo;
	private Date createDate;

	// Constructors

	/** default constructor */
	public Dict() {
	}

	/** minimal constructor */
	public Dict(String dictName, String ckey, String cvalue, Long useFlag,
			Long orderNo) {
		this.dictName = dictName;
		this.ckey = ckey;
		this.cvalue = cvalue;
		this.useFlag = useFlag;
		this.orderNo = orderNo;
	}

	/** full constructor */
	public Dict(String dictName, String ckey, String cvalue, Long useFlag,
			Long orderNo, Date createDate) {
		this.dictName = dictName;
		this.ckey = ckey;
		this.cvalue = cvalue;
		this.useFlag = useFlag;
		this.orderNo = orderNo;
		this.createDate = createDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getCkey() {
		return this.ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return this.cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public Long getUseFlag() {
		return this.useFlag;
	}

	public void setUseFlag(Long useFlag) {
		this.useFlag = useFlag;
	}

	public Long getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}