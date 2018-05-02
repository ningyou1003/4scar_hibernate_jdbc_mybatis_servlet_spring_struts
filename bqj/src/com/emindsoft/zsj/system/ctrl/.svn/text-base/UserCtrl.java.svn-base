package com.emindsoft.zsj.system.ctrl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.dreampie.routebind.ControllerKey;

import com.emindsoft.zsj.base.anatation.PowerBind;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;
import com.emindsoft.zsj.system.model.Role;
import com.emindsoft.zsj.system.model.RolePower;
import com.emindsoft.zsj.system.model.RoleUser;
import com.emindsoft.zsj.system.model.User;
import com.emindsoft.zsj.system.vo.UserDetailVO;
import com.emindsoft.zsj.system.vo.UserPageVO;
import com.emindsoft.zsj.system.vo.UserSelectVO;
import com.emindsoft.zsj.util.PropertiesContent;
import com.emindsoft.zsj.util.Tools;
import com.emindsoft.zsj.util.safe.MD5;
import com.emindsoft.zsj.vo.PageVO;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * Created by ym on 15-3-9.
 */
@ControllerKey("user")
public class UserCtrl extends AdminBaseController<User> {

	public UserCtrl() {
		this.modelClass = User.class;
	}

	/**
	 * 用户信息
	 */
	public void info() {
		String id = getPara("id", getCurrentUserId());
		User info = User.dao.findById(id);
		success(info);
	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	@Before(Tx.class)
	@PowerBind(code = "813_LookPower", funcName = "添加用户")
	public void add() throws Exception {
		User user;
		try {
			user = getModel(User.class);
			User u = User.dao.findByUserName(user.getStr("username"));
			if(u==null){
				user.set("KeyId", User.dao.getId());
				String pwd = user.getStr("password");
				pwd = MD5.getMD5ofStr(PropertiesContent.get("md5code")
						+ pwd + PropertiesContent.get("md5code"));
				user.set("PassWord", pwd);
				String rolesid = getAttr("rolesId");
				RoleUser.dao.save(user.getStr("KeyId"), rolesid);//保存用户与角色关系

				if(StrKit.isBlank(user.getStr("regionId"))){
					user.set("regionId", getCurrentUserRegionId());
				}

				user.save();
				success(id);
			} else {
				error(003, "该用户名已经存在，请重新输入！");
			}		
			
		} catch (Exception e) {
			// 　捕捉顶级异常，用于保存用户信息事务出错时回滚
			log.error("保存用户异常", e);
			error(801);
			throw e;
		}
	}

	/**
	 * 用户管理模块删除用户时将选中的用户状态改为不可用，
	 * @throws Exception 
	 */
	@Before(Tx.class)
	@PowerBind(code = "813_DelPower", funcName = "删除用户")
	public void deleteUser() throws Exception {
		try {
			String[] keyids = getPara("keyids").split(",");
			User.dao.setUnvailable(keyids);
			success();
		} catch (Exception e) {
			log.error("删除用户异常", e);
			error(805);
			throw e;
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @throws Exception
	 */
	@Before(Tx.class)
	@PowerBind(code = "813_EditPower", funcName = "编辑用户信息")
	public void edit() throws Exception {
		User user;
		try {
			user = getModel(User.class);
			String rolesid = this.getPara("rolesId");
			RoleUser.dao.update(user.getStr("KeyId"), rolesid);
			user.update();
			String currentUserId = getCurrentUserId();
			if(currentUserId.equals(user.getStr("keyid"))){
				this.setCookie("pagesize", user.getInt("UPageSize") + "", 604800);
			}
			success();
		} catch (Exception e) {
			// 　捕捉顶级异常，用于更新用户信息事务出错时回滚
			log.error("更新用户异常", e);
			error(802);
			throw e;
		}
	}

	/**
	 * 获取需要修改的用户的相关信息
	 */
	public void editData() {
		String keyid = getPara("keyid");
		User user = User.dao.loadUserDetail(keyid);
		Role role = Role.dao.loadUserRole(keyid);
		if(role!=null){
			int level = role.getInt("level");
			List<Role> rolesList = Role.dao.loadRoleByLevel(String.valueOf(level));
			UserDetailVO udVO = new UserDetailVO(user, role, rolesList);
			success(udVO);
		}
		
	}

	/**
	 * 用户列表
	 */
	@PowerBind(code = "813_LookPower", funcName = "用户列表")
	public void userList() {
		RolePower rp = RolePower.dao.getOperPower("813", getCurrentUserId());
		UserSelectVO userVO = Tools.getSubVO(UserSelectVO.class, getRequest());
		String regionId = getPara("regionId");
		if(regionId==null || regionId==""){
			regionId = getCurrentUserRegionId();//获取当前用户所在区域编码
		}
		Page<User> userPage = User.dao.page(getPageNo(), getPageSize(), userVO, regionId);
		success(new PageVO(userPage, rp));
	}

	/**
	 * 用户分页列表
	 */
	@PowerBind(code = "813_LookPower", funcName = "用户分页列表")
	public void userListPage() {
		RolePower rp ;
		UserSelectVO userVO = Tools.getSubVO(UserSelectVO.class, getRequest());
		String chooseRegionId = getChooseRegionId();
		String userRegionId = getCurrentUserRegionId();
		String regionCode = "";
		if(!StringUtils.isEmpty(chooseRegionId) && !"undefined".equals(chooseRegionId)){		
			rp = RolePower.dao.getLookPower("813", getCurrentUserId());
			userRegionId = chooseRegionId;
		} else {
			rp = RolePower.dao.getOperPower("813", getCurrentUserId());
		}
		String regioncode =  getPara("regionId");
		if(StringUtils.isEmpty(regioncode)||"undefined".equals(regioncode)){
			regionCode =  userRegionId;//获取当前用户所在区域编码
		} else {
			regionCode = regioncode;
		}
		
		Page<User> userPage = User.dao.page(getPageNo(), getPageSize(), userVO, regionCode);
		List<Role> roleList = Role.dao.loadRolePick(regionCode);
		Role role = new Role();
		role.set("name","所有角色");
		role.set("keyid", "");
		roleList.add(0, role);

		UserPageVO upvo = new UserPageVO(userPage, rp);
		upvo.setRoleList(roleList);
		success(upvo);
	}

	public void userDetail() {
		String keyid = getPara("keyid");
		User user = User.dao.loadUserDetail(keyid);
		Role role = Role.dao.loadUserRole(keyid);
		UserDetailVO udVO = new UserDetailVO(user, role);
		success(udVO);
	}

	/**
	 * 密码修改
	 */
	public void editPassword() {
		String id = getPara("id");
		String password = getPara("password");
		String newpassword = getPara("newpassword");
		String newpasswordrepeat = getPara("repeatnewpassword");
		if (!newpassword.equals(newpasswordrepeat)) {
			error(803);
			return;
		}
		User user = User.dao.check(id, password);
		if (user == null) {
			error(804);
			return;
		}
		newpassword = MD5.getMD5ofStr(PropertiesContent.get("md5code")
				+ newpassword + PropertiesContent.get("md5code"));
		user.set("PassWord", newpassword);
		user.update();
		success();
	}
	
	public void resetPw(){
		String keyid = getPara("keyid");
		User user = User.dao.findById(keyid);
		if(user!=null){
			String pw = MD5.getMD5ofStr(PropertiesContent.get("md5code")
					+ "123456" + PropertiesContent.get("md5code"));
			user.set("PassWord", pw);
			user.update();
		}
		success();
	}

}
