package com.emindsoft.zsj.system.vo;

import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

public class JobPageVO extends PageVO{

	public JobPageVO(Page page, RolePower rp) {
		super(page, rp);
	}
	private String regioncode;
	public String getRegioncode() {
		return regioncode;
	}
	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}
}
