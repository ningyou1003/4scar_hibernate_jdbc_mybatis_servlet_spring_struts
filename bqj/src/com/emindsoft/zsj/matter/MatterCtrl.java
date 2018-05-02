package com.emindsoft.zsj.matter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("matter")
public class MatterCtrl extends AdminBaseController<Matter> {

	public MatterCtrl () {
		this.modelClass = Matter.class;
	}

	public void matterList(){
		String status = getAttr("status");
		String userId = getCurrentUserId();
		Page<Matter> matterList = Matter.dao.page(getPageNo(), getPageSize(), userId, status);
		success(matterList);
	}
	
	public void add() {
		Matter matter;
		try {
			matter = getModel();
			matter.set("keyid", Matter.dao.getId());
			matter.set("createUserId", getCurrentUserId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			matter.set("publishTime", time);
			matter.save();
			processAttachment(matter.getStr("keyid"));
			success(matter.get("keyid"));
		} catch (Exception e) {
			log.error("添加事项异常", e);
			error(003);
		}
	}
	
	@PowerBind(code = "812_EditPower", funcName = "编辑事项管理")
	public void edit() {					
		Matter matter;
		try {
			matter = getModel();
			matter.update();
			success(matter.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新事项异常", e);
			error(002);
		}
	}
	
	public void editData(){
		String MatterId = getPara("keyid");
		Matter matter = Matter.dao.findById(MatterId);
		success(matter);
	}
	/**
	 * 删除选择的专项活动
	 */
	@PowerBind(code = "812_DelPower", funcName = "删除事项管理")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Matter.dao.deleteByIds(keyids);
		success(001);
	}
	
}
