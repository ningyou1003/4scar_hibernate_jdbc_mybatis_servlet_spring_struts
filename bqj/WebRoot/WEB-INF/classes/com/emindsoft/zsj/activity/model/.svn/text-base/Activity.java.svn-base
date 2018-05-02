package com.emindsoft.zsj.activity.model;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "a_activity", pkName = "KeyId")
public class Activity extends BaseModel<Activity>{
	public static Activity dao = new Activity();
	public static String table = "a_activity";
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}

	public Page<Activity> page(int pageNo, int pageSize, String type,
			String regionCode, String title, String ispublic) {
		if (!StringUtils.isEmpty(regionCode)) {
			String sql = "SELECT act.keyid, act.title, act.ispublic, act.publishTime, input.RelaName inputperson, report.RelaName reportperson ";
			String sqlExpand = "FROM " + table + " act LEFT JOIN s_user input ON act.createuserid=input.keyid LEFT JOIN s_user report on act.reportpersonid=report.keyid where act.type='"+type+"' ";
			sqlExpand += "AND act.status in (1,3,4) ";
			if(StringUtils.isNotEmpty(title)){
				sqlExpand += "AND act.title like'%" +title+"%' ";
			}
			if(StringUtils.isNotEmpty(ispublic)){
				sqlExpand += "AND act.ispublic='" +ispublic+"'";
			}
			sqlExpand += "AND act.regionId='" + regionCode + "' ORDER BY act.createTime DESC";
			return this.paginate(pageNo, pageSize, sql, sqlExpand);
		} 
		return null;
	}

	public Page<Activity> page(int pageNo, int pageSize, String type) {
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_activity WHERE type='" + type + "' ORDER BY publishTime");
	}
	/**
	 * 公众网-专项行动
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @param regioncode
	 * @return
	 */
	public Page<Activity> page(int pageNo, int pageSize, String type,String regioncode) {
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_activity WHERE type='" + type + "' and regionid='"+regioncode+"' and ispublic=1 and status<>0 ORDER BY publishTime DESC");
	}
	/**
	 * 公众网-首页
	 * @param pageNo
	 * @param pageSize
	 * @param regioncode
	 * @return
	 */
	public Page<Activity> pageForIndex(int pageNo, int pageSize, String regioncode) {
		return this.paginate(pageNo, pageSize, "SELECT *"," FROM a_activity WHERE  regionid='"+regioncode+"' and ispublic=1 and status<>0 ORDER BY publishTime DESC");
	}

}
