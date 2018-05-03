package com.entor.entity;

/**
 * 配件库存类
 * @author ZHQL
 */
public class FitStock {
	// ID
	private long id;
	// 配件ID(FK)
	private long fittingsId;
	// 数量
	private long count;
	// 备注
	private String remark;
	//临时字段
	private String brand;
	private String type;
	private String fitName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFittingsId() {
		return fittingsId;
	}
	public void setFittingsId(long fittingsId) {
		this.fittingsId = fittingsId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFitName() {
		return fitName;
	}
	public void setFitName(String fitName) {
		this.fitName = fitName;
	}
}
