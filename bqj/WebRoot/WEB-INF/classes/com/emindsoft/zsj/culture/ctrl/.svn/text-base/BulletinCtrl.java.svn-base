package com.emindsoft.zsj.culture.ctrl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Bulletin;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.routebind.ControllerKey;

@ControllerKey("bulletin")
public class BulletinCtrl extends AdminBaseController<Bulletin> {
	
	public BulletinCtrl() {
		this.modelClass = Bulletin.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	@PowerBind(code = "1611_AddPower", funcName = "简报添加")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功能名
	public void add() {
		Bulletin bulletin;
		try {
			bulletin = getModel();
			bulletin.set("keyid", Bulletin.dao.getId());
//			bulletin.set("releaseTime",dateTimeFormat.format(new Date()));
			bulletin.set("UserId",getCurrentUserId());
			bulletin.set("status", 4);
			bulletin.set("title", bulletin.get("year")+"年第"+bulletin.getStr("number")+"期");
			//修改为保存时的区域是当前选择的区域
			String RegionId=getCurrentUserRegionId();
			try {
				
				
				if (!"undefined".equals(getChooseRegionId()) && getChooseRegionId().length()>10) {
					RegionId=getChooseRegionId();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//异常改变区域id
				RegionId=getCurrentUserRegionId();
			}
			bulletin.set("regionId",RegionId);
			bulletin.save();
			processAttachment(bulletin.getStr("keyid"));
			success(bulletin.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1611_DelPower", funcName = "简报删除")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Bulletin.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1611_EditPower", funcName = "简报更新")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功能名称
	public void edit() {
		Bulletin bulletin;
		try {
			bulletin = getModel();
			bulletin.set("UserId",getCurrentUserId());
			bulletin.set("title", bulletin.get("year")+"年第"+bulletin.getStr("number")+"期");
			bulletin.update();
			success(bulletin.getStr("keyId"));
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
		Bulletin bulletin = Bulletin.dao.findById(keyid);
		success(bulletin);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	@PowerBind(code = "1611_LookPower", funcName = "简报列表")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功能名称
	public void List() {
		String selectRegionId;
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		//获得当前选择区域的所有上级区域
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
				
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId) 
				|| (parentregion.contains(userRegionId) && 5==orderid)) {
			rp = RolePower.dao.getOperPower("1611", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1611",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		
		if (!"undefined".equals(chooseRegionId)) {
			selectRegionId=chooseRegionId;
		}
		
		cultureSelectVO sv = Tools.getSubVO(cultureSelectVO.class, getRequest());
		Page<Bulletin> pageList = Bulletin.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),sv,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getBulletinList() {
		Page<Bulletin> pageList = Bulletin.dao.page(getPageNo(), getPageSize());
		success(pageList);
	}

}
