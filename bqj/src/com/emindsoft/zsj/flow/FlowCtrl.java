package com.emindsoft.zsj.flow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.attachment.ctrl.AttachmentController;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.PropertiesContent;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@ControllerKey("flow")
public class FlowCtrl extends AdminBaseController<Flow> {

	// 点击[上报]后弹出的人员选择
	public void chooseUser() {
		String issued=getPara("issued");
		String regionId = getCurrentUserRegionId();
		String ChooseRegionId=getChooseRegionId();//获取当前选择的区域编号 
		List<String> ChooseRegionIdParents=Area.dao.getAllParentAreaRegionId(ChooseRegionId);
		if (ChooseRegionIdParents.contains(regionId)) {
			regionId=ChooseRegionId;
		}
		Area area = Area.dao.findByRegion(regionId);
		
		String pId = "";
		if (area.getInt("operlevel") == 0) {// 当为最高级管理员时，审核人员可以为自己或者同级的人
			pId = area.getStr("regioncode");
		} else {
			pId = area.getStr("parentCode");
		}
		if (null!=issued && issued.equals("true")) {
			pId = area.getStr("regioncode");
			success(User.dao.findByParentRegionId(pId));
			return;
		}
		List<User> userList = User.dao.findByRegionId(pId);
		success(userList);
	}

	// 上报
	public void flowSend() {
		// 获取上传信息的keyid
		String flowId = getAttr("FlowId");
		// 获取上报信息类型
		String flowType = getAttr("FlowType");
		if(flowType.equals("n_events") || flowType.equals("n_history") || flowType.equals("n_leader")){
			//log.error("此类行记录不可上报！", e);
			error(003);
			return;
		}
		// 复制一条新的记录到对应信息表
		String tableName = PropertiesContent.get(flowType);
		if (!StringUtils.isEmpty(tableName)) {
			Record record = Db.findFirst("select * from " + tableName
					+ " where keyid='" + flowId + "'");
			if (record!=null) {
				String updatesql="UPDATE "+tableName+" SET status=3 WHERE keyid='" + flowId + "'";
				Db.update(updatesql);
			}
			
		}
			String newFlowId=saveTable(flowType, flowId);
		
		String userId = "", sql = "";
		Flow flow;
		String[] userIds = getPara("UserId").split(",");
		for (int i = 0; i < userIds.length; i++) {
			userId = userIds[i];
			sql = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
					+ "' AND UserId='" + userId + "'";
			flow = Flow.dao.findFirst(sql);
			// if(flow!=null){
			// error(1301);
			// } else {
			// 向待办表中添加一条记录
			flow = new Flow();
			try {
				flow.set("keyid", Flow.dao.getId());
				flow.set("flowType", flowType);
				flow.set("flowId", newFlowId);
				flow.set("userId", userId);
				flow.set("status", 0);
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				flow.set("StartTime", df.format(new Date()));
				flow.set("regionId", getCurrentUserRegionId());
				flow.save();
				// flow.reportTable(flowType, flowId);//将对应表中的数据状态改为已上报
				success(flow);
			} catch (Exception e) {
				log.error("上报异常！", e);
				error(003);
			}
			// }
		}

	}
	
	// 下发
		public void flowIssued() {
			// 获取上传信息的keyid
			String flowId = getAttr("FlowId");
			// 获取上报信息类型
			String flowType = getAttr("FlowType");
			String issued=getAttr("issued");
			String[] userIds = getPara("UserId").split(",");
			
			//List<String> regionids=Area.dao.getChildrenRegionCode(getCurrentUserRegionId());
			// 复制一条新的记录到对应信息表
//			for(String region:regionids){
//				saveTable(flowType, flowId,region);
//			}
				
			

			String userId = "", sql = "";
			Flow flow;
			String newflowid="";
			Map<String, String> map=new HashMap<String, String>();
			for (int i = 0; i < userIds.length; i++) {
				userId = userIds[i];
//				sql = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
//						+ "' AND UserId='" + userId + "'";
//				flow = Flow.dao.findFirst(sql);
				// if(flow!=null){
				// error(1301);
				// } else {
				// 向待办表中添加一条记录
				User u=User.dao.findById(userId);
				String uRegionid=u.getStr("RegionId");
				if (map.containsKey(uRegionid)) {
					newflowid=map.get(uRegionid);
				}else {
					newflowid=saveTable(flowType, flowId,uRegionid);
					map.put(uRegionid, newflowid);
				}
				flow = new Flow();
				try {
					flow.set("keyid", Flow.dao.getId());
					flow.set("flowType", flowType);
					flow.set("flowId", newflowid);
					flow.set("userId", userId);
					flow.set("status", 0);
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					flow.set("StartTime", df.format(new Date()));
					flow.set("regionId", uRegionid);
					flow.save();
					// flow.reportTable(flowType, flowId);//将对应表中的数据状态改为已上报
					
				} catch (Exception e) {
					log.error("下发异常！", e);
					error(003);
				}
				// }
			}
			success("下发成功！");
		}

