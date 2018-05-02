package com.emindsoft.zsj.culture.ctrl;

import java.util.List;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.culture.model.Silhouette;
import com.emindsoft.zsj.culture.model.SilhouetteYear;
import com.emindsoft.zsj.system.model.Area;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

@ControllerKey("silhouetteYear")
public class SilhouetteYearCtrl extends AdminBaseController<SilhouetteYear> {

	public SilhouetteYearCtrl() {
		this.modelClass = SilhouetteYear.class;
	}
	
	
	/**
	 * 增加墙报年份
	 */
	public void addSilhouetteYear() {
		SilhouetteYear silhouetteYear;
		try {
			silhouetteYear = getModel();
			String year=silhouetteYear.getStr("Region");
			if (SilhouetteYear.dao.findByRegion(year)!=null) {
				error(003, "已存在，不可创建");
				return;
			}
			silhouetteYear.set("keyid", SilhouetteYear.dao.getId());
			silhouetteYear.save();
			success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("添加异常！", e);
			error(003, "保存失败");
		}
	}
	
	
	/**
	 * 编辑墙报年份
	 */
	public void editSilhouetteYear() {
		SilhouetteYear s;
		try {
			s = getModel();
			s.update();
			success();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("更新异常！", e);
			error(002, "保存失败");
		}
	}
	
	/**
	 * 获取编辑数据
	 */
	public void editDataSilhouetteYear() {
		String keyid = getPara("keyid");
		success(SilhouetteYear.dao.findById(keyid));
	}
	
	/**
	 * 删除墙报年份
	 */
	public void deleteSilhouetteYear() {
		String keyid = getPara("keyid");
		String year = getPara("year");
		SilhouetteYear.dao.deleteById(keyid);
		Silhouette.dao.deleteByYear(year);
		success();
	}
	
	/**
	 * 加载年份树形结构
	 */
	public void loadYearTree() {
		List<SilhouetteYear> sy = SilhouetteYear.dao.find("select * from p_silhouetteyear ORDER BY region DESC");
		this.renderJson(sy);
	}
	
	
	/**
	 * 获取年份数据集
	 */
	public void yearList() {
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		Role r=Role.dao.findRolesByUser(getCurrentUserId());
		int orderid=0;
		if (r!=null) {
			orderid=r.getInt("orderid");
		}
		List<String> parentregion=Area.dao.getAllParentAreaRegionId(chooseRegionId);
		if("450000000000".equals(userRegionId) || (parentregion.contains(userRegionId) && 5==orderid)) {
			rp = RolePower.dao.getOperPower("1613", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("1613", getCurrentUserId());
		}
		Page<SilhouetteYear> pageList = SilhouetteYear.dao.page(getPageNo(), getPageSize());
		success(new PageVO(pageList,rp));
	}
	
}
