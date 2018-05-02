package com.emindsoft.zsj.dataStatistics.ctrl;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.dataStatistics.model.Ledger;
import com.emindsoft.zsj.dataStatistics.vo.LedgerDetailVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.util.POIReadExcelToHtml;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

import cn.dreampie.routebind.ControllerKey;

@ControllerKey("ledger")
public class LedgerCtrl extends AdminBaseController<Ledger> {
	
	public LedgerCtrl() {
		this.modelClass = Ledger.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Ledger ledger;
		try {
			ledger = getModel();
			ledger.set("keyid", Ledger.dao.getId());
//			ledger.set("releaseTime",dateTimeFormat.format(new Date()));
			ledger.set("UserId",getCurrentUserId());
			ledger.set("regionId",getCurrentUserRegionId());
			ledger.set("status", 4);
			ledger.save();
			processAttachment(ledger.getStr("keyid"));
			success(ledger.getStr("keyId"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	@PowerBind(code = "1412_DelPower", funcName = "删除台帐")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Ledger.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1412_EditPower", funcName = "更新台帐")
	public void edit() {
		Ledger ledger;
		try {
			ledger = getModel();
			ledger.set("UserId",getCurrentUserId());
//			ledger.set("releaseTime",dateTimeFormat.format(new Date()));
			ledger.update();
			success(ledger.getStr("keyId"));
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
		Ledger ledger = Ledger.dao.findById(keyid);
		success(ledger);
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void ledgerList() {
		String selectRegionId;
		RolePower rp;
		String SelectTitle = getPara("SelectTitle");//按标题查询
		if("".equals(SelectTitle) || SelectTitle == null) {
			SelectTitle = "";
		}
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("1412", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1412",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		Page<Ledger> pageList = Ledger.dao.page(getPageNo(), getPageSize(), SelectTitle,selectRegionId);
		success(new PageVO(pageList,rp));;
	}
	
	/**
	 * 详情
	 */
	public void detailData() {
		String keyid = getPara("keyid");
		Ledger ledger = Ledger.dao.findById(keyid);
		
		POIReadExcelToHtml excel = new POIReadExcelToHtml();//excel表格转换为html
		String att = PropertiesContent.get("upload_dir");//获取上传位置
		List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"ledger");
		for(Attachment a : attachment) {
			String name = a.getStr("path");
			String filePath = att + name;
			String html = excel.readExcelToHtml(filePath,true);
			a.put("preview", html);
		}
		success(new LedgerDetailVO(ledger,attachment));
	}
	
	/**
	 * 查询APP台账详情数据
	 */
	public void getAppDetaiData() {
		detailData();
	}

}
