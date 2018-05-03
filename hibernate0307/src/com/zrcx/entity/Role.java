package com.zrcx.entity;

import java.util.Date;
import java.util.Set;
/**
 * 班级类
 * @author zhql
 */
public class Role {
	private long id;
	private String name;
	private Date createDate;
	private Set<Menu> menus;
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", createDate="
				+ createDate + "]";
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}






