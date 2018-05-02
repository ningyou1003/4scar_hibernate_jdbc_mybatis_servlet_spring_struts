package com.emindsoft.zsj.system.ctrl;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.*;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/**
 * 角色controller
 * 
 * @author ym
 */
@ControllerKey("role")
// 访问这个controller所需要的字符串，命名为role，每一个方法相当于对应一个action
public class RoleCtrl extends AdminBaseController<Role> {

	/**
	 * 构造方法，并在父类中封装了对象modelClass，方便获取页面数据
	 */
	public RoleCtrl() {
		this.modelClass = Role.class;
	}

	/**
	 * 增加角色方法
	 */
	@PowerBind(code = "812_AddPower", funcName = "添加角色")
	public void add() {
		Role role;
		try {
			role = getModel();
			String name = role.getStr("name");
			int level = role.getInt("level");
			String permission = getPara("par");
			List<Role> roleList = Role.dao.findByNameAndLevel(name, level);
			if(roleList.size()>0){
				error(806);
			} else {
				role.set("KeyId", Role.dao.getId());
				role.save();
				permissionDeal(permission, role.getStr("keyid"));
				success(001);
			}

		} catch (Exception e) {
			log.error("保存角色异常", e);
			error(806);
		}
	}

	/**
	 * 获取角色编辑数据
	 */
	public void editData() {// 编辑
		String keyid = getPara("keyid");
		Role role = Role.dao.findByKeyid(keyid);
		success(role);
	}

	/**
	 * 删除角色方法
	 * 
	 * @throws Exception
	 */
	@Before(Tx.class)
	@PowerBind(code = "812_DelPower", funcName = "删除角色")
	public void deleteRole() throws Exception {
		try {
			String[] keyids = getPara("keyids").split(",");
			Role.dao.deleteByIds(keyids);
			RoleUser.dao.deleteByRoleId(keyids);
			success();
		} catch (Exception e) {
			log.error("删除角色异常", e);
			error(808);
			throw e;
		}

	}

	/**
	 * 获取角色列表方法
	 */
	@PowerBind(code = "812_LookPower", funcName = "角色列表")
	public void roleList() {
		RolePower rp = RolePower.dao.getPagePower("812", getCurrentUserId());	
		/*RolePower rp;
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode = "";
		if(StringUtils.isEmpty(chooseRegionId)||"undefined".equals(chooseRegionId)||userRegionId.equals(chooseRegionId)){		
			rp = RolePower.dao.getOperPower("500", getCurrentUserId());
			regionCode = userRegionId;
		} else {
			rp = RolePower.dao.getLookPower("500", getCurrentUserId());
			regionCode = chooseRegionId;
		}
		Page<Role> roleList = Role.dao.page(getPageNo(), getPageSize(), regionCode);*/
		Page<Role> roleList = Role.dao.page(getPageNo(), getPageSize());
		success(PageVO.getPageVO(roleList, rp));
	}

	/**
	 * 获取角色对应权限列表数据
	 */
	public void loadRolePowerEdit() {
		String userId = getCurrentUserId();
		String keyid = getPara("keyid");//角色id
//		List<RolePowerVO> powerList = RolesPower.dao.loadPowerEdit(userId, keyid);

		String sql = "select p.KeyID, p.`Name`, p.ParentID, p.OrderID, p.ISShow, rp.lookpower, rp.permission from " + Power.table + " p left outer join " + RolePower.table + " rp on p.keyId=rp.powerId and rp.objectId=? where p.isShow=0 ";
		String expand = " order by p.orderId";
		/*String gxzsjOrgNum = PropertiesContent.get("gxzsj_orgnum");
		User user = User.dao.findFirst("SELECT * FROM s_user u," + Org.table + " o WHERE u.orgid=o.KeyID AND o.orgNum='"+gxzsjOrgNum+"' AND u.KeyID='"+userId+"'");
		if(user==null){
			sql += " and p.KeyID NOT LIKE '4%' ";
		}*/
		List<Record> pList = Db.find(sql+expand, keyid);
		
		String permissionSql = "select p.KeyID, p.powerId, p.`key`,p.`name`,sp.ParentID from s_power_permission p JOIN s_power sp ON p.powerId=sp.KeyID";
		List<Record> allPermissionList = Db.find(permissionSql);
		Map<String, List> allPermissionMap = new HashMap();
		for(Record r : allPermissionList){
			String powerId = r.getStr("powerId");
			if(!allPermissionMap.containsKey(powerId)){
				allPermissionMap.put(powerId, new ArrayList());
			}
			allPermissionMap.get(powerId).add(r);
		}

		for(Record r : pList){
			String powerId = r.getStr("keyId");
			String parentPowerID = r.getStr("ParentID");
			List<Map> bizPermission = new ArrayList<Map>();

			List<Record> list = allPermissionMap.get(powerId);
			if(list!=null)
			for(Record permission : list){
				Map biz = new HashMap();
				String actualPermission = r.getStr("permission");
				String permissionStr = permission.getStr("key")+",";
				biz.put("key", permission.getStr("key"));biz.put("name", permission.getStr("name"));
				if(StrKit.notBlank(actualPermission) && actualPermission.contains(permissionStr)){
					biz.put("isSelect", "1");
				}else{
					biz.put("isSelect", "0");
				}
				bizPermission.add(biz);
			}

			r.set("bizPermission", bizPermission);
			r.set("parentID", parentPowerID);
		}

		renderJson("Rows", pList);
	}
	
