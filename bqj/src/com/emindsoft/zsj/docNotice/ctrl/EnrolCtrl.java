package com.emindsoft.zsj.docNotice.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.docNotice.model.Document;
import com.emindsoft.zsj.docNotice.model.Enrol;
import com.emindsoft.zsj.docNotice.model.Notice;

@ControllerKey("enrol")
public class EnrolCtrl extends AdminBaseController<Enrol>{
	
	/**
	 * 添加报名
	 */
	public void add(){
		String createUser=getCurrentUserId();
		String regioncode=getCurrentUserRegionId();
		String flowid=getPara("flowid");
		String type=getPara("type");
		String sourceFlowId=null;
		
		
		
		if (Integer.parseInt(type)==0) {
			sourceFlowId=Document.dao.findById(flowid).getStr("sourceFlowId");
		}else{
			sourceFlowId=Notice.dao.findById(flowid).getStr("sourceFlowId");
		}
		if (sourceFlowId==null||sourceFlowId.length()<10) {
			sourceFlowId=flowid;
		}
		try {
			Enrol e=new Enrol();
			e.set("keyid", e.getId());
			e.set("type", type);
			e.set("createUser", createUser);
			e.set("flowid", flowid);
			e.set("sourceFlowId", sourceFlowId);
			e.set("RegionCode", regioncode);
			e.set("Time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			e.set("Name", getPara("name"));
			e.set("Sex", getPara("sex"));
			e.set("Phone", getPara("phone"));
			e.set("Peoples", getPara("peoples"));
			e.set("Duty", getPara("duty"));
			e.set("transportation", getPara("transportation"));
			e.set("arriveTime", getPara("arrivetime"));
			e.set("arriveSite", getPara("arrivesite"));
			e.set("Ps", getPara("ps"));
			
			e.save();
			success(e.getStr("KeyId"));
		} catch (Exception e) {
			e.printStackTrace();
			error(4);
		}
		
	}
	/**
	 * 获得报名列表
	 */
	public void list(){
		try {
		String flowid=getPara("flowid");
		success(Enrol.dao.list(flowid));
		} catch (Exception e) {
			e.printStackTrace();
			error(4, "获取数据异常");
		}
	}
	
	/**
	 * 删除一条报名记录
	 */
	public void remove(){
		try {
		
		String enrolid=getPara("eid");
		Enrol e=Enrol.dao.findById(enrolid);
		e.delete();
		success();
		} catch (Exception e) {
			e.printStackTrace();
			error(4, "删除报名记录异常");
		}
	}
	
	/**
	 * 源文件获取所有报名信息
	 */
	public void all(){
		try {
			
		
			String flowid=getPara("flowid");
			String sourceFlowId=flowid;
//			Enrol e=Enrol.dao.findFistByFlowid(flowid);
//			if (e==null) {
//				success("");
//				return;
//			}
//			
//			sourceFlowId=e.getStr("sourceFlowId");
			if (sourceFlowId==null || flowid.equals(sourceFlowId)) {
				success(Enrol.dao.allList(sourceFlowId));
			}else {
				success(Enrol.dao.list(flowid));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			error(4, "获取报名信息异常");
		}
	}
}
