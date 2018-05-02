package com.emindsoft.zsj.culture.ctrl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Media;
import com.emindsoft.zsj.culture.vo.cultureSelectVO;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("media")
public class MediaCtrl extends AdminBaseController<Media> {
	
	public MediaCtrl() {
		this.modelClass = Media.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Media media;
		try {
			media = getModel();
			media.set("keyid", Media.dao.getId());
			media.set("UserId",getCurrentUserId());
			media.set("status", 4);
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
			media.set("regionId",RegionId);
			media.save();
			processAttachment(media.getStr("keyid"));
			success(media.get("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1616_DelPower", funcName = "媒体宣传删除")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Media.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1616_EditPower", funcName = "媒体宣传更新")
	public void edit() {
		Media media;
		try {
			media = getModel();;
			media.set("UserId",getCurrentUserId());
			media.update();
			success(media.getStr("keyId"));
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
		Media media = Media.dao.findById(keyid);
		success(media);
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
		//获得当前选择区域的所有上级区域
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
		Page<Media> pageList = Media.dao.page(getPageNo(), getPageSize(),getCurrentUserId(),sv,selectRegionId);
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 前端公众网分页
	 */
	public void getMediaList(){
		String regioncode=getPara("regioncode");
		success(Media.dao.getPageList(getPageNo(), getPageSize(), regioncode));
	}

}
