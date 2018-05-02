package com.emindsoft.zsj.zoneDefence.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.emindsoft.zsj.zoneDefence.model.Zdsystem;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("zdsys")
public class ZdsysCtrl extends AdminBaseController<Zdsystem>{
	public ZdsysCtrl() {
		this.modelClass = Zdsystem.class;
	}
	
	public void zdsysList() {
		RolePower rp = RolePower.dao.getOperPower("202", getCurrentUserId());
		Page<Zdsystem> zdsysList = Zdsystem.dao.page(getPageNo(), getPageSize());
		PageVO zdsysVO = new PageVO(zdsysList, rp);
		success(zdsysVO);
	}
	
	/**
	 * 添加制度
	 */
	public void add() {
		Zdsystem zdsys;
		try {
			zdsys = getModel();
			zdsys.set("KeyId", Zdsystem.dao.getId());
			zdsys.set("publisherId", getCurrentUserId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			zdsys.set("publishTime", time);
			zdsys.save();
			success();
		} catch (Exception e) {
			log.error("添加通知异常", e);
			error(003);
		}
	}
	
	public void edit() {					
		Zdsystem zdsys;
		try {
			zdsys = getModel();
			zdsys.update();
			success(zdsys.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新通知异常", e);
			error(003);
		}
	}

	public void editData(){
		String keyid = getPara("keyid");
		Zdsystem zdsys = Zdsystem.dao.findById(keyid);
		success(zdsys);
	}
	
	/**
	 * 删除选择的通知
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Zdsystem.dao.deleteByIds(keyids);
		success(001);
	}

}
