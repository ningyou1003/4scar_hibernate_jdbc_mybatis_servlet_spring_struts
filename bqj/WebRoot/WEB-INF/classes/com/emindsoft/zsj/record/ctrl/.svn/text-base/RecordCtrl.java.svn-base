package com.emindsoft.zsj.record.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.cases.model.Big;
import com.emindsoft.zsj.record.model.Record;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.ApageVO;
import com.emindsoft.zsj.vo.CasesSelectVO;
import com.emindsoft.zsj.vo.PointsSelectVO;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("points")
public class RecordCtrl extends AdminBaseController<Record>{
	public RecordCtrl(){
		this.modelClass = Record.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Record record;
		try {
			record=(Record)getModel();
			record.set("keyid", Record.dao.getId());
			Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			record.set("time", nowdate);
			String creator = getCurrentUserId();
			User u = User.dao.loadUserDetail(creator);
			record.set("creator", u.getStr("relaname"));
			record.set("createuserid", getCurrentUserId());
			record.set("type", getPara("type"));
			record.set("status", 0);
			record.save();
			processAttachment(record.getStr("keyid"));
			success(record.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.error(801);
			throw e;
		}
	}
	
	public void edit(){
		Record record;
		try{
			record=(Record)getModel();
			record.update();
			success(record.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	@Before(Tx.class)
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Record.dao.deletePointsByIds(keyids);
		success();
	}
	
	public void editData() {			
		String keyid = getPara("keyid");
		Record record = Record.dao.loadPointsDetail(keyid);
		success(record);
	}
	
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}
		String type = getPara("type");
		String fid = getCurrentUserId();
		PointsSelectVO pointsVO = Tools.getSubVO(PointsSelectVO.class, getRequest());
		Page<Record> rePage=Record.dao.page(getPageNo(), getPageSize(), pointsVO,fid,type);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(rePage,rp));
		success(map);
	}
}
