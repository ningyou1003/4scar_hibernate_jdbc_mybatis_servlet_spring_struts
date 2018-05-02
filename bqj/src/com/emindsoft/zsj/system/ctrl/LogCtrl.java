package com.emindsoft.zsj.system.ctrl;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Log;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.vo.LogSelectVO;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.plugin.activerecord.Page;

/**
 * 日志controller
 * 
 * @author ym
 * 
 */
@ControllerKey("log")
// 用注解表明访问这个controller所需要的字符串，命名为log
public class LogCtrl extends AdminBaseController<Role> {

	/**
	 * 构造方法，并在父类中封装了对象modelClass，方便获取页面数据
	 */
	public LogCtrl() {
		this.modelClass = Role.class;
	}

	/**
	 * 查询所有日志方法，把查到的数据放到JSON
	 */
	@PowerBind(code = "816_LookPower", funcName = "系统日志列表")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功能名称
	public void logList() {
		String selectRegionId;
		RolePower rp;
		String userRegionId = getCurrentUserRegionId();
		String chooseRegionId =getChooseRegionId();
		if(StringUtils.isEmpty(chooseRegionId) || "undefined".equals(chooseRegionId)) {
			rp = RolePower.dao.getOperPower("814", getCurrentUserId());
			selectRegionId = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("814",getCurrentUserId());
			selectRegionId = chooseRegionId;
		}
		LogSelectVO lsVO = Tools.getSubVO(LogSelectVO.class, getRequest());
		Page<Log> logPage = Log.dao.page(getPageNo(), getPageSize(), lsVO,
				getCurrentUser(),selectRegionId);
//		for(Log l:logPage.getList()){
//			if(l.getInt("demonstration")!=null && l.getInt("demonstration")==1){
//				l.set("Region", l.getStr("Region")+"(示范)");
//			}
//		}
		success(new PageVO(logPage, rp));
	}

	/**
	 * 删除日志记录的方法
	 */
	@PowerBind(code = "816_DelPower", funcName = "系统日志删除")
	// 调用自定义的注解@PowerBind，code权限代码，funcName功能名称
	public void deleteLog() {
		String[] keyids = getPara("keyids").split(",");
		Log.dao.deleteByIds(keyids);
		success();
	}
}
