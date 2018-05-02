package com.emindsoft.zsj.law.ctrl;

import org.apache.commons.lang.StringUtils;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.law.model.Notice;
import com.emindsoft.zsj.law.vo.NoticeSelectVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("notice")
public class NoticeCtrl extends AdminBaseController<Notice> {
	
	public NoticeCtrl() {
		this.modelClass = Notice.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Notice notice;
		try {
			notice = getModel();
			notice.set("keyid", Notice.dao.getId());
//			notice.set("releaseTime",dateTimeFormat.format(new Date()));
			notice.set("userId",getCurrentUserId());
			notice.set("regionId",getCurrentUserRegionId());
			notice.save();
			processAttachment(notice.getStr("keyid"));
			success();
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Notice.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	public void edit() {
		Notice notice;
		try {
			notice = getModel();
			notice.set("userId",getCurrentUserId());
//			notice.set("releaseTime",dateTimeFormat.format(new Date()));
			notice.update();
			success(notice.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败！");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {
		String keyid = getPara("keyId");
		Notice notice = Notice.dao.findById(keyid);
		success(notice);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void noticeList() {
		String selectRegionId;
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("613", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("613",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		NoticeSelectVO noticeSelectVO = Tools.getSubVO(NoticeSelectVO.class, getRequest());
		Page<Notice> pageList = Notice.dao.page(getPageNo(), getPageSize(),noticeSelectVO,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getNoticeList() { 
		String regioncode=getPara("regioncode");
		Page<Notice> pageList = Notice.dao.page(getPageNo(), getPageSize(),regioncode);
		success(pageList);

	}

}
