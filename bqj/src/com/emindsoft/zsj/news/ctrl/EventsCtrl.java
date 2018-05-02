package com.emindsoft.zsj.news.ctrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.news.model.Dynamic;
import com.emindsoft.zsj.news.model.Events;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.ApageVO;
import com.emindsoft.zsj.vo.EventsSelectVO;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@ControllerKey("events")
public class EventsCtrl extends AdminBaseController<Events>{
	public EventsCtrl(){
		this.modelClass=Events.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Events events;
		try {
			events=(Events)getModel();
			events.set("keyid", Events.dao.getId());
			Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			events.set("time", nowdate);
			String creator = getCurrentUserId();
			User u = User.dao.loadUserDetail(creator);
			events.set("creator", u.getStr("relaname"));//生成创建者
			events.set("createuserid", creator);
			events.set("status", 4);
			events.set("regionId",  u.getStr("RegionId"));
			events.save();
			processAttachment(events.getStr("keyid"));
			success(events.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.error(801);
			throw e;
		}
	}
	@PowerBind(code = "1205_EditPower", funcName = "编辑历年事件")
	public void edit(){
		Events events;
		try{
			events=(Events)getModel();
			events.update();
			success(events.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	@Before(Tx.class)
	@PowerBind(code = "1205_DelPower", funcName = "删除历年事件")
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Events.dao.deleteEventsByIds(keyids);
		success();
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {			
		String keyid = getPara("keyid");
		Events events = Events.dao.loadEventsDetail(keyid);
		success(events);
	}
	
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String fid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		String ispublic = getPara("ispublic");
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}		
		
		EventsSelectVO evVO = Tools.getSubVO(EventsSelectVO.class, getRequest());
		Page<Events> grPage=Events.dao.page(getPageNo(), getPageSize(), evVO,fid,chooseRegionId,userRegionId,ispublic);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	public void listMonth(){
		List arrayList = new ArrayList();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month=ca.get(Calendar.MONTH)+1;
		String date;
		for (int i=month; i>=1;i--) {
			if(month>9){
				date=+year+"-"+i;
			}else{
				date=+year+"-0"+i;
			}
					
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("month", date);
			arrayList.add(map);
		}
		success(arrayList);
	}
	public void listEvents(){
		
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}
		
		String fid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		EventsSelectVO evVO = Tools.getSubVO(EventsSelectVO.class, getRequest());
		Page<Events> grPage=Events.dao.pageEvents(getPageNo(), getPageSize(), evVO,fid,chooseRegionId,userRegionId);
		List<Events> list= grPage.getList();
		for (Events events : list) {
			String eData = events.getStr("month");//如2016-06
			String[] sData=eData.split("-");
			String eyear = sData[0].toString();
			String emonth = sData[1].toString();
			events.put("eyear", eyear);
			events.put("emonth", emonth);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listEventsWeb(){
			Page<Events> grPage=Events.dao.pageEventsWeb(getPageNo(), getPageSize());
			List<Events> list= grPage.getList();
			for (Events events : list) {
				String eData = events.getStr("month");//如2016-06
				String[] sData=eData.split("-");
				String eyear = sData[0].toString();
				String emonth = sData[1].toString();
				events.put("eyear", eyear);
				events.put("emonth", emonth);
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("aPage", grPage);
			success(map);
		}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listEventsApp(){//app接口，大事记
		String regionId = getRegionCodeForApp();
		int page = getParaToInt("page");
		//int pageSize = getParaToInt("pageSize");
		Page<Events> events=Events.dao.pageApp(page, getPageSize(),regionId);
		List<Events> list = events.getList();
		for (Events e : list) {
			String eData = e.getStr("month");
			String[] sData=eData.split("-");
			String eyear = sData[0].toString();
			String emonth = sData[1].toString();
			e.put("eyear", eyear);
			e.put("emonth", emonth);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", events);
		success(map);
		
	}
	
	/**
	 * 官网分页列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void getEventsList() {
		String regioncode=getPara("regioncode");
		Page<Events> pageList = Events.dao.page(getPageNo(), getPageSize(),regioncode);
		success(pageList);
	}
}
