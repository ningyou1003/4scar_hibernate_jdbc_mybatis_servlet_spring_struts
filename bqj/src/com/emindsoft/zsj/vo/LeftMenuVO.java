package com.emindsoft.zsj.vo;

import com.emindsoft.zsj.system.model.Power;

import java.util.ArrayList;
import java.util.List;

public class LeftMenuVO {
	private String id;
	private String name;
	private String parentId;
	private String url;
	private String img;
	private int orderID;
	private List<LeftMenuVO> child = new ArrayList<LeftMenuVO>();
	
	public LeftMenuVO() {}

	public LeftMenuVO(Power power) {
		this.id = power.getStr("KeyID");
		this.name = power.getStr("Name");
		this.parentId = power.getStr("ParentId");
		this.url = power.getStr("Url");
		this.img = power.getStr("Img");
		this.orderID = power.getInt("OrderID");
	}

	public static LeftMenuVO copyFormPower(Power power) {
		LeftMenuVO lm = new LeftMenuVO();
		lm.id = power.getStr("KeyID");
		lm.name = power.getStr("Name");
		lm.parentId = power.getStr("ParentId");
		lm.url = power.getStr("Url");
		lm.img = power.getStr("Img");
		lm.orderID = power.getInt("OrderID");
		if (power.getMenuList().size() != 0) {
			for (Power p : power.getMenuList()) {
				lm.getChild().add(LeftMenuVO.copyFormPower(p));
			}
		}
		return lm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public List<LeftMenuVO> getChild() {
		return child;
	}

	public void setChild(List<LeftMenuVO> child) {
		this.child = child;
	}

}
