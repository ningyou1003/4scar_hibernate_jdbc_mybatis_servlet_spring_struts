package com.emindsoft.zsj.law.ctrl;

import javax.validation.constraints.Null;

import org.apache.commons.lang.StringUtils;
import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.law.model.Law;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("law")
public class LawCtrl extends AdminBaseController<Law> {
	
	public LawCtrl() {
		this.modelClass = Law.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Law law;
		try {
			law = getModel();
			law.set("keyid", Law.dao.getId());
//			law.set("releaseTime",dateTimeFormat.format(new Date()));
			law.set("userId",getCurrentUserId());
			law.set("regionId",getCurrentUserRegionId());
			law.save();
			processAttachment(law.getStr("keyid"));
			success();
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Law.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	public void edit() {
		Law law;
		try {
			law = getModel();
			law.set("userId",getCurrentUserId());
//			law.set("releasetime",dateTimeFormat.format(new Date()));
			law.update();
			success(law.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {
		String keyid = getPara("keyId");
		Law law = Law.dao.findById(keyid);
		success(law);
	}
	
	/**
	 * APP详情数据接口
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getAppDetailData() {
		String keyid = getPara("keyid");
		Law law = Law.dao.findById(keyid);
		success(law);
	}
	
	/**
	 * 分页列表
	 * 按登入用户区域获取
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void List() {
		String selectRegionId;
		RolePower rp = null;
		String SelecTitle = getPara("SelecTitle") == null ? "" : getPara("SelecTitle");
		String userid = getCurrentUserId();
		//是否是登入用户，否则返回区级数据
		if(userid != null) {
			String userRegionId = getCurrentUserRegionId();
			String chooseRegionId =getChooseRegionId();
			if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) ||userRegionId.equals(chooseRegionId)) {
				rp = RolePower.dao.getOperPower("611", getCurrentUserId());
				selectRegionId = userRegionId;
			} else {
				rp = RolePower.dao.getLookPower("611",getCurrentUserId());
				selectRegionId = chooseRegionId;
			}
		} else{
			selectRegionId = "450000000000";
		}
		Page<Law> pageList = Law.dao.page(getPageNo(), getPageSize(),SelecTitle,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 官网分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getLawList() {
		String regioncode=getPara("regioncode");
		Page<Law> pageList = Law.dao.page(getPageNo(), getPageSize(),regioncode);
		success(pageList);
	}
	

}
