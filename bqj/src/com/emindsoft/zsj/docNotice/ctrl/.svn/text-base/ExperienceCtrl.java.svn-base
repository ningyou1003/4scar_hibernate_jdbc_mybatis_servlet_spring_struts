package com.emindsoft.zsj.docNotice.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.docNotice.model.Experience;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;
import cn.dreampie.routebind.ControllerKey;

@ControllerKey("experience")
public class ExperienceCtrl extends AdminBaseController<Experience> {
	
	public ExperienceCtrl() {
		this.modelClass = Experience.class;
	}
	
	@PowerBind(code = "302_LookPower", funcName = "查看经验介绍列表")
	public void experienceList() {
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode = "";
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("302", getCurrentUserId());
			regionCode = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("302", getCurrentUserId());
			regionCode = chooseRegionId;
		}
		Page<Experience> experienceList = Experience.dao.page(getPageNo(), getPageSize(), regionCode);
		PageVO experiencevo = new PageVO(experienceList, rp);
		success(experiencevo);
	}
	
	/**
	 * 添加经验介绍
	 */
	@PowerBind(code = "302_AddPower", funcName = "添加经验介绍")
	public void add() {
		Experience experience;
		try {
			experience = getModel();
			experience.set("KeyId", Experience.dao.getId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			experience.set("uploaddate", time);
			experience.set("regionId", getCurrentUserRegionId());
			experience.set("uploader", getCurrentUserId());
			experience.set("status", 0);
			experience.save();
			processAttachment(experience.getStr("keyid"));
			success();
		} catch (Exception e) {
			log.error("添加经验介绍异常", e);
			error(003);
		}
	}
	
	@PowerBind(code = "302_EditPower", funcName = "编辑经验介绍")
	public void edit() {					
		Experience experience;
		try {
			experience = getModel();
			experience.update();
			success(experience.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新经验介绍异常", e);
			error(003);
		}
	}

	public void editData(){
		String keyid = getPara("keyid");
		Experience experience = Experience.dao.findById(keyid);
		success(experience);
	}
	
	/**
	 * 删除选择的经验介绍
	 */
	@PowerBind(code = "302_DelPower", funcName = "删除经验介绍")
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		Experience.dao.deleteByIds(keyids);
		success(001);
	}
	
	/**
	 * 公众网获取经验介绍接口 
	 * @return
	 */
	public void getExperienceList(){
		//配置文件添加/experience/getExperienceList免登录
		String regioncode=getPara("regioncode");
		success(Experience.dao.page(getPageNo(), getPageSize(), regioncode));
		
	}

}
