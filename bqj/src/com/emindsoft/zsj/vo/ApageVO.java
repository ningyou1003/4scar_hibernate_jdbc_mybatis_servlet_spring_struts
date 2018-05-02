package com.emindsoft.zsj.vo;

import com.emindsoft.zsj.system.model.RolePower;
import com.jfinal.plugin.activerecord.Page;

public class ApageVO {
	private Page page;
	private RolePower rp;
	
	public ApageVO(Page page,RolePower rp){
		this.page = page;
		this.rp = rp;
	}
	
	public static ApageVO getApageVO(Page page,RolePower rp){
		return new ApageVO(page,rp);
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public RolePower getRp() {
		return rp;
	}

	public void setRp(RolePower rp) {
		this.rp = rp;
	}
	
	
}
