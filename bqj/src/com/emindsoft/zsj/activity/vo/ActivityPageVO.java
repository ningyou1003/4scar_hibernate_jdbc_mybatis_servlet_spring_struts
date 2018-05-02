package com.emindsoft.zsj.activity.vo;

import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

public class ActivityPageVO extends PageVO {
	private String type;//活动类型

	public ActivityPageVO(Page page, RolePower rp) {
		super(page, rp);
	}

	public ActivityPageVO(Page page, RolePower rp, String type) {
		super(page, rp);
		this.type = type;
	}

}
