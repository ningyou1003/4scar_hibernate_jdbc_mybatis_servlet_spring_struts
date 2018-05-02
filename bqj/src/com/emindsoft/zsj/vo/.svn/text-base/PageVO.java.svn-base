package com.emindsoft.zsj.vo;

import com.emindsoft.zsj.system.model.RolePower;
import com.jfinal.plugin.activerecord.Page;

/**
 * 返回到页面的数据的数据结构
 * @author root
 *
 */
public class PageVO {

	private RolePower rp;

	private Page page;

	public PageVO(Page page, RolePower rp) {
		this.page = page;
		this.rp = rp;
	}

	public static PageVO getPageVO(Page page, RolePower rp) {
		return new PageVO(page, rp);
	}

	public RolePower getRp() {
		return rp;
	}

	public void setRp(RolePower rp) {
		this.rp = rp;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
