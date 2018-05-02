package com.emindsoft.zsj.manage;

import java.util.List;

import com.emindsoft.zsj.system.model.AreaMember;

public class MembersVO {
	List<AreaMember> leader;
	List<AreaMember> office;
	List<AreaMember> members;
	
	public MembersVO(List<AreaMember> leader2, List<AreaMember> office2, List<AreaMember> members2) {
		super();
		this.leader = leader2;
		this.office = office2;
		this.members = members2;
	}

	public List<AreaMember> getLeader() {
		return leader;
	}

	public void setLeader(List<AreaMember> leader) {
		this.leader = leader;
	}

	public List<AreaMember> getOffice() {
		return office;
	}

	public void setOffice(List<AreaMember> office) {
		this.office = office;
	}

	public List<AreaMember> getMembers() {
		return members;
	}

	public void setMembers(List<AreaMember> members) {
		this.members = members;
	}
	
}
