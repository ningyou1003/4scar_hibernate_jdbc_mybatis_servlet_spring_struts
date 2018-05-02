package com.emindsoft.zsj.system.vo;

import com.emindsoft.zsj.system.model.OrgDepartment;

import java.util.ArrayList;
import java.util.List;

public class OrgDepTreeVO {
	
	private String keyid;// 子菜单id
	private String parentid;// 父菜单id
	private String parentname;//父级部门名称
	private String departmentname;// 部门名称
	private String departmenton;// 部门编号
	
	private String orgkeyid;//部门所属单位
	private String orgname;
	private String departmentworkarea;//部门工作职责
	private String departmentleader;//部门领导
	private String departmentphone;//部门电话
	private String type;//部门电话
	private Integer deporder;//部门排序
	private List<OrgDepTreeVO> children;// 子菜单集合
	
	public OrgDepTreeVO() {
		children = new ArrayList<OrgDepTreeVO>();
	}
	
	public static OrgDepTreeVO markTree(OrgDepartment orgdep,List<OrgDepartment> orgDepList){
		OrgDepTreeVO dtvo=new OrgDepTreeVO();//new一个部门树的对象
		dtvo.keyid=orgdep.getStr("keyid");
		dtvo.parentid=orgdep.getStr("parentid");
		dtvo.parentname=orgdep.getStr("parentname");
		dtvo.departmentname=orgdep.getStr("departmentname");
		dtvo.departmenton=orgdep.getStr("departmenton");
		dtvo.orgkeyid =orgdep.getStr("orgkeyid");
		dtvo.orgname=orgdep.getStr("orgname");
		dtvo.departmentworkarea=orgdep.getStr("departmentworkarea");
		dtvo.departmentleader=orgdep.getStr("departmentleader");
		dtvo.departmentphone=orgdep.getStr("departmentphone");
		dtvo.type=orgdep.getStr("type");
		dtvo.deporder = orgdep.getInt("deporder");
		for (OrgDepartment orgDep : orgDepList) {
			if(dtvo.keyid.equals(orgDep.getStr("parentid"))){
				dtvo.children.add(markTree(orgDep, orgDepList));
			}
		}
		return dtvo;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDepartmenton() {
		return departmenton;
	}

	public void setDepartmenton(String departmenton) {
		this.departmenton = departmenton;
	}

	public String getOrgkeyid() {
		return orgkeyid;
	}

	public void setOrgkeyid(String orgkeyid) {
		this.orgkeyid = orgkeyid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getDepartmentworkarea() {
		return departmentworkarea;
	}

	public void setDepartmentworkarea(String departmentworkarea) {
		this.departmentworkarea = departmentworkarea;
	}

	public String getDepartmentleader() {
		return departmentleader;
	}

	public void setDepartmentleader(String departmentleader) {
		this.departmentleader = departmentleader;
	}

	public String getDepartmentphone() {
		return departmentphone;
	}

	public void setDepartmentphone(String departmentphone) {
		this.departmentphone = departmentphone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<OrgDepTreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<OrgDepTreeVO> children) {
		this.children = children;
	}

	public Integer getDeporder() {
		return deporder;
	}

	public void setDeporder(Integer deporder) {
		this.deporder = deporder;
	}

}
