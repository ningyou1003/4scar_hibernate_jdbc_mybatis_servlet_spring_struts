package com.entor.entity;

import java.util.Date;
/**
 * 配件类
 * @author ZHQL
 */
public class Fittings {
	// ID
	private long id;
	// 配件名称
	private String name;
	// 单位
	private int unit;
	// 价格
	private float price;
	// 品牌
	private String brand;
	// 型号
	private String type;
	// 创建日期
	private Date createDate;
	// 备注
	private String remark;
	// 删除标志(0-未删,1-已删)
	private int delFlag;
	
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
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
