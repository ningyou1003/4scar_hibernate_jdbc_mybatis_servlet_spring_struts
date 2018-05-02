package com.emindsoft.zsj.law.ctrl;

import org.apache.commons.lang.StringUtils;
import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.law.model.Regulations;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("regulations")
public class RegulationsCtrl extends AdminBaseController<Regulations> {
	
	public RegulationsCtrl() {
		this.modelClass = Regulations.class;
	}
	
	/**
	 * 分页列表
	 *
	 */
	public void regulationsList() {
		String selectRegionId;
		RolePower rp;
		String SelecTitle = getPara("SelecTitle");
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("612", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("612",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		Page<Regulations> regulationsList = Regulations.dao.page(getPageNo(), getPageSize(),SelecTitle,selectRegionId);
		success(new PageVO(regulationsList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getRegulationsList() { 
		String regioncode=getPara("regioncode");
		Page<Regulations> regulationsList = Regulations.dao.page(getPageNo(), getPageSize(),regioncode);
		success(regulationsList);
	}
	
	
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Regulations regulations;
		try {
			regulations = getModel();
			regulations.set("keyid", Regulations.dao.getId());
//			regulations.set("releaseTime",dateTimeFormat.format(new Date()));
			regulations.set("userId",getCurrentUserId());
			regulations.set("regionId",getCurrentUserRegionId());
			regulations.save();
			processAttachment(regulations.getStr("keyid"));
			success();
		} catch(Exception e) {
			log.error("添加异常！",e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Regulations.dao.deleteByIds(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	public void edit() {
		Regulations regulations;
		try {
			regulations = getModel();
			regulations.set("userId",getCurrentUserId());
//			regulations.set("releaseTime",dateTimeFormat.format(new Date()));
			regulations.update();
			success(regulations.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！",e);
			error(002, "保存失败！");
		}
	}
	
	/**
	 * 获取更新数据
	 * 
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {
		String regulationsId = getPara("keyId");
		Regulations rlt = Regulations.dao.findById(regulationsId);
		success(rlt);
	}

}
