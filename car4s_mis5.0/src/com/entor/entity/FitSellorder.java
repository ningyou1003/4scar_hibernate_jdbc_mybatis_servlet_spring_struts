package com.entor.entity;

import java.util.Date;
/**
 * 配件销售单类
 * @author ZHQL
 */
public class FitSellorder {
	//ID
	private int id;
	//配件ID
	private int fittingsId;
	//客户ID
	private long customerId;
	//销售人姓名
	private String salesman;
	//实际单价
	private float sellPrice;
	//数量
	private int count;
	//总价
	private float total;
	//销售日期(年月日时分)
	private Date sellDate;
	//提货日期(年月日)
	private Date outDate;
	//提货状态(1-未提货,2-已提货)
	private int outState;
	//备注
	private String remark;
	
	//临时字段
	private String brand;
	private String type;
	private String fitName;
	private String custName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFittingsId() {
		return fittingsId;
	}
	public void setFittingsId(int fittingsId) {
		this.fittingsId = fittingsId;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public int getOutState() {
		return outState;
	}
	public void setOutState(int outState) {
		this.outState = outState;
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
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
}

