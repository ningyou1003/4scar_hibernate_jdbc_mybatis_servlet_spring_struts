package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.StatusCode;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.*;
import com.emindsoft.zsj.system.vo.OrgSelectVO;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ControllerKey("org")
public class OrgCtrl extends AdminBaseController<Org> {

	public OrgCtrl() {
		this.modelClass = Org.class;
	}
	
	/**
	 * 查询机构方法
	 */
	public void orgListPage(){
		String currentUserRegion = getCurrentUserRegionId();
		RolePower rp = RolePower.dao.getOperPower("532", getCurrentUserId());
		OrgSelectVO orgVO = Tools.getSubVO(OrgSelectVO.class, getRequest());
		Page<Org> orgPage=Org.dao.page(getPageNo(), getPageSize(), orgVO,currentUserRegion);
		rendJson(true, 001, StatusCode.get("001"), new PageVO(orgPage, rp));
		
	}
	
	/**
	 * 加载机构下的人员
	 * */
	public void loadOrgUsers(){
		String orgid = getAttr("orgid");
		List<User> uList = User.dao.findOrgUsers(orgid);
		this.renderJson(uList);
	}
	
	/**
	 * 删除机构方法
	 */
	public void deleteOrg() {
		String[] keyids = getPara("keyids").split(",");
		boolean a = OrgDepartment.dao.checkDep(keyids);
		if(a){
			this.rendJson(false, 810, StatusCode.get("810"), keyids);
			return;
		}
		Org.dao.deleteOrgByIds(keyids);
		this.rendJson(true, null, "操作成功！", keyids);
	}
	
	/**
	 * 增加机构
	 */
	public void addOrg(){
		Org org;
		try{
			org= getModel();
			org.set("KeyId", Org.dao.getId());
			org.set("isvailable", 0);//默认状态0表示可用
			org.save();//保存方法
			success(org.getStr("keyid"));
		}catch (Exception e) {
			log.error("保存用户异常", e);
			this.rendJson(false, null, "保存数据异常！");
		}
		
	}
	
	//2015.6.16增加机构的方法
	@Before(Tx.class)
	public void add() throws Exception {
		Org org;
		try {
			org= getModel();
			org.set("KeyId", Org.dao.getId());

			String regionCode = org.getStr("regioncode");
			if(StringUtils.isEmpty(regionCode)) {
				org.set("regioncode", getCurrentUserRegionId());
			}
			org.set("orgNum", setOrgNum(getCurrentUserRegionId()));
			org.save();
			this.success(org.getStr("KeyId"));
		} catch (Exception e) {
			// 　捕捉顶级异常，用于保存用户信息事务出错时回滚
			log.error("保存用户异常", e);
			this.error(801);
			throw e;
		}
	}
	
	/**
	 * 编辑机构
	 */
	public void edit(){
		try {
			//删除待审核信息
			OrgTemp orgTemp = (OrgTemp)getModel2(OrgTemp.class);
			OrgTemp.dao.deleteById(orgTemp.getStr("keyId"));

			//插入临时表
			orgTemp.set("status", 0);
			orgTemp.save();

			//主表暂存状态
			new Org().set("keyId", orgTemp.getStr("keyId")).set("status", 2).update();

			success(orgTemp);
		} catch (Exception e) {
			log.error("更新用户异常", e);
			error(002);
		}
		
	}
	/**
	 * 获取编辑机构的信息
	 */
	public void editOrg(){
		String keyid=getPara("keyid");
		Org org=Org.dao.loadOrgDetail(keyid);
		success(org);
	}
	
	/**
	 * 机构详细信息页,可以编辑
	 */
	public void editData(){
		String keyid = getPara("keyid");

		boolean isSame = false; //是否是本级
		//非本级的就是审核
		Org org=Org.dao.loadOrgDetail(keyid);
		String regionCode = org.getStr("regionCode");
		String currentUserRegion = getCurrentUserRegionId();
		if(StrKit.notBlank(regionCode) && StrKit.notBlank(currentUserRegion) && regionCode.equals(currentUserRegion)){
			isSame = true;
		}
		org.put("isSame", isSame);

		OrgTemp orgTemp = OrgTemp.dao.loadOrgDetail(keyid);
		if(orgTemp!=null){
			orgTemp.put("status", org.getInt("status"));

			boolean tempIsSame = false;
			String tempRegionCode = orgTemp.getStr("regionCode");
			if(StrKit.notBlank(tempRegionCode) && StrKit.notBlank(currentUserRegion) && tempRegionCode.equals(currentUserRegion)){
				tempIsSame = true;
			}
			orgTemp.put("isSame", tempIsSame);
			success(orgTemp);
			return;
		}

		success(org);
	}
	
	/**
	 *获得弹出框机构树形信息 
	 */
	public void loadOrgTree() {
		List<Org> orgTree = Org.dao.loadOrgTree(getCurrentUserRegionId());
		this.renderJson(orgTree);
	}

	/**
	 * 提交审核
	 */
	public void apply(){
		String id = getPara("id");
		new OrgTemp().set("keyId", id).set("status", 1).update();
		//主表审核状态
		new Org().set("keyId", id).set("status", 1).update();
		success();
	}

	@Before(Tx.class)
	public void acceptChange(){
		String id = getPara("id");

		OrgTemp orgTemp = OrgTemp.dao.findById(id);
		if(orgTemp!=null){
			new Org().setAttrs(orgTemp.getAttrs()).update();
		}
		OrgTemp.dao.deleteById(id);

		//主表通过状态
		new Org().set("keyId", id).set("status", 0).update();

		success();
	}

	public void rejectChange(){
		String id = getPara("id");
		OrgTemp.dao.deleteById(id);

		//主表拒绝状态
		new Org().set("keyId", id).set("status", -1).update();

		success();
	}

	/**
	 * 初始化数据库中机构表编号
	 */
	public void setOrgNumber(){
		List<Org> orgList = Org.dao.find("select * from " + Org.table + " ");
		int n ;
		String regioncode;
		for(Org org:orgList){
			n=8;
			String orgNum = org.getStr("orgNum");
			regioncode = org.getStr("regioncode");
			n -= regioncode.length();
			if(n>0){
				regioncode += String.format("%1$0"+n+"d", 0);
			}
			if(StringUtils.isEmpty(orgNum)){
				orgNum = calculateOrgCode(regioncode);
				org.set("orgNum", orgNum);
				org.update();
			}
		}
		success(orgList);
	}
	
	/**
	 * 根据机构所属区域对机构进行编码
	 * @param regioncode 所属区域
	 * @return
	 */
	private String setOrgNum(String regioncode){
		int n = 8;
		n -= regioncode.length();
		if(n>0){
			regioncode += String.format("%1$0"+n+"d", 0);
		}
		String orgNum = calculateOrgCode(regioncode);
		return orgNum;
	}
	
	private String calculateOrgCode(String pre){
		for(int i=1; i<999; i++){
			String orgNum = StringUtils.leftPad(i+"", 3, "0");
			Org org = Org.dao.findByOrgNum(pre + orgNum);
			if(org==null){
				return pre + orgNum;
			}
		}

		return "";
	}
}
