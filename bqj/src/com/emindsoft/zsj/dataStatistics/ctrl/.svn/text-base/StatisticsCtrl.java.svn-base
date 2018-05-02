package com.emindsoft.zsj.dataStatistics.ctrl;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.dataStatistics.model.Statistics;
import com.emindsoft.zsj.dataStatistics.vo.StatisticsDetailVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.POIReadExcelToHtml;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.routebind.ControllerKey;

@ControllerKey("statistics")
public class StatisticsCtrl extends AdminBaseController<Statistics> {
	
	public StatisticsCtrl() {
		this.modelClass = Statistics.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Statistics statistics;
		try {
			statistics = getModel();
			statistics.set("keyid", Statistics.dao.getId());
//			statistics.set("releaseTime",dateTimeFormat.format(new Date()));
			statistics.set("userId",getCurrentUserId());
			statistics.set("regionId",getCurrentUserRegionId());
			statistics.set("status", 4);
			statistics.save();
			processAttachment(statistics.getStr("keyid"));
			success(statistics.getStr("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1411_DelPower", funcName = "删除统计")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Statistics.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1411_EditPower", funcName = "编辑统计")
	public void edit() {
		Statistics statistics;
		try {
			statistics = getModel();
			statistics.set("userId",getCurrentUserId());
//			statistics.set("releaseTime",dateTimeFormat.format(new Date()));
			statistics.update();
			success(statistics.getStr("keyId"));
		} catch(Exception e) {
			log.error("更新异常！", e);
			error(002, "保存失败！");
		}
	}
	
	/**
	 * 获取更新数据
	 */
	public void editData() {
		String keyid = getPara("keyId");
		Statistics statistics = Statistics.dao.findById(keyid);
		success(statistics);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void statisticsList() {
		String selectRegionId;
		RolePower rp;
		String SelectTitle = getPara("SelectTitle");//按标题查询
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("1411", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1411",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		Page<Statistics> pageList = Statistics.dao.page(getPageNo(), getPageSize(),SelectTitle, selectRegionId);
		success(new PageVO(pageList,rp));;
	}
	
	public void detailData() {
		String keyid = getPara("keyId");
		Statistics statistics = Statistics.dao.findById(keyid);
		
		POIReadExcelToHtml excel = new POIReadExcelToHtml();//excel表格转换为html
		String att = PropertiesContent.get("upload_dir");//获取上传位置
		List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"statistics");
		for(Attachment a : attachment) {
			String name = a.getStr("path");
			String filePath = att + name;
			String html = excel.readExcelToHtml(filePath,true);
			a.put("preview", html);
		} 
		success(new StatisticsDetailVO(statistics,attachment));
	}


}
