package com.emindsoft.zsj.record.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.record.model.RecordMap;

public class RecordTreeVO {
	private String regionId;// 子菜单id
	private String pRegionId;// 父菜单id
	private String regionName;// 区域名称
	// private Integer level;//子菜单级别
	private String level;
	private String recordId;// 检查记录id
	private Integer outerNumber;// 出动人次
	private String address;// 地点
	private String title;// 检查点
	//2016-11-23 新增字段检查人员(checkName) 开始
	private String checkName;
		//2016-11-23新增字段检查人员(checkName) 结束
	// private Integer hasProblem;//检查是否发现问题
	private String hasProblem;
	private String content;// 问题描述
	// private Integer isSolved;//问题是否解决
	private String isSolved;
	
	//2017-02-14 增加解决情况字段 开始
	private String settle;
	//2017-02-14 增加解决情况字段 结束
	
	//2016-11-1 excel导出 开始
	private String time;// 時間
	private String checkTimes;
	//2016-11-1 excel导出 结束
	private List<RecordTreeVO> children;// 子菜单集合

	public RecordTreeVO() {
		children = new ArrayList<RecordTreeVO>();
	}

	public static RecordTreeVO markTree(RecordMap rm, List<RecordMap> rmlist) {
		RecordTreeVO rt = new RecordTreeVO();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		rt.regionId = rm.getStr("regionId");
		rt.pRegionId = rm.getStr("pRegionId");
		// rt.level = rm.getInt("level");
		int l = rm.getInt("level");
		if (l == 0) {
			rt.level = "自治区";
		}
		if (l == 1) {
			rt.level = "市";
		}
		if (l == 2) {
			rt.level = "县";
		}
		if (l == 3) {
			rt.level = "县";
		}
		//2016-11-1 excel导出 开始
			if(rm.getTimestamp("time")!=null){
				rt.time = sd.format(rm.getTimestamp("time"));
			}else{
				rt.time = rm.getStr("time");
			}
			rt.checkTimes = rm.getStr("keyId");
			//2016-11-1 excel导出 结束
			
		rt.regionName = rm.getStr("regionName");
		rt.recordId = rm.getStr("recordId");
		rt.outerNumber = rm.getInt("outerNumber");
		rt.address = rm.getStr("address");
		rt.title = rm.getStr("title");
		//2016-11-23 新增字段检查人员(checkName) 开始
		rt.checkName = rm.getStr("checkName");
		//2016-11-23 新增字段检查人员(checkName) 结束
		// rt.hasProblem = rm.getInt("hasProblem");
		Integer problem = rm.getInt("hasProblem");
		if (problem != null) {
			if (problem == 0) {
				rt.hasProblem = "否";
			}
			if (problem == 1) {
				rt.hasProblem = "是";
			}
		}
		rt.content = rm.getStr("content");
		Integer solved = rm.getInt("isSolved");
		if (solved != null) {
			if (solved == 0) {
				rt.isSolved = "否";
			}
			if (solved == 1) {
				rt.isSolved = "是";
			}
		}
		//2017-02-14 增加解决情况字段 开始
		rt.settle = rm.getStr("settle");
		//2017-02-14 增加解决情况字段 结束
		for (RecordMap rm2 : rmlist) {
			if(rm2!=null){
			if (rt.regionId.equals(rm2.get("parentid"))) {
				rt.children.add(markTree(rm2, rmlist));
			}
			}
		}
		return rt;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getpRegionId() {
		return pRegionId;
	}

	public void setpRegionId(String pRegionId) {
		this.pRegionId = pRegionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getOuterNumber() {
		return outerNumber;
	}

	public void setOuterNumber(Integer outerNumber) {
		this.outerNumber = outerNumber;
	}

	public void setOuterNumber(int outerNumber) {
		this.outerNumber = outerNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	//2016-11-23 新增字段检查人员(checkName) 开始
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	//2016-11-23新增字段检查人员(checkName) 结束
	public String getHasProblem() {
		return hasProblem;
	}

	public void setHasProblem(String hasProblem) {
		this.hasProblem = hasProblem;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsSolved() {
		return isSolved;
	}

	public void setIsSolved(String isSolved) {
		this.isSolved = isSolved;
	}

	public String getSettle() {
		return settle;
	}

	public void setSettle(String settle) {
		this.settle = settle;
	}
	
	public List<RecordTreeVO> getChildren() {
		return children;
	}
	//2016-11-1 excel导出 开始
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getCheckTimes() {
		return checkTimes;
	}

	public void setCheckTimes(String checkTimes) {
		this.checkTimes = checkTimes;
	}

	//2016-11-1 excel导出 结束
	public void setChildren(List<RecordTreeVO> children) {
		this.children = children;
	}


}