	public void flowSend_old() {// 已弃用
		/*
		String flowType = getAttr("FlowType");
		String flowId = getAttr("FlowId");
		// String userId = getAttr("UserId");
		String userId = "", sql = "";
		Flow flow;
		String[] userIds = getPara("UserId").split(",");
		for (int i = 0; i < userIds.length; i++) {
			userId = userIds[i];
			sql = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
					+ "' AND UserId='" + userId + "'";
			flow = Flow.dao.findFirst(sql);
			if (flow != null) {
				error(1301);
			} else {
				// 向待办表中添加一条记录
				flow = new Flow();
				try {
					flow.set("keyid", Flow.dao.getId());
					flow.set("flowType", flowType);
					flow.set("flowId", flowId);
					flow.set("userId", userId);
					flow.set("status", 0);
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					flow.set("StartTime", df.format(new Date()));
					flow.set("regionId", getCurrentUserRegionId());
					flow.save();
					flow.reportTable(flowType, flowId);// 将对应表中的数据状态改为已上报
					success(flow);
				} catch (Exception e) {
					log.error("上报异常！", e);
					error(003);
				}
			}
		}
*/
		flowSend();
	}

	// 审核通过
	public void approve() {
		String flowType = getAttr("FlowType");
		String flowId = getAttr("FlowId");
		String userId = getCurrentUserId();
		String sql = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
				+ "' AND UserId='" + userId + "'";
		Flow flow = Flow.dao.findFirst(sql);
		flow.set("status", 1);
		String updatesql="UPDATE flow_todo SET status=1 WHERE FlowId='" + flowId
				+ "' AND UserId='" + userId + "'";
		Db.update(updatesql);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		flow.set("FinishTime", df.format(new Date()));
		flow.update();
		String tableName = PropertiesContent.get(flowType);
		if (!StringUtils.isEmpty(tableName)) {
			Record record = Db.findFirst("select * from " + tableName
					+ " where keyid='" + flowId + "'");
			String title=record.getStr("Title");
			String RegionId = getCurrentUserRegionId();
			
			Record record2 = Db.findFirst("select * from " + tableName
					+ " where Title='" + title + "' and regionId='"+RegionId+"'");
			if (record2!=null) {
				String sql2="update "+tableName+" set status=1 where keyid='"+record2.getStr("keyid")+"'";
//				System.out.println(sql2);
				Db.update(sql2);
			}
		}
		flow.updateTable(flowType, flowId);
		// 是否还将记录上报给其他人，有的话删除
		String sql1 = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
				+ "' AND UserId<>'" + userId + "'";
		List<Flow> flowList = Flow.dao.find(sql1);
		if (flowList.size() > 0) {
			for (Flow f : flowList) {
				f.delete();
			}
		}
		success();
	}

