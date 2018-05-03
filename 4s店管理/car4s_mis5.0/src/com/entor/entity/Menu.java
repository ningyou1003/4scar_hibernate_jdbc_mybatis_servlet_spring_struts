package com.entor.entity;

import java.io.Serializable;
import java.util.Date;
import net.sf.json.JSONString;

/**
 * 菜单类
 * @author ZHQL
 */
public class Menu implements JSONString,Comparable<Menu>,Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String url;
	private long parentId;
	private int menuLevel;
	private int useFlag;
	private Date createDate;
	//该字段表明菜单是已分配给角色
	private boolean checked;

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

	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public int getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toJSONString() {
		StringBuilder s =  new StringBuilder();
		s.append("{id:").append(id);
		s.append(",pId:").append(parentId);
		s.append(",name:\"").append(name);
		s.append("\",checked:").append(checked);
		s.append(",open:").append(menuLevel==1?"true":"false");
		s.append("}");		
		return s.toString();
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
}











