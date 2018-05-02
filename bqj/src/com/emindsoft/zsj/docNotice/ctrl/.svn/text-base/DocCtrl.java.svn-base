package com.emindsoft.zsj.docNotice.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import com.emindsoft.zsj.docNotice.vo.SummationsVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("doc")
public class DocCtrl extends AdminBaseController<Document>{
	public DocCtrl() {
		this.modelClass = Document.class;
	}
	
	@PowerBind(code = "300_LookPower", funcName = "查看文件列表")
	public void docList() {
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode = "";
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("300", getCurrentUserId());
			regionCode = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("300", getCurrentUserId());
			regionCode = chooseRegionId;
		}
		Page<Document> docList = Document.dao.page(getPageNo(), getPageSize(), regionCode);
		PageVO docvo = new PageVO(docList, rp);
		success(docvo);
	}
	
	/**
	 * 添加文件
	 */
	@PowerBind(code = "300_AddPower", funcName = "添加文件")
	public void add() {
		Document doc;
		try {
			doc = getModel();
			String keyid=Document.dao.getId();
			doc.set("KeyId", keyid);
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			doc.set("uploaddate", time);
			doc.set("regionId", getCurrentUserRegionId());
			doc.set("uploader", getCurrentUserId());
			doc.set("sourceflowid", keyid);// 源事件id
			doc.set("status", 0);
			doc.save();
			processAttachment(doc.getStr("keyid"));
			success();
		} catch (Exception e) {
			log.error("添加文件异常", e);
			error(003);
		}
	}
	
	@PowerBind(code = "300_EditPower", funcName = "编辑文件")
	public void edit() {					
		Document doc;
		try {
			doc = getModel();
			doc.update();
			success(doc.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新文件异常", e);
			error(003);
		}
	}

	public void editData(){
		String keyid = getPara("keyid");
		Document doc = Document.dao.findById(keyid);
		success(doc);
	}
	
	/**
	 * 删除选择的文件
	 */
	@PowerBind(code = "300_DelPower", funcName = "删除文件")
	public void deleteDoc() {
		String[] keyids = getPara("keyids").split(",");
		Document.dao.deleteByIds(keyids);
		success(001);
	}

	/**
	 * 为安卓端提供的文件列表
	 */
	@ClearInterceptor(ClearLayer.ALL)
	public void docListForAndroid() {
		String regionCode = getRegionCodeForApp();
		Page<Document> docList = Document.dao.page(getPageNo(), getPageSize(), regionCode);
		success(docList);
	}
	
	
	/**
	 * 汇总
	 */
	public void summation(){
		String regionid=getCurrentUserRegionId();
		List<Document> docList=Document.dao.getCountByTime(regionid);
		
		List<SummationsVO> slist=new ArrayList<SummationsVO>();
		for(Document d:docList){
			
			String time=d.getTimestamp("uploaddate").toString().split(" ")[0];
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
