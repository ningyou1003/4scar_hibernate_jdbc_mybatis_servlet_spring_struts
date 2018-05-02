package com.emindsoft.zsj.record.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.record.model.RecordMap;

public class RecortTimeCountVO {
	private String id;
	private String pid;
	private String regionname;
	private String level;
	private Long checkTimes;
	private BigDecimal outerNumber;
	private BigDecimal problemNumbers;
	private BigDecimal isSolvedNumbers;
	private List<RecortTimeCountVO> children;
	
	public RecortTimeCountVO(){
		children = new ArrayList<RecortTimeCountVO>();
	}
	
	public static RecortTimeCountVO markTree(RecordMap rm, List<RecordMap> rmlist){
		RecortTimeCountVO rt = new RecortTimeCountVO();
		rt.id = rm.getStr("id");
		rt.pid = rm.getStr("pid");
		rt.regionname = rm.getStr("regionname");
		rt.checkTimes = rm.getLong("checkTimes");
		rt.outerNumber = rm.getBigDecimal("outerNumber");
		rt.problemNumbers = rm.getBigDecimal("problemNumbers");
		rt.isSolvedNumbers = rm.getBigDecimal("isSolvedNumbers");

		int l = rm.getInt("level");
		if(l==0){
			rt.level = "自治区";
		}
		if(l==1){
			rt.level = "市";
		}
		if(l==2){
			rt.level = "县";
		}
		if(l==3){
			rt.level = "县";
		}
		for (RecordMap rm2 : rmlist) {
			if(rm2 != null){
				if (rt.id.equals(rm2.get("pid"))) {
					rt.children.add(markTree(rm2, rmlist));
				}
			}
		}
		return rt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public Long getCheckTimes() {
		return checkTimes;
	}

	public void setCheckTimes(Long checkTimes) {
		this.checkTimes = checkTimes;
	}

	public BigDecimal getOuterNumber() {
		return outerNumber;
	}

	public void setOuterNumber(BigDecimal outerNumber) {
		this.outerNumber = outerNumber;
	}

	public BigDecimal getProblemNumbers() {
		return problemNumbers;
	}

	public void setProblemNumbers(BigDecimal problemNumbers) {
		this.problemNumbers = problemNumbers;
	}

	public BigDecimal getIsSolvedNumbers() {
		return isSolvedNumbers;
	}

	public void setIsSolvedNumbers(BigDecimal isSolvedNumbers) {
		this.isSolvedNumbers = isSolvedNumbers;
	}

	public List<RecortTimeCountVO> getChildren() {
		return children;
	}

	public void setChildren(List<RecortTimeCountVO> children) {
		this.children = children;
	}
	
}
