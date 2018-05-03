package com.entor.entity;

import java.util.Date;
/**
 * 汽车进货单
 * @author ZHQL
 *
 */
public class CarInorder {
	//ID
	private long id;
	//整车ID
	private long carId;
	//供应商ID
	private long supplierId;
	//进货价
	private float inPrice;
	//数量
	private long count;
	//总价
	private float total;
	//入库日期(年月日时分)
	private Date inDate;
	//入库状态(1-未入库,2-已入库)
	private int inState;
	//填单日期
	private Date createDate;
	//备注
	private String remark;
	//临时字段
	private String brand;
	private String series;
	private String supName;
	
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
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public float getInPrice() {
		return inPrice;
	}
	public void setInPrice(float inPrice) {
		this.inPrice = inPrice;
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
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public int getInState() {
		return inState;
	}
	public void setInState(int inState) {
		this.inState = inState;
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
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
}
