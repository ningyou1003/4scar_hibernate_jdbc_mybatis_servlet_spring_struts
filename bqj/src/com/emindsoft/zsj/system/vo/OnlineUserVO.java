package com.emindsoft.zsj.system.vo;

public class OnlineUserVO {
	private String uid;//用户id
	private String uip;//用户ip
	private String address;//用户地址
	public static long number=0;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUip() {
		return uip;
	}
	public void setUip(String uip) {
		this.uip = uip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public OnlineUserVO(String uid, String uip, String address) {
		super();
		this.uid = uid;
		this.uip = uip;
		this.address = address;
	}
	public OnlineUserVO() {
		super();
	}
	
	
}
