package com.emindsoft.zsj.law.ctrl;

import org.apache.commons.lang.StringUtils;
import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.law.model.Stipulate;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("stipulate")
public class StipulateCtrl extends AdminBaseController<Stipulate> {
	
	public StipulateCtrl() {
		this.modelClass = Stipulate.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Stipulate stipulate;
		try {
			stipulate = getModel();
			stipulate.set("keyid", Stipulate.dao.getId());
//			stipulate.set("releaseTime",dateTimeFormat.format(new Date()));
			stipulate.set("userId",getCurrentUserId());
			stipulate.set("regionId",getCurrentUserRegionId());
			stipulate.save();
			processAttachment(stipulate.getStr("keyid"));
			success();
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");;
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Stipulate.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	public void edit() {
		Stipulate stipulate;
		try {
			stipulate = getModel();
			stipulate.set("userId",getCurrentUserId());
//			stipulate.set("releaseTime",dateTimeFormat.format(new Date()));
			stipulate.update();
			success(stipulate.getStr("keyId"));
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
		Stipulate sl = Stipulate.dao.findById(keyid);
		success(sl);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void stipulateList() {
		String selectRegionId;
		RolePower rp;
		String SelecTitle = getPara("SelecTitle");
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("613", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("613",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		Page<Stipulate> slList = Stipulate.dao.page(getPageNo(), getPageSize(),SelecTitle,selectRegionId);
		success(new PageVO(slList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getStipulateList() { 
		String regioncode=getPara("regioncode");
		Page<Stipulate> slList = Stipulate.dao.page(getPageNo(), getPageSize(),regioncode);
		success(slList);
	}

}