	/**
	 * 角色列表用于选择
	 */
	public void loadRolePick() {
		String regionCode = getPara("regionCode");
		List<Role> roleList = Role.dao.loadRolePick(regionCode);
		success(roleList);
	}

	/**
	 * 根据登录用户所在的区域编码加载,分区域角色树形结构
	 */
	public void loadRoleTreeByUserRegionId() {
		String regioncode = getAttr("regioncode");
		String currentUserId = getCurrentUserId();
		this.renderJson(Area.dao.loadRoleTreeByUserRegionId(regioncode,
				currentUserId));
	}
	
	public void loadRoleByLevel(){
		String roleLevel = getAttr("roleLevel");
		if(!StringUtils.isEmpty(roleLevel)){
			success(Role.dao.loadRoleByLevel(roleLevel));
		}
	}
	
	/**
	 * 获取用户所属角色信息
	 */
	public void roleUserDate() {
		String userId = getPara("userId");
		List<Role> rolelist = Role.dao.loadRolesForUser(userId);
		success(rolelist);
	}
	
	/**
	 * 2015-12-10
	 *根据用户选择的工作单位(机构)信息，查询该单位(机构)下所在的区域有哪些角色
	 * 
	 * */
	public void loadRoleByUserRegionId() {
		String regionid = getAttr("regionid");
		if(StrKit.isBlank(regionid)){
			regionid = getCurrentUserRegionId();
		}
		List<Role> regionRole = Role.dao.loadRolesForUserRegion(regionid);
		this.renderJson(regionRole);
	}
	
	/**
	 * 角色编辑，需要事务
	 * 
	 * @throws Exception
	 */
	@Before(Tx.class)
	// 调用框架底层提供的事务类
	@PowerBind(code = "812_EditPower", funcName = "编辑角色信息")
	public void edit() throws Exception {
		String permission = getPara("par");

		//Role
		Role role = getModel();
		role.saveOrUpdate();

		String keyId = getPara("keyid");
		permissionDeal(permission, keyId);
		success("");
	}
	
	//角色处理方法，删除原有角色权限，绑定新的权限
		private void permissionDeal(String permission, String keyId) {
			//删除原角色权限
			if(!StringUtils.isEmpty(keyId)){
				RolePower.dao.deleteRolesPower(keyId);
			}

			//处理
			Map<String, String> permissionMap = new HashMap();
			Set<String> lookPowerSet = new HashSet<String>();
			Set<String> addPowerSet = new HashSet<String>();
			Set<String> delPowerSet = new HashSet<String>();
			Set<String> editPowerSet = new HashSet<String>();
			Set<String> checkPowerSet = new HashSet<String>();
			String[] permissionArr = permission.split(",");
			for(String p : permissionArr){
				if(StrKit.isBlank(p)){
					continue;
				}

				String[] pair = p.split("_");
				if(pair[1].equals("LookPower")) {
					lookPowerSet.add(pair[0]);
				}
				if(pair[1].equals("AddPower")) {
					addPowerSet.add(pair[0]);
				}
				if(pair[1].equals("DelPower")) {
					delPowerSet.add(pair[0]);
				}
				if(pair[1].equals("EditPower")) {
					editPowerSet.add(pair[0]);
				}
				if(pair[1].equals("CheckPower")) {
					checkPowerSet.add(pair[0]);
				}
				if(permissionMap.containsKey(pair[0])){
					permissionMap.put(pair[0], permissionMap.get(pair[0])+pair[1]+",");
				}else{
					permissionMap.put(pair[0], pair[1]+",");
				}
			}

			for(String p : permissionMap.keySet()){
				System.out.println(p + " " + permissionMap.get(p));
				RolePower rp = new RolePower();
				rp.set("powerId", p).set("objectId", keyId).set("permission", permissionMap.get(p));
				if(lookPowerSet.contains(p)){
					rp.set("LookPower", 1);
				}
				if(addPowerSet.contains(p)){
					rp.set("AddPower", 1);
				}
				if(delPowerSet.contains(p)){
					rp.set("DelPower", 1);
				}
				if(editPowerSet.contains(p)){
					rp.set("EditPower", 1);
				}
				if(checkPowerSet.contains(p)){
					rp.set("CheckPower", 1);
				}
				rp.saveOrUpdate();
			}
		}
}
