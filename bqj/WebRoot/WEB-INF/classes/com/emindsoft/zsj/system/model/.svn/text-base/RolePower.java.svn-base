package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

@TableBind(tableName = "s_role_power")
public class RolePower extends BaseModel<RolePower> {

	public static RolePower dao = new RolePower();
	public static String table = "s_role_power";

	public void deleteRolesPower(String objectID) {
		Db.update("delete from " + RolePower.table + " where objectID = ?", objectID);
	}

	/**
	 * 根据权限码和当前用户ID获取该用户在此页面下的权限
	 * 多角色取权限并集
	 * 
	 * @param pid
	 * @param uid
	 * @return
	 */
	public RolePower getPagePower(String pid, String uid) {
		String sql = "select 1 as AddPower, 1 as EditPower, 1 as DelPower, 1 as AppPower, 1 as LookPower from " + table + " LIMIT 1";
		RolePower p = findFirst(sql);
		return p;
	}

	/**
	 * 根据PowerBind标签，当前用户id。 判断是否对即将执行的方法有权限。同时记录日志
	 * 
	 * @param code
	 * @param currentUserId
	 * @param ip
	 * @return
	 */
	public boolean checkPower(String[] code, String currentUserId, String ip) {
		for (String powerCode : code) {
			String[] powers = powerCode.split("_");
			RolePower rp = getPagePower(powers[0], currentUserId);
			String n = powers[0].substring(0, 1);	//截取权限首位数字
			if (rp != null) {
				for (int i = 1; i < powers.length; i++) {
					if (rp.getLong(powers[i]) != 1) {
						Log.dao.wirteDeny(powerCode, currentUserId, ip);
						return false;
					}
				}
			} else {
				Log.dao.wirteDeny(powerCode, currentUserId, ip);
				return false;
			}
			Log.dao.wirteLog(powerCode, currentUserId, ip, null);
		}

		return true;
	}

	/**
	 * 获取当前用户的操作权限
	 * @param pid 权限id
	 * @param currentUserId 当前用户id
	 * @return
	 */
	public RolePower getOperPower(String pid, String currentUserId) {
		RoleUser ru = RoleUser.dao.findFirst("SELECT roleId FROM s_role_user WHERE userId='"+currentUserId+"'");
		String roleId = ru.getStr("roleId");
		String sql = "SELECT AddPower,EditPower,DelPower,LookPower,AppPower,CheckPower FROM s_role_power WHERE objectID=? AND PowerID=?";

		List<RolePower> rpList = find(sql, roleId, pid);
		RolePower rp = new RolePower();
		for(RolePower r : rpList){
			String[] names = r.getAttrNames();
			Set<Entry<String,Object>> es=r.getAttrsEntrySet();
			for(Entry<String, Object> e:es){
				if(e.getValue()!=null){
					rp.set(e.getKey(), e.getValue());
				}	
			}
		}
		return rp;
	}
	
	public RolePower getLookPower(String pid, String uid) {
		String sql = "select 0 as AddPower, 0 as EditPower, 0 as DelPower, 0 as AppPower, 1 as LookPower from " + table;
		RolePower p = findFirst(sql);
		return p;
	}

	public String getPermission(String pid, String currentUserId) {
		String sql = "SELECT rp.permission" +
				" FROM " + RolePower.table + " rp, s_role_user ru" +
				" WHERE rp.objectID=ru.roleid and ru.userid=? AND rp.PowerID=?";

		Set permissionSet = new HashSet();
		List<RolePower> rpList = find(sql, currentUserId, pid);
		for(RolePower r : rpList){
			String p = r.getStr("permission");
			if(StrKit.notBlank(p)){
				String[] ps = p.split(",");
				for(String pp : ps){
					permissionSet.add(pp);
				}
			}
		}

		String permission = StringUtils.join(permissionSet, ",");
		return permission;
	}

}
