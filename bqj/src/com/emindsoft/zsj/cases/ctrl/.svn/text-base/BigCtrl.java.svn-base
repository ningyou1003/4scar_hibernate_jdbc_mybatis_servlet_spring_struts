package com.emindsoft.zsj.cases.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.cases.model.Big;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.ApageVO;
import com.emindsoft.zsj.vo.CasesSelectVO;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@ControllerKey("case")
public class BigCtrl extends AdminBaseController<Big>{
	
	public BigCtrl(){
		this.modelClass = Big.class;
	}
	
	@Before(Tx.class)
	public void add() throws Exception{
		Big b;
		try {
			b=(Big)getModel();
			b.set("keyid", Big.dao.getId());//生成keyid
			/*Date date = new Date();
			String nowdate = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			b.set("time", nowdate);//生成时间*/
			String creator = getCurrentUserId();
			User u = User.dao.loadUserDetail(creator);
			b.set("creator", u.getStr("relaname"));//生成创建者
			b.set("type", getPara("type"));
			b.set("createuserid", creator);
			b.set("status", 4);
			b.set("regionId", getCurrentUserRegionId());
			b.save();
			processAttachment(b.getStr("keyid"));
			success(b.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.error(801);
			throw e;
		}
	}
	@PowerBind(code = "10_EditPower", funcName = "编辑案件查办")
	public void edit(){
		Big b;
		try{
			b=(Big)getModel();
			b.update();
			success(b.getStr("keyid"));
		} catch (Exception e) {
			log.error("保存异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	@Before(Tx.class)
	@PowerBind(code = "10_DelPower", funcName = "删除案件查办")
	public void delete(){
		String[] keyids = getPara("keyids").split(",");
		Big.dao.deleteBigByIds(keyids);
		success();
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void editData() {			
		String keyid = getPara("keyid");
		Big b = Big.dao.loadBigDetail(keyid);
		success(b);
	}
	
	public void listPage(){
		RolePower rp ;
		String chooseRegionId = getChooseRegionId();//区域码
		String type = getPara("type");
		String uid = getCurrentUserId();
		String userRegionId = getCurrentUserRegionId();
		String ispublic = getPara("ispublic");
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());;
		}
		
		CasesSelectVO caseVO = Tools.getSubVO(CasesSelectVO.class, getRequest());
		Page<Big> grPage=Big.dao.page(getPageNo(), getPageSize(), caseVO,uid,type,chooseRegionId,userRegionId,ispublic);//fid是判断是否当前登录者是否跟创建在者匹配，是就显示数据
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("aPage", new ApageVO(grPage,rp));
		success(map);
	}
	
	//以下是前端网站接口--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listPageWeb(){
		String type = getPara("type");
		String regioncode=getPara("regioncode");
		Page<Big> list = Big.dao.page(getPageNo(), getPageSize(), type,regioncode);
		success(list);
	}
	
	@ClearInterceptor(ClearLayer.ALL)
	public void listPageForAndroid(){//安卓列表接口(带区域)
		String regionId = getRegionCodeForApp(); 
		String type = getPara("type");
		Page<Big> list = Big.dao.pageAndroid(getPageNo(), getPageSize(), type,regionId);
		success(list);
	}
}
