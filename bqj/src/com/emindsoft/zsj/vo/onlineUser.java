package com.emindsoft.zsj.vo;

import java.io.Serializable;

public class onlineUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4622040150737850675L;
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
	public onlineUser(String uid, String uip, String address) {
		super();
		this.uid = uid;
		this.uip = uip;
		this.address = address;
	}
	public onlineUser() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		onlineUser other = (onlineUser) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
}
