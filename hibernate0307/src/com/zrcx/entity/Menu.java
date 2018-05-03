package com.zrcx.entity;

import java.util.Date;
import java.util.Set;
/**
 * 菜单类
 * @author zhql
 */
public class Menu implements Comparable<Menu>{
	private long id;
	private String name;
	private String url;
	private long parentId;
	private int menuLevel;
	private Date createDate;
	private Set<Role> Role;
	//临时属性，标记菜单是否已经分配给某个角色
	private boolean checked;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url
				+ ", parentId=" + parentId + ", menuLevel=" + menuLevel
				+ ", createDate=" + createDate + ", checked=" + checked + "]";
	}
	@Override
	public int compareTo(Menu o) {
		if(this.id > o.id){
			return 1;
		}else if(this.id < o.id){
			return -1;
		}else{
			return 0;
		}
		
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Set<Role> getRole() {
		return Role;
	}
	public void setRole(Set<Role> role) {
		Role = role;
	}
}






