package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;
import com.alibaba.fastjson.JSON;
import com.emindsoft.zsj.StatusCode;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.OrgDepartment;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.system.vo.OrgDepTreeVO;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;

import java.util.List;
/**
 * Created by ym on 15-3-9.
 */
@ControllerKey("org/dep")
public class OrgDepCtrl extends AdminBaseController<OrgDepartment> {

	public OrgDepCtrl() {
		this.modelClass = OrgDepartment.class;
	}
	
	/**
	 *获得弹出框部门树形信息 
	 */
	public void loadDepTree() {
		this.renderJson(OrgDepartment.dao.loadDepartment(getCurrentUserRegionId()));
	}

	/**
	 * 
	 */
	public void loadDepTreeByOrgId(){
		String orgId = getPara("orgId");
		List<OrgDepartment> depList = OrgDepartment.dao.loadDepartment(getCurrentUserRegionId(),orgId);
		this.renderJson(depList);
	}
	
	/**
	 * deleteDepUpdateIsAvailable()删除选择的部门信息
	 * 更新字段IsAvailable为1,不可见	
	 */
	public void deleteDepUpdateIsAvailable() {
		String[] keyids = getPara("keyids").split(",");
		boolean depUser = User.dao.findByDepNo(keyids);
		boolean isParetnDep =  OrgDepartment.dao.checkParentDep(keyids);
		if(depUser){
			this.rendJson(false, 820, StatusCode.get("820"),keyids);
			return;
		}else if(isParetnDep){
			this.rendJson(false, 830, StatusCode.get("830"),keyids);
			return;
		}else{
			OrgDepartment.dao.deleteDepByIds(keyids);
			this.rendJson(true, null, "操作成功！", id);
		}
	}
	
	/**
	 * editData()查询要读取的信息
	 */
	public void editData() {
		String keyid = getPara("keyid");
		OrgDepartment orgDepartment = OrgDepartment.dao.findOrgDepDataByKeyid(keyid);
		success(orgDepartment);
	}

	/**
	 * edit()把读取出来的信息进行修改
	 */
	public void edit() {					
		OrgDepartment department;
		try {
			department = getModel();
			department.update();
			success(department);
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}
	
	/**
	 * 添加部门信息
	 */
	public void add() {
		OrgDepartment department;
		try {
			department = getModel();
			department.set("KeyId", OrgDepartment.dao.getId());
			department.save();
			success(department);
		} catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
	}

	public void loadOrgDepTree(){
		String orgid = getPara("orgid");
		List<Record> deplist = OrgDepartment.dao.loadOrgDepTree(orgid);
		renderJson("Rows",deplist);
	}

	public void loadOrgDepTreePick(){
		String orgid = getPara("orgid");
		if(orgid==null||orgid=="" ){
			User user = User.dao.findById(getCurrentUserId());
			orgid = user.getStr("orgid");
		}
		List<Record> deplist = OrgDepartment.dao.loadOrgDepTree(orgid);
		this.renderJson(deplist);
	}

	public void loadOrgDepTree2(){
		String orgid = getPara("orgid");
		List<OrgDepTreeVO> deplist = OrgDepartment.dao.loadOrgDepTree2(orgid);
		String text = JSON.toJSONString(deplist);
		text = "{\"Rows\":" + text + "}";
		render(new JsonRender(text));//*/
	}

	public void loadPickLeader(){
		String orgid = getPara("orgid");
		if(orgid==null||orgid=="" ){
			User user = User.dao.findById(getCurrentUserId());
			orgid = user.getStr("orgid");
		}
		List<OrgDepartment> deplist = OrgDepartment.dao.loadPickLeader(orgid);
		this.renderJson(deplist);
	}
	
	/**
	 * 活动管理模块调用的添加机构下部门和人员方法　2016.1.18 wzf
	 */
	public void loadPickPerson(){
		String orgid = getPara("orgid");
		List<OrgDepartment> deplist = OrgDepartment.dao.loadPickPerson(orgid);
		this.renderJson(deplist);
	}
	/**
	 * 活动管理模块调用的添加部门下人员方法 2016.1.19 wzf
	 */
	public void loadDepPerson(){
		String depid = getPara("depid");
		List<User> uList = User.dao.findDepUsers(depid);
		this.renderJson(uList);
	}

	public void depTreePick(){
		String keyid = getAttr("keyid");
		//List<DepTreeVO> oadlist = Department.dao.findDepTreePick(keyid);
		this.renderJson(OrgDepartment.dao.findDepTreePick(keyid));
	}

	public void deleteOrgDep(){
		String keyid = getAttr("keyid");
		List<User> user = User.dao.delOrgDepCheckUserExist(keyid);
		List<OrgDepartment> orgDep = OrgDepartment.dao.findByParentId(keyid);
		if(orgDep.size()>0){
			this.rendJson(false, 800, "该部门下还有其他子部门，不能删除！");
		}else if(user.size()>0){
			this.rendJson(false, 801, "该部门下还有人员，不能删除！");
		}else{
			OrgDepartment.dao.deleteById(keyid);
			this.success();
		}
		
	}
}
