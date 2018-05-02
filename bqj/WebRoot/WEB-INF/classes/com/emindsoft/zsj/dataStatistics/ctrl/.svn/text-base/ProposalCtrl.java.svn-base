package com.emindsoft.zsj.dataStatistics.ctrl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.dataStatistics.model.Proposal;
import com.emindsoft.zsj.dataStatistics.model.Statistics;
import com.emindsoft.zsj.dataStatistics.vo.ProposalDetailVO;
import com.emindsoft.zsj.dataStatistics.vo.StatisticsDetailVO;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.POIReadExcelToHtml;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.routebind.ControllerKey;

@ControllerKey("proposal")
public class ProposalCtrl extends AdminBaseController<Proposal> {
	
	public ProposalCtrl() {
		this.modelClass = Proposal.class;
	}
	
	/**
	 * 添加
	 * 
	 */
	public void add() {
		Proposal proposal;
		try {
			proposal = getModel();
			proposal.set("keyid", Proposal.dao.getId());
//			proposal.set("releaseTime",dateTimeFormat.format(new Date()));
			proposal.set("userId",getCurrentUserId());
			proposal.set("regionId",getCurrentUserRegionId());
			proposal.set("status", 4);
			proposal.save();
			processAttachment(proposal.getStr("keyid"));
			success(proposal.getStr("keyid"));
		} catch(Exception e) {
			log.error("添加异常！", e);
			error(003, "保存失败！");
		}
	}
	
	/**
	 * 删除
	 * 
	 */
	
	@PowerBind(code = "1414_DelPower", funcName = "删除意见与建议")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Proposal.dao.deleteById(keyids);
		success(001);
	}
	
	/**
	 * 更新
	 * 
	 */
	@PowerBind(code = "1414_EditPower", funcName = "编辑意见与建议")
	public void edit() {
		Proposal proposal;
		try {
			proposal = getModel();
			proposal.set("userId",getCurrentUserId());
//			proposal.set("releaseTime",dateTimeFormat.format(new Date()));
			proposal.update();
			success(proposal.getStr("keyId"));
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
		Proposal proposal = Proposal.dao.findById(keyid);
		success(proposal);
	}
	
	public void detailData() {
		String keyid = getPara("keyId");
		Proposal proposal = Proposal.dao.findById(keyid);
		
		POIReadExcelToHtml excel = new POIReadExcelToHtml();//excel表格转换为html
		String att = PropertiesContent.get("upload_dir");//获取上传位置
		List<Attachment> attachment = Attachment.dao.find("SELECT path FROM base_attachment WHERE RelateId = ? and RelateType = ? ",keyid,"proposal");
		for(Attachment a : attachment) {
			String name = a.getStr("path");
			String filePath = att + name;
			String html = excel.readExcelToHtml(filePath,true);
			a.put("preview", html);
		} 
		success(new ProposalDetailVO(proposal,attachment));
	}
	
	/**
	 * 分页列表
	 * 
	 */
	public void proposalList() {
		String selectRegionId;
		RolePower rp;
		String SelectTitle = getPara("SelectTitle");//按标题查询
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId) || userRegionId.equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("1414", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("1414",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		Page<Proposal> pageList = Proposal.dao.page(getPageNo(), getPageSize(),SelectTitle,selectRegionId);
		success(new PageVO(pageList,rp));;
	}

}
