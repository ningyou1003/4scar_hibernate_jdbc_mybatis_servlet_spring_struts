package com.emindsoft.zsj.appealalarm.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.appealalarm.model.Alarm;
import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.cases.model.Big;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.AlarmSelectVO;
import com.emindsoft.zsj.vo.ApageVO;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@ControllerKey("al")
public class AlarmCtrl extends AdminBaseController<Alarm>{
	
	public AlarmCtrl(){
		this.modelClass = Alarm.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Alarm a;
		try {
			a=(Alarm)getModel();
			a.set("keyid", Alarm.dao.getId());
			/*Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			a.set("atime", nowdate);*/
			String fid = getCurrentUserId();
			User u = User.dao.findById(fid);
			a.set("createuserId", fid);
			a.set("creator", u.getStr("RelaName"));
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
	@PowerBind(code = "1101_EditPower", funcName = "编辑举报")
	public void edit(){
		Alarm a;
		try{
			a=(Alarm)getModel();
			a.update();
			success(a.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	@Before(Tx.class)
	@PowerBind(code = "1101_DelPower", funcName = "删除举报")
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Alarm.dao.deleteAlarmByIds(keyids);
		success();
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {			
		String keyid = getPara("keyid");
		Alarm a = Alarm.dao.loadAlarmDetail(keyid);
		success(a);
	}
	
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String fid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode="";
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
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());//只能看
		}	
		
		if (!"undefined".equals(chooseRegionId)) {
			regionCode=chooseRegionId;
		}
		AlarmSelectVO apVO = Tools.getSubVO(AlarmSelectVO.class, getRequest());
		Page<Alarm> grPage=Alarm.dao.page(getPageNo(), getPageSize(), apVO,fid,chooseRegionId,userRegionId);//fid是判断是否当前登录者是否跟创建在者匹配，是就显示数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listAlarmForWeb(){
		String regioncode=getPara("regioncode");
		Page<Alarm> list = Alarm.dao.page(getPageNo(), getPageSize(),regioncode);
		//Page<Alarm> list = Alarm.dao.page(getPageNo(), getPageSize());
		success(list);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void addAlarmForAndroid(){		
		String adescribe = getPara("adescribe");
		String objectname = getPara("objectName");
		String objecttel = getPara("objectTel");
		String gender = getPara("gender");
		//String opinion = getPara("opinion");
		String title = "网络举报";
		
		Date date = new Date();
		String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
		Alarm a;
		try {
			a=(Alarm)getModel();
			a.set("keyid", Tools.getRandomSerial());
			a.set("title", title);
			a.set("regionid", getRegionCodeForApp());
			a.set("adescribe", adescribe);
			a.set("object", objectname);
			a.set("objecttel", objecttel);
			a.set("gender", gender);
			a.set("atime", nowdate);
			//a.set("opinion", opinion);
			a.save();
			success(a.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.error(801);
		}
		
	}
}
