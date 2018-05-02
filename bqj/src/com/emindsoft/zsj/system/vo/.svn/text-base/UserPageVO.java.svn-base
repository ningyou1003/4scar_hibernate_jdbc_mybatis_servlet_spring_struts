package com.emindsoft.zsj.system.vo;

import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * 返回到用户页面的数据的数据结构
 * @author root
 *
 */
public class UserPageVO extends PageVO {

	public UserPageVO(Page page, RolePower rp) {
		super(page, rp);
	}
	
	private List<Role> roleList;
	//CC 2015.4.1
	private List<String> jobList;
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<String> getJobList() {
		return jobList;
	}
	public void setJobList(List<String> jobList) {
		this.jobList = jobList;
	}
	
}
