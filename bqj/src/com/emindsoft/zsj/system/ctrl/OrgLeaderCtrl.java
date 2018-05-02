package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.OrgLeader;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.List;

@ControllerKey("orgleader")
public class OrgLeaderCtrl extends AdminBaseController<OrgLeader> {
	
	private String table = "s_org_user";
	
	public OrgLeaderCtrl() {
		this.modelClass = OrgLeader.class;
	}
	
	/**
	 * 列表
	 */
	public void listLeader() {
		String orgid = getPara("orgid");
		List<?> list=Db.find("SELECT u.KeyID,o.KeyID as id,u.RelaName as name, u.phone as phone,u.mobile as mobile,(SELECT j.NAME FROM s_job j WHERE j.KeyID = u.JobsID ) as job FROM s_user u ,s_org_user o WHERE u.KeyID=o.userid AND o.orgid=? ",orgid);
		success(list);
	}
	
	/**
	 * 添加领导的方法
	 * @throws Exception
	 */
	@Before(Tx.class)
	public void orgaddleader() throws Exception{
		OrgLeader orgleader;
		try {
			orgleader= getModel();
			String[] userid = orgleader.getStr("userid").split(",");
			for(int i=0;i<userid.length;i++){
				orgleader.set("KeyID", OrgLeader.dao.getId());				
				orgleader.set("userid", userid[i]);
				boolean depUser=OrgLeader.dao.checkUserid(orgleader.getStr("orgid"),userid[i]);
				if(depUser){
					this.rendJson(false,null,"您选择的领导在列表中已经存在");
					return;
				}
				orgleader.save();
			}
			this.success();
			/*orgleader=(OrgLeader) getModel();//页面不多选，不..
			orgleader.set("KeyID", OrgLeader.dao.getId());	
			boolean depUser=OrgLeader.dao.checkUserid(orgleader.getStr("orgid"),orgleader.getStr("userid"));
			if(depUser){
				this.rendJson(false,null,"您选择的领导已经在列表中");
				return;
			}
			orgleader.save();
			this.success();*/
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
		
	}
	
	//删除
	@Before(Tx.class)
	public void delete() {
		try {
			String keyid = getPara("keyid");	
			OrgLeader.dao.deleteById(keyid);
			success();
		} catch (Exception e) {
			log.error("删除异常", e);
			error(803);
		}
	}
	
}
