package com.emindsoft.zsj.appealalarm.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.appealalarm.model.Appeal;
import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.ApageVO;
import com.emindsoft.zsj.vo.AppealSelectVO;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@ControllerKey("ap")
public class AppealCtrl extends AdminBaseController<Appeal>{
	
	public AppealCtrl(){
		this.modelClass = Appeal.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Appeal a;
		try {
			a=(Appeal)getModel();
			a.set("keyid", Appeal.dao.getId());
			/*Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			a.set("time", nowdate);*/
			String fid = getCurrentUserId();
			a.set("createuserId", fid);
			a.set("status", 4);
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
			a.set("regionId", RegionId);
			a.save();
			processAttachment(a.getStr("keyid"));
			success(a.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.error(801);
			throw e;
		}
	}
	@PowerBind(code = "1102_EditPower", funcName = "编辑投诉")
	public void edit(){
		Appeal a;
		try{
			a=(Appeal)getModel();
			a.update();
			success(a.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	@Before(Tx.class)
	@PowerBind(code = "1102_DelPower", funcName = "删除投诉")
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Appeal.dao.deleteAppealByIds(keyids);
		success();
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {			
		String keyid = getPara("keyid");
		Appeal a = Appeal.dao.loadAppealDetail(keyid);
		success(a);
	}
	
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String fid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId) 
				|| (parentregion.contains(userRegionId) && 5==orderid)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}
		
		AppealSelectVO apVO = Tools.getSubVO(AppealSelectVO.class, getRequest());
		Page<Appeal> grPage=Appeal.dao.page(getPageNo(), getPageSize(), apVO,fid,chooseRegionId,userRegionId);//fid是判断是否当前登录者是否跟创建在者匹配，是就显示数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listAppealForWeb(){
		String regioncode=getPara("regioncode");
		Page<Appeal> list = Appeal.dao.page(getPageNo(), getPageSize(),regioncode);
		//Page<Appeal> list = Appeal.dao.page(getPageNo(), getPageSize());
		success(list);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void addAppealForAndroid(){		
		String ap_describe = getPara("ap_describe");
		String ap_objectname = getPara("ap_objectname");
		String ap_objecttel = getPara("ap_objecttel");
		//String ap_opinion = getPara("ap_opinion");
		String title = "网络举报";
		
		Date date = new Date();
		String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		Appeal a;
		try {
			a = new Appeal();
			//a= (Appeal) getModel2(Appeal.class);
			a.set("keyid", Tools.getRandomSerial());
			a.set("title", title);
			a.set("regionid", getRegionCodeForApp());
			a.set("describe", ap_describe);
			a.set("feedbacker", ap_objectname);
			a.set("phone", ap_objecttel);
			a.set("time", nowdate);
			//a.set("opinion", ap_opinion);
			a.save();
			success(a.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.error(801);
		}
		
	}
}
