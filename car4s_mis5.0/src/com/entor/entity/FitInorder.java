package com.entor.entity;

import java.util.Date;
/**
 * 配件进货单类
 * @author ZHQL
 */
public class FitInorder {
	//ID
	private int id;
	//配件ID
	private int fittingsId;
	//供应商ID
	private int supplierId;
	//进货价
	private float inPrice;
	//数量
	private int count;
	//总价
	private float total;
	//填单日期(年月日时分)
	private Date createDate;
	//入库日期(年月日时分)
	private Date inDate;
	//入库状态(1-未入库,2-已入库)
	private int inState;
	//备注
	private String remark;
	
	//临时字段
	private String brand;
	private String type;
	private String fitName;
	private String supName;
	
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
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public float getInPrice() {
		return inPrice;
	}
	public void setInPrice(float inPrice) {
		this.inPrice = inPrice;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getFitName() {
		return fitName;
	}
	public void setFitName(String fitName) {
		this.fitName = fitName;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

