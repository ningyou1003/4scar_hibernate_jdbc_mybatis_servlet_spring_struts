package com.emindsoft.zsj.cases.ctrl;

import java.util.List;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.cases.model.FormulaYear;
import com.emindsoft.zsj.culture.model.SilhouetteYear;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("formulayear")
public class FormulaYearCtrl extends AdminBaseController<FormulaYear>{
	
	public FormulaYearCtrl(){
		this.modelClass = FormulaYear.class;
	}
	
	/**
	 * 加载年份树形结构
	 */
	public void loadYearTree() {
		List<FormulaYear> fy = FormulaYear.dao.loadYearTree();
		this.renderJson(fy);
	}
	
	/**
	 * 获取年份数据集
	 */
	public void yearList() {
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		if("450000000000".equals(userRegionId)) {
			rp = RolePower.dao.getOperPower("1613", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("1613", getCurrentUserId());
		}
		Page<FormulaYear> pageList = FormulaYear.dao.page(getPageNo(), getPageSize());
		success(new PageVO(pageList,rp));
	}
	
	/**
	 * 增加年份
	 */
	public void addFormulaYear() {
		FormulaYear f;
		try {
			f = getModel();
			f.set("keyid", FormulaYear.dao.getId());
			f.save();
			success();
		} catch (Exception e) {
			log.error("添加异常！", e);
			success(003);
		}
		success();
	}
	
	/**
	 * 编辑年份
	 */
	public void editFormulaYear() {
		FormulaYear f;
		try {
			f = getModel();
			f.update();
			success();
		} catch (Exception e) {
			log.error("更新异常！", e);
			success(002);
		}
	}
	
	/**
	 * 删除年份
	 */
	public void deleteFormulaYear() {
		String keyid = getPara("keyid");
		success(FormulaYear.dao.deleteById(keyid));
	}
}
