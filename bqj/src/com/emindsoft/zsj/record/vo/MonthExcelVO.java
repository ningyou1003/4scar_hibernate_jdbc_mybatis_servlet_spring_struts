package com.emindsoft.zsj.record.vo;

public class MonthExcelVO {
	
	private String level;
	private String regionName;
	private String checkNumber;
	private String actionNumber;
	private String findNumber;
	private String solveNumber;
	
	public MonthExcelVO(String level, String regionName, String checkNumber,
			String actionNumber, String findNumber, String solveNumber) {
		super();
		this.level = level;
		this.regionName = regionName;
		this.checkNumber = checkNumber;
		this.actionNumber = actionNumber;
		this.findNumber = findNumber;
		this.solveNumber = solveNumber;
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public String getActionNumber() {
		return actionNumber;
	}
	public void setActionNumber(String actionNumber) {
		this.actionNumber = actionNumber;
	}
	public String getFindNumber() {
		return findNumber;
	}
	public void setFindNumber(String findNumber) {
		this.findNumber = findNumber;
	}
	public String getSolveNumber() {
		return solveNumber;
	}
	public void setSolveNumber(String solveNumber) {
		this.solveNumber = solveNumber;
	}
}
