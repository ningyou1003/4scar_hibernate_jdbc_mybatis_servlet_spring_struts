package com.emindsoft.zsj.activity.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.activity.model.Activity;
import com.emindsoft.zsj.activity.vo.ActivityPageVO;
import com.emindsoft.zsj.activity.vo.ActivityVO;
import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.docNotice.model.Document;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.RolePower;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("activity")
public class ActivityCtrl extends AdminBaseController<Activity>{
	public ActivityCtrl() {
		this.modelClass = Activity.class;
	}
	
	@PowerBind(code = "500_LookPower", funcName = "查看专项活动列表")
	public void activityList() {
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String type = getPara("type");
		String title = getPara("title");
		String ispublic = getPara("ispublic");
		String regionCode = "";
		//获得当前选择区域的所有上级区域
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		
		if(StringUtils.isEmpty(chooseRegionId)
				||"undefined".equals(chooseRegionId)
				||userRegionId.equals(chooseRegionId) 
				|| parentregion.contains(userRegionId)){		
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
			regionCode = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());
			regionCode = chooseRegionId;
		}
		if (!"undefined".equals(chooseRegionId)) {
			regionCode=chooseRegionId;
		}
		Page<Activity> actList = Activity.dao.page(getPageNo(), getPageSize(), type, regionCode,title, ispublic);
		ActivityPageVO actPage = new ActivityPageVO(actList, rp, type);
		success(actPage);
	}
	
	/**
	 * 添加专项活动
	 */
	@PowerBind(code = "500_AddPower", funcName = "添加专项活动")
	public void add() {
		Activity activity;
		try {
			activity = getModel();
			activity.set("keyid", Activity.dao.getId());
			activity.set("createUserId", getCurrentUserId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			activity.set("createTime", time);
			activity.set("status", 4);
			//修改为保存时的区域是当前选择的区域
			String RegionId=getCurrentUserRegionId();
			if (!"undefined".equals(getChooseRegionId())) {
				RegionId=getChooseRegionId();
			}
			activity.set("regionId", RegionId);
			
			activity.save();
			processAttachment(activity.getStr("keyid"));
			success(activity.get("keyid"));
		} catch (Exception e) {
			log.error("添加专项活动异常", e);
			error(003);
		}
	}
	
	@PowerBind(code = "500_EditPower", funcName = "编辑专项活动")
	public void edit() {					
		Activity act;
		try {
			act = getModel();
			act.update();
			success(act.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新专项活动异常", e);
			error(002);
		}
	}
	
	public void editData(){
		String activityId = getPara("keyid");
		Activity act = Activity.dao.findById(activityId);
		List<Document> referList = Document.dao.getReferList(activityId);
		ActivityVO actVO = new ActivityVO(act, referList);
		success(actVO);
	}
	/**
	 * 删除选择的专项活动
	 */
	@PowerBind(code = "500_DelPower", funcName = "删除专项活动")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Activity.dao.deleteByIds(keyids);
		success(001);
	}
	
	//-------------------为前端官网提供的接口开始-----------------------
	/**
	 * 为前端提供的列表接口，返回公开的类型
	 */
	public void actListForFront() {
		String type = getPara("type");
		String regioncode=getPara("regioncode");
		Page<Activity> actList = Activity.dao.page(getPageNo(), getPageSize(), type,regioncode);
		success(actList);
	}
	
	//-------------------为前端官网提供的接口结束-----------------------
	
	//-------------------为前端官网提供的接口开始-----------------------
		/**
		 * 为前端提供的列表接口，返回公开的类型
		 */
		public void actListForIndexFront() {
			String regioncode=getPara("regioncode");
			Page<Activity> actList = Activity.dao.pageForIndex(getPageNo(), getPageSize(),regioncode);
			success(actList);
		}
		
		//-------------------为前端官网提供的接口结束-----------------------

}
