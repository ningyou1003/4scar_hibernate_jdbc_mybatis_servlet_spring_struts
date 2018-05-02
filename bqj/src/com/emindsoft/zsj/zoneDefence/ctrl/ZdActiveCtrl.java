package com.emindsoft.zsj.zoneDefence.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.emindsoft.zsj.zoneDefence.model.ZdActive;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("zdactive")
public class ZdActiveCtrl extends AdminBaseController<ZdActive>{
	public ZdActiveCtrl() {
		this.modelClass = ZdActive.class;
	}
	
	public void zdActiveList() {
		RolePower rp = RolePower.dao.getOperPower("201", getCurrentUserId());
		Page<ZdActive> zdsysList = ZdActive.dao.page(getPageNo(), getPageSize());
		PageVO zdsysVO = new PageVO(zdsysList, rp);
		success(zdsysVO);
	}
	
	/**
	 * 添加制度
	 */
	public void add() {
		ZdActive zdActive;
		try {
			zdActive = getModel();
			zdActive.set("KeyId", ZdActive.dao.getId());
			zdActive.save();
			processAttachment(zdActive.getStr("keyid"));
			success();
		} catch (Exception e) {
			log.error("添加活动异常", e);
			error(003);
		}
	}
	
	public void edit() {					
		ZdActive zdActive;
		try {
			zdActive = getModel();
			zdActive.update();
			success(zdActive.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新活动异常", e);
			error(003);
		}
	}

	public void editData(){
		String keyid = getPara("keyid");
		ZdActive zdActive = ZdActive.dao.findById(keyid);
		success(zdActive);
	}
	
	/**
	 * 删除选择的通知
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		ZdActive.dao.deleteByIds(keyids);
		success(001);
	}

}
