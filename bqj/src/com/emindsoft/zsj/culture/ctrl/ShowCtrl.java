package com.emindsoft.zsj.culture.ctrl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Show;
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

@ControllerKey("show")
public class ShowCtrl extends AdminBaseController<Show> {
	
	public ShowCtrl() {
		this.modelClass = Show.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Show show;
		try {
			show = getModel();
			show.set("keyid", Show.dao.getId());
//			show.set("releaseTime",dateTimeFormat.format(new Date()));
			show.set("UserId",getCurrentUserId());
			show.set("status", 4);
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
			show.set("regionId",RegionId);
			show.save();
			processAttachment(show.getStr("keyid"));
			success(show.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1612_DelPower", funcName = "演出删除")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Show.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1612_EditPower", funcName = "演出更新")
	public void edit() {
		Show show;
		try {
			show = getModel();;
			show.set("UserId",getCurrentUserId());
//			show.set("releasetime",dateTimeFormat.format(new Date()));
			show.update();
			success(show.getStr("keyId"));
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
		Show show = Show.dao.findById(keyid);
		success(show);
	}
	
	/**
	 * 分页列表
	 * 
	 */
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
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)
				|| (parentregion.contains(userRegionId) && 5==orderid)) {
			rp = RolePower.dao.getOperPower("1612", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1612",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		if (!"undefined".equals(chooseRegionId)) {
			selectRegionId=chooseRegionId;
		}
		cultureSelectVO sv = Tools.getSubVO(cultureSelectVO.class, getRequest());
		Page<Show> pageList = Show.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),sv,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 分页
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getShowList() { 
		Page<Show> pageList = Show.dao.page(getPageNo(), getPageSize());
		success(pageList);
	}
}
