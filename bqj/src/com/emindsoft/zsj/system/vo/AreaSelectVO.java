package com.emindsoft.zsj.system.vo;

/**
 * 区域查询条件选择框中数据的数据结构
 * @author root
 *
 */
public class AreaSelectVO {
	private String region;
	private String regioncode;
	private String parentcode;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegioncode() {
		return regioncode;
	}
	public void setRegioncode(String regionCode) {
		this.regioncode = regionCode;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	
	
	
}
