package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Dictionary;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.vo.DicPageVO;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by ym on 15-3-9.
 */
@ControllerKey("dic")
public class DicCtrl extends AdminBaseController<Dictionary> {

	public DicCtrl() {
		this.modelClass = Dictionary.class;
	}
	
	/**
	 * 数据字典列表
	 */
	public void dicList() {
		List<Dictionary> dicGroup = Dictionary.dao.loadSelect();
		RolePower rp = RolePower.dao.getOperPower("813", getCurrentUserId());
		String selectType = getPara("selectType");
		Page<Dictionary> dicList = Dictionary.dao.page(getPageNo(),
				getPageSize(), selectType);
		DicPageVO dicvo = new DicPageVO(dicList, rp);
		dicvo.setDicGroup(dicGroup);
		success(dicvo);
	}
	
	/**
	 * 数据字典列表
	 */
	public void getNameByType() {
		RolePower rp = RolePower.dao.getPagePower("813", getCurrentUserId());
		String selectType = getPara("selectType");
		Page<Dictionary> dicList = Dictionary.dao.page(getPageNo(),
				getPageSize(), selectType);
		DicPageVO dicvo = new DicPageVO(dicList, rp);
		success(dicvo);
	}
	
	/**
	 * 活动组合调用 2016.5.24
	 */
	public void getNameByTypeForAct() {
		String selectType = getPara("selectType");
		Page<Dictionary> dicList = Dictionary.dao.page(getPageNo(),
				getPageSize(), selectType);
		success(dicList);
	}
	
	/**
	 * 数据字典列表
	 */
	public void getDicNameByType() {
		String selectType = getPara("selectType");
		List<Dictionary> dicList = Dictionary.dao.findDicNameByType(selectType);
		success(dicList);
	}

	/**
	 * editdate()修改部门功能中读取信息出来修改
	 */
	public void editData() {			
		String keyid = getPara("keyid");
		Dictionary dic = Dictionary.dao.findById(keyid);
		success(dic);
	}
	
	/**
	 * 加载自动补齐控件字典列表
	 * @return
	 */
	public void dicAutoCompleteDate() {
		List<Dictionary> dicType = Dictionary.dao.findAllType();
		success(dicType);
	}
	/**
	 * deit()把读取出来的信息进行修改
	 */
	public void edit() {				
		Dictionary dic;
		try {
			dic = getModel();
			dic.update();
			success(dic);
		} catch (Exception e) {
			log.error("编辑字典信息异常", e);
			error(811);
		}
	}
	/**
	 * 给字典管理添加信息
	 */
	public void add() {
		Dictionary dic;
		try {
			dic = getModel();
			dic.set("KeyId", Dictionary.dao.getId());
			dic.save();
			success();
		} catch (Exception e) {
			log.error("添加字典信息异常", e);
			error(809);
		}
	}
	/**
	 * 删除选择的字典管理
	 */
	public void deleteDic() {
		String[] keyids = getPara("keyids").split(",");
		Dictionary.dao.deleteByIds(keyids);
		success(001);
	}

}
