package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Db;

@TableBind(tableName = "s_role_user", pkName = "KeyId")
public class RoleUser extends BaseModel<RoleUser> {

	public static RoleUser dao = new RoleUser();
	public static String table = "s_role_user";

	/**
	 * 保存用户与角色关系
	 * 
	 * @param user
	 * @param rolesid
	 */
	public void save(String uid, String rolesid) {
//		String[] ids = rolesid.split(",");
		
//		for (String rid : ids) {
			RoleUser ru = new RoleUser();
			ru.set("roleid", rolesid);
			ru.set("userid", uid);
			ru.set("KeyID", getId());
			ru.save();
//		}
	}

	/**
	 * 更新用户与角色关系
	 * 
	 * @param uid
	 * @param rolesid
	 */
	public void update(String uid, String rolesid) {
		String sql = "delete from " + table + " where userid = ?";
		Db.update(sql, uid);
		save(uid, rolesid);
	}

	/**
	 * 清理用户与角色关系
	 * 
	 * @param uids
	 */
	public boolean deleteByUserId(String[] uids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("DELETE FROM ");
		sql.append(table);
		sql.append(" WHERE userid in ( ? ");
		for (String keyid : uids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";

		int i = Db.update(sqlString, uids);
		return i > 0;
	}
	
	/**
	 * 清理用户与角色关系
	 * 
	 * @param uids
	 */
	public boolean deleteByRoleId(String[] rids) {
		StringBuffer sql = new StringBuffer("");
		sql.append("DELETE FROM ");
		sql.append(table);
		sql.append(" WHERE roleid in ( ? ");
		for (String keyid : rids) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";

		int i = Db.update(sqlString, rids);
		return i > 0;
	}
}
