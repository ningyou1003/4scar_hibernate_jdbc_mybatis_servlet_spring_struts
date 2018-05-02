package com.emindsoft.zsj.record.vo;

import java.util.ArrayList;
import java.util.List;

import com.emindsoft.zsj.record.model.RecordMap;

public class RecordMonthCountVO {
	private String id;
	private String pid;
	private String regionname;
	private String level;
	private String title;// 检查点
	//2016-11-23 新增字段检查人员(checkName) 开始
	private String checkName;
	//2016-11-23新增字段检查人员(checkName) 结束
	private String outernumber;
	private String time;
	private String hasproblem;
	private String content;
	private String issolved;
	//2017-2-14 新增字段 解决情况(settle)开始
	private String settle;
	//2017-2-14 新增字段 解决情况(settle)结束
	private List<RecordMonthCountVO> children;

	public RecordMonthCountVO() {
		children = new ArrayList<RecordMonthCountVO>();
	}

	public static RecordMonthCountVO markTree(RecordMap rm,
			List<RecordMap> rmList) {
		RecordMonthCountVO rmct = new RecordMonthCountVO();
		
		rmct.id = rm.getStr("id");
		rmct.pid = rm.getStr("pid");
		/*
		 * int l = rm.getInt("level"); if(l==0){ rmct.level = "自治区"; } if(l==1){
		 * rmct.level = "市"; } if(l==2){ rmct.level = "县"; } if(l==3){
		 * rmct.level = "县"; }
		 */
		String l = rm.getStr("level");
		if ("0".equals(l)) {
			rmct.level = "自治区";
		}
		if ("1".equals(l)) {
			rmct.level = "市";
		}
		if ("2".equals(l)) {
			rmct.level = "县";
		}
		if ("3".equals(l)) {
			rmct.level = "县";
		}
		rmct.regionname = rm.getStr("regionname");
		rmct.title = rm.getStr("title");
		
		//2016-11-23 新增字段检查人员(checkName) 开始
		rmct.checkName = rm.getStr("checkName");
		//2016-11-23 新增字段检查人员(checkName) 结束
		
		rmct.outernumber = rm.getStr("outernumber");
		rmct.time = rm.getStr("time");
		/*
		 * Integer problem = rm.getInt("hasProblem"); if(problem!=null){
		 * if(problem==0){ rmct.hasproblem = "否"; } if(problem==1){
		 * rmct.hasproblem = "是"; } }
		 */
		String problem = rm.getStr("hasProblem");
		if (problem != null) {
			if ("0".equals(problem)) {
				rmct.hasproblem = "否";
			}
			if ("1".equals(problem)) {
				rmct.hasproblem = "是";
			}
		}
		rmct.content = rm.getStr("content");
		/*
		 * Integer solved = rm.getInt("isSolved"); if(solved!=null){
		 * if(solved==0){ rmct.issolved = "否"; } if(solved==1){ rmct.issolved =
		 * "是"; } }
		 */
		String solved = rm.getStr("isSolved");
		if (solved != null) {
			if ("0".equals(solved)) {
				rmct.issolved = "否";
			}
			if ("1".equals(solved)) {
				rmct.issolved = "是";
			}
		}
		//2017-2-14 新增字段 解决情况(settle)开始
		rmct.settle = rm.getStr("settle");
		//2017-2-14 新增字段 解决情况(settle)结束
		for (RecordMap rm2 : rmList) {
			if (rm2 != null) {
				if (rmct.id.equals(rm2.get("pid"))) {
					rmct.children.add(markTree(rm2, rmList));
				}
			}
		}
		return rmct;
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

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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
	public String getOuternumber() {
		return outernumber;
	}

	public void setOuternumber(String outernumber) {
		this.outernumber = outernumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHasproblem() {
		return hasproblem;
	}

	public void setHasproblem(String hasproblem) {
		this.hasproblem = hasproblem;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIssolved() {
		return issolved;
	}

	public void setIssolved(String issolved) {
		this.issolved = issolved;
	}

	public List<RecordMonthCountVO> getChildren() {
		return children;
	}
	public void setChildren(List<RecordMonthCountVO> children) {
		this.children = children;
	}
	//2017-2-14 新增字段 解决情况(settle)开始
	public String getSettle() {
		return settle;
	}

	public void setSettle(String settle) {
		this.settle = settle;
	}
	//2017-2-14 新增字段 解决情况(settle)结束
}
