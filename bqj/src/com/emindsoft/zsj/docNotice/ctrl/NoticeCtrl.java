package com.emindsoft.zsj.docNotice.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.docNotice.model.Document;
import com.emindsoft.zsj.docNotice.model.Notice;
import com.emindsoft.zsj.docNotice.vo.SummationsVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("docNotice")
public class NoticeCtrl extends AdminBaseController<Notice>{
	public NoticeCtrl() {
		this.modelClass = Notice.class;
	}
	@PowerBind(code = "301_LookPower", funcName = "查看通知列表")
	public void noticeList() {
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode = "";
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("301", getCurrentUserId());
			regionCode = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("301", getCurrentUserId());
			regionCode = chooseRegionId;
		}
		Page<Notice> noticeList = Notice.dao.page(getPageNo(), getPageSize(), regionCode);
		PageVO noticeVO = new PageVO(noticeList, rp);
		success(noticeVO);
	}
	
	/**
	 * 添加通知
	 */
	@PowerBind(code = "301_AddPower", funcName = "添加通知")
	public void add() {
		Notice notice;
		try {
			notice = getModel();
			String keyid=Document.dao.getId();
			notice.set("KeyId", keyid);
			notice.set("publisherId", getCurrentUserId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			notice.set("publishTime", time);
			notice.set("regionId", getCurrentUserRegionId());
			notice.set("sourceflowid", keyid);// 源事件id
			notice.save();
			processAttachment(notice.getStr("keyid"));
			success();
		} catch (Exception e) {
			log.error("添加通知异常", e);
			error(003);
		}
	}
	
	@PowerBind(code = "301_EditPower", funcName = "编辑通知")
	public void edit() {					
		Notice notice;
		try {
			notice = getModel();
			notice.update();
			success(notice.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新通知异常", e);
			error(003);
		}
	}

	@ClearInterceptor(ClearLayer.ALL)
	public void editData(){
		String keyid = getPara("keyid");
		Notice notice = Notice.dao.findById(keyid);
		success(notice);
	}
	
	/**
	 * 删除选择的通知
	 */
	@PowerBind(code = "301_LookPower", funcName = "删除通知")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Notice.dao.deleteByIds(keyids);
		success(001);
	}
	
	/**
	 * 为安卓端提供的消息列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void noticeListForAndroid() {
		String regionCode = getRegionCodeForApp();
		Page<Notice> noticeList = Notice.dao.page(getPageNo(), getPageSize(), regionCode);
		success(noticeList);
	}
	

	/**
	 * 汇总
	 */
	public void summation(){
		String regionid=getCurrentUserRegionId();
		List<Notice> docList=Notice.dao.getCountByTime(regionid);
		List<SummationsVO> slist=new ArrayList<SummationsVO>();
		for(Notice d:docList){
			
			String time=d.getTimestamp("publishTime").toString().split(" ")[0];
			time=time.split("-")[0]+"-"+time.split("-")[1];
			boolean b=true;
			for(SummationsVO svo:slist){
				if (svo.getTime().equals(time)) {
					svo.setSum(svo.getSum()+d.getLong("status"));
					b=false;
					break;
				}
			}
			if (b) {
				slist.add(new SummationsVO(time, d.getLong("status")));
			}
		}
		
		success(slist); 
	}
}
