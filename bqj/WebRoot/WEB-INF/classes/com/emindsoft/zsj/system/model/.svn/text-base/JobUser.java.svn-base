package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Db;

@TableBind(tableName = "s_job_user", pkName = "KeyId")
public class JobUser extends BaseModel<JobUser> {

	public static JobUser dao = new JobUser();
	public static String table = "s_job_user";

	/**
	 * 保存用户与职务关系
	 * 
	 * @param user
	 * @param jobsid
	 */
	public void save(String uid, String jobsid) {
		String[] ids = jobsid.split(",");
		for (String rid : ids) {
			JobUser ju = new JobUser();
			ju.set("jobid", rid);
			ju.set("userid", uid);
			ju.set("KeyID", getId());
			ju.save();
		}
	}

	/**
	 * 更新用户与职务关系
	 * 
	 * @param uid
	 * @param jobsid
	 */
	public void update(String uid, String jobsid) {
		String sql = "delete from " + table + " where userid = ?";
		Db.update(sql, uid);
		save(uid, jobsid);
	}

	/**
	 * 清理用户与职务关系
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
	 * 清理用户与职务关系
	 * 
	 * @param uids
	 */
	public boolean deleteByJobId(String[] jobsid) {
		StringBuffer sql = new StringBuffer("");
		sql.append("DELETE FROM ");
		sql.append(table);
		sql.append(" WHERE jobid in ( ? ");
		for (String keyid : jobsid) {
			sql.append(", ? ");
		}
		String sqlString = sql.substring(0, sql.length() - 4);
		sqlString = sqlString + ")";

		int i = Db.update(sqlString, jobsid);
		return i > 0;
	}
}
