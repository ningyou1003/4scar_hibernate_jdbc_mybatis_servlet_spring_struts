package com.emindsoft.zsj.zoneDefence.ctrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.emindsoft.zsj.zoneDefence.model.ZdFile;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("zdfile")
public class ZdFileCtrl extends AdminBaseController<ZdFile>{
	public ZdFileCtrl() {
		this.modelClass = ZdFile.class;
	}
	
	public void zdFileList() {
		String userId = getCurrentUserId();
		RolePower rp = RolePower.dao.getOperPower("200", userId);
		Page<ZdFile> zdFileList = ZdFile.dao.page(getPageNo(), getPageSize(), userId);
		PageVO zdsysVO = new PageVO(zdFileList, rp);
		success(zdsysVO);
	}
	
	/**
	 * 添加文件
	 */
	public void add() {
		ZdFile zdfile;
		try {
			zdfile = getModel();
			zdfile.set("KeyId", ZdFile.dao.getId());
			zdfile.set("uploaderId", getCurrentUserId());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			zdfile.set("uploadTime", time);
			zdfile.save();
			processAttachment(zdfile.getStr("keyid"));
			success();
		} catch (Exception e) {
			log.error("添加文件异常", e);
			error(003);
		}
	}
	
	public void edit() {					
		ZdFile zdfile;
		try {
			zdfile = getModel();
			zdfile.update();
			success(zdfile.getStr("KeyId"));
		} catch (Exception e) {
			log.error("更新文件异常", e);
			error(003);
		}
	}

	public void editData(){
		String keyid = getPara("keyid");
		ZdFile zdfile = ZdFile.dao.findById(keyid);
		success(zdfile);
	}
	
	/**
	 * 删除选择的文件
	 */
	public void delete() {
		String[] keyids = getPara("keyids").split(",");
		ZdFile.dao.deleteByIds(keyids);
		success(001);
	}

}