	/**
	 * wzf模块调用
	 */
	public void newapprove() {
		String flowType = getAttr("FlowType");
		String flowId = getAttr("FlowId");
		String userId = getCurrentUserId();
		String sql = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
				+ "' AND UserId='" + userId + "'";
		Flow flow = Flow.dao.findFirst(sql);
		flow.set("status", 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		flow.set("FinishTime", df.format(new Date()));
		flow.update();
		flow.updateTable(flowType, flowId);
		saveTable(flowType, flowId);// 审核通过后复制一条记录到相应表
		// 是否还将记录上报给其他人，有的话删除
		String sql1 = "SELECT * FROM flow_todo WHERE FlowId='" + flowId
				+ "' AND UserId<>'" + userId + "'";
		List<Flow> flowList = Flow.dao.find(sql1);
		if (flowList.size() > 0) {
			for (Flow f : flowList) {
				f.delete();
			}
		}
		success();
	}

	/**
	 * 复制上报的信息并保存
	 * 
	 * @param flowType
	 *            信息类型
	 * @param flowId
	 *            信息ID
	 * @return 
	 */
	private String  saveTable(String flowType, String flowId) {
		String keyid=null;
		String tableName = PropertiesContent.get(flowType);
		if (!StringUtils.isEmpty(tableName)) {
			Record record = Db.findFirst("select * from " + tableName
					+ " where keyid='" + flowId + "'");
			keyid = Flow.dao.getId();
			record.set("keyid", keyid);
			record.set("status", 0);
			if(record.getColumns().containsKey("ispublic")){
				record.set("IsPublic", 0);// 公开状态上传后为0（不公开）
			}
			//System.err.println(getCurrentUserRegionId());
			String pRegionId = Area.dao.findByRegionCode(
					getCurrentUserRegionId()).getStr("ParentCode");
			record.set("regionId", pRegionId);// 将复制的信息区域为当前用户所在区域的上级区域
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			if ("d_document".equals(tableName)) {
				record.set("uploaddate", df.format(new Date()));// 上报时间
				record.set("uploader", getCurrentUserId());// 上报人id
			}else{
				record.set("reportTime", df.format(new Date()));// 上报时间
				record.set("reportPersonId", getCurrentUserId());// 上报人id
			}
			
			
			if ("zoneDefence".equals(flowType)|| "activity".equals(flowType)|| "n_dynamic".equals(flowType)|| "n_events".equals(flowType)|| "other".equals(flowType)|| "show".equals(flowType)|| "formula".equals(flowType)) {
				record.set("IsPublic", 0);// 公开状态上传后为0（不公开）
			}
			
			
			// 特殊处理文化宣传：墙报与视频模块
			if ("silhouette".equals(flowType)) {
				String[] keyids = new String[2];// 新的附件ID
				String[] flowIds = new String[2];// 原附件ID
				keyids[0] = keyid;
				keyids[1] = UUID.randomUUID().toString().replace("-", "");
				flowIds[0] = flowId;
				flowIds[1] = record.get("SourceID");
				record.set("SourceID", keyids[1]);
				record.set("IsPublic", 0);// 公开状态
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowIds, keyids);// 复制上报信息关联的附件
			} else if ("video".equals(flowType)) {
				String[] keyids = new String[3];// 新的附件ID
				String[] flowIds = new String[3];// 原附件ID
				keyids[0] = keyid;
				keyids[1] = UUID.randomUUID().toString().replace("-", "");
				keyids[2] = UUID.randomUUID().toString().replace("-", "");
				flowIds[0] = flowId;
				flowIds[1] = record.get("VideoImgID");
				flowIds[2] = record.get("VideoSourceID");
				record.set("VideoImgID", keyids[1]);
				record.set("VideoSourceID", keyids[2]);
				record.set("IsPublic", 0);// 公开状态
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowIds, keyids);// 复制上报信息关联的附件
			} else {
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowId, keyid);// 复制上报信息关联的附件
			}
			
		}
		return keyid;
	}
	
	private String  saveTable(String flowType, String flowId,String sonregincode) {
		String tableName = PropertiesContent.get(flowType);
		String keyid=null;
		if (!StringUtils.isEmpty(tableName)) {
			Record record = Db.findFirst("select * from " + tableName
					+ " where keyid='" + flowId + "'");
			 keyid = Flow.dao.getId();
			record.set("keyid", keyid);
			record.set("regionId", sonregincode);// 将复制的信息区域更新为当前用户所下发的区域
			if(record.getColumns().containsKey("ispublic")){
				record.set("IsPublic", 0);// 公开状态上传后为0（不公开）
			}
			if(record.getColumns().containsKey("status")){
				record.set("status", 0);// 状态默认为未审核状态
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			if ("d_document".equals(tableName)) {
				record.set("uploaddate", df.format(new Date()));// 上报时间
				record.set("uploader", getCurrentUserId());// 上报人id
				if (null==record.getStr("sourceflowid") || record.getStr("sourceflowid").length()<10) {
					record.set("sourceflowid", flowId);// 源事件id
				}
				
			}else if("d_notice".equals(tableName)){
				record.set("publishTime", df.format(new Date()));// 上报时间
				record.set("publisherId", getCurrentUserId());// 上报人id
				if (null==record.getStr("sourceflowid") || record.getStr("sourceflowid").length()<10) {
					record.set("sourceflowid", flowId);// 源事件id
				}
			}
			else{
				record.set("reportTime", df.format(new Date()));// 上报时间
				record.set("reportPersonId", getCurrentUserId());// 上报人id
			}
			
			
			if ("zoneDefence".equals(flowType)|| "activity".equals(flowType)|| "n_dynamic".equals(flowType)|| "n_events".equals(flowType)|| "other".equals(flowType)|| "show".equals(flowType)|| "formula".equals(flowType)) {
				record.set("IsPublic", 0);// 公开状态
			}
			
			
			// 特殊处理文化宣传：墙报与视频模块
			if ("silhouette".equals(flowType)) {
				String[] keyids = new String[2];// 新的附件ID
				String[] flowIds = new String[2];// 原附件ID
				keyids[0] = keyid;
				keyids[1] = UUID.randomUUID().toString().replace("-", "");
				flowIds[0] = flowId;
				flowIds[1] = record.get("SourceID");
				record.set("SourceID", keyids[1]);
				record.set("IsPublic", 0);// 公开状态
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowIds, keyids);// 复制上报信息关联的附件
			} else if ("video".equals(flowType)) {
				String[] keyids = new String[3];// 新的附件ID
				String[] flowIds = new String[3];// 原附件ID
				keyids[0] = keyid;
				keyids[1] = UUID.randomUUID().toString().replace("-", "");
				keyids[2] = UUID.randomUUID().toString().replace("-", "");
				flowIds[0] = flowId;
				flowIds[1] = record.get("VideoImgID");
				flowIds[2] = record.get("VideoSourceID");
				record.set("VideoImgID", keyids[1]);
				record.set("VideoSourceID", keyids[2]);
				record.set("IsPublic", 0);// 公开状态
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowIds, keyids);// 复制上报信息关联的附件
			} else {
				Db.save(tableName, record);
				AttachmentController attCtrl = new AttachmentController();
				attCtrl.copyAttr(flowId, keyid);// 复制上报信息关联的附件
			}
			
		}
		return keyid;
	}

	/**
	 * 获得用户所有待办事件接口
	 */
	public void flowList() {
		String status = getAttr("status");
		String userId = getCurrentUserId();
		Page<Flow> flowList = Flow.dao.page(getPageNo(), getPageSize(), userId,
				status);
		
		success(flowList);
	}

	public void getType() {
		String tab = getAttr("Ftable");
		String kid = getAttr("Kid");
		String type = Db.queryStr("select type from " + tab + " where keyid='"
				+ kid + "'");
		success(type);
	}
	
	/**
	 * 待办取消显示
	 * 使待办事件的状态改为4，不再在列表显示
	 */
	public void readFlow(){
		try {
			
		String flowid=getPara("flowid");
		String userid=getCurrentUserId();
		//测试//System.out.println(flowid+","+userid);
		if (flowid!=null && flowid.length()>0) {
			Flow f=Flow.dao.FindByFlowid(flowid, userid);
			if (f==null) {
				error(0);
				return;
			}
			if (userid!=null && userid.equals(f.getStr("UserId"))) {
				f.set("status", 4);
				f.update();
				success();
			}else {
				error(0);
			}
		}

		} catch (Exception e) {
			e.printStackTrace();
			error(0);
		}
		
	}
	
	/**
	 * 待办已阅读接口
	 * 使待办事件的状态从null改为1，显示不同颜色
	 */
	public void openReadFlow(){
		try {
			
		String flowid=getPara("flowid");
		String userid=getCurrentUserId();
		//测试//System.out.println(flowid+","+userid);
		if (flowid!=null && flowid.length()>0) {
			Flow f=Flow.dao.FindByFlowid(flowid, userid);
			if (f==null) {
				error(0);
				return;
			}
			
			if (userid!=null && userid.equals(f.getStr("UserId"))) {
				String updatesql="UPDATE flow_todo SET isRead=1 WHERE FlowId='" + flowid
						+ "' AND UserId='" + userid + "'";
				Db.update(updatesql);
				success();
			}else {
				error(0);
			}
		}

		} catch (Exception e) {
			e.printStackTrace();
			error(0);
		}
		
	}
}
