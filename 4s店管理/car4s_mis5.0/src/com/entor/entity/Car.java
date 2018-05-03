package com.entor.entity;

import java.util.Date;
/**
 * 整车信息实体类
 * @author ZHQL
 */
public class Car {
	//ID
	private int id;
	//品牌
	private String brand;
	//车系
	private String series;
	//类型
	private int type;
	//排量
	private String volume;
	//颜色
	private String color;
	//生产地
	private String proPlace;
	//价格
	private float price;
	//上市日期
	private Date createDate;
	//备注
	private String remark;
	//图片路径
	private String picPath;
	//供应商ID
	//private int supplierId;
	//厂家名称
	private String vender;
	//删除标志(0-未删,1-已删)
	private int delFlag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProPlace() {
		return proPlace;
	}
	public void setProPlace(String proPlace) {
		this.proPlace = proPlace;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public int getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
}

