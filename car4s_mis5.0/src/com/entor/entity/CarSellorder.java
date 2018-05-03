package com.entor.entity;

import java.util.Date;
/**
 * 汽车销售单
 * @author ZHQL
 */
public class CarSellorder {
	//ID
	private long id;
	//整车ID
	private long carId;
	//客户ID
	private long customerId;
	//销售人姓名
	private String salesman;
	//实际价格
	private float sellPrice;
	//数量
	private long count;
	//总价
	private float total;
	//销售日期(年月日时分)
	private Date sellDate;
	//提车日期(年月日)
	private Date outDate;
	//提车状态(1-未提车,2-已提车)
	private int outState;
	//备注
	private String remark;
	//临时字段
	private String brand;
	private String series;
	private String custName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCarId() {
		return carId;
	}
	public void setCarId(long carId) {
		this.carId = carId;
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
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
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
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

